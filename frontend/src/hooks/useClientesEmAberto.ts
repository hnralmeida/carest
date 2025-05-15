import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useClientesEmAbertoHook = () => {
    const [clientesEmAberto, setClientesEmAberto] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(false);

    const listarClientesEmAberto = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/emaberto`);
            if (response.data) {
                setClientesEmAberto(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    };

    const relatoriosClientesEmAberto = async () => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/emaberto/pdf`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-clientes-em-aberto.pdf'; // nome do arquivo
            link.click();

            // Libera o objeto do URL após uso
            window.URL.revokeObjectURL(url);

            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao baixar o PDF");
        }
    };

    return {
        loading,
        clientesEmAberto,
        listarClientesEmAberto,
        relatoriosClientesEmAberto,
    };
};
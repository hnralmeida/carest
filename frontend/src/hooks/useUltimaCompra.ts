import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useUltimaCompraHook = () => {
    const [ultimaCompra, setUltimaCompra] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [dataSelecionada, setDataSelecionada] = useState<string>('');

    const listarUltimaCompra = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ultimaCompra`);
            if (response.data) {
                setUltimaCompra(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    };

    const relatoriosUltimaCompra = async () => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/ultimaCompra/pdf`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-ultimaCompra-medio.pdf'; // nome do arquivo
            link.click();

            // Libera o objeto do URL após uso
            window.URL.revokeObjectURL(url);

            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao baixar o PDF");
        }
    };

    const ultimaCompraPorData = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ultimaCompra/${dataSelecionada}`);
            if (response.data) {
                setUltimaCompra(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    }

    return {
        loading,
        ultimaCompra,
        listarUltimaCompra,
        relatoriosUltimaCompra,
        setDataSelecionada,
        ultimaCompraPorData,
    };
};
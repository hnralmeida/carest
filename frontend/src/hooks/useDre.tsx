import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { DRE } from "@/models/dre";


export const useDreHook = () => {
    const [dre, setDre] = useState<DRE[] | null>(null);
    const [loading, setLoading] = useState(true);

    const listarDre = async (dataInicio: string, dataFim: string) => {
        setLoading(true);
        try {
            const hoje = new Date();
            const response = await axiosClient.get(`/relatorios/dre-diario?dataInicio=${dataInicio}&dataFim=${dataFim}`);
            if (response.data) {
                setDre(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            console.error('Error fetching administrators:', error);
            return Promise.reject("Clientes não encontrados");
        }
    };

    const relatoriosDRE = async (dataInicio: string, dataFim: string) => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/pdf/dre-diario?dataInicio=${dataInicio}&dataFim=${dataFim}`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-dre.pdf'; // nome do arquivo
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
        dre,
        listarDre,
        relatoriosDRE,
        loading
    };
};
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";


export const useConsumoDiarioHook = () => {
    const [consumoDiario, setConsumoDiario] = useState<any>(null);

    const [loading, setLoading] = useState(false);

    const listarConsumoDiario = async (dataInicio: string, dataFim: string) => {
        setLoading(true);
        if (!dataInicio || !dataFim) {
            setLoading(false);
            return Promise.reject("Data de início e fim são obrigatórias");
        }
        try {
            const response = await axiosClient.get(`/relatorios/consumoDiario?dataInicio=${dataInicio}&dataFim=${dataFim}`,
                { responseType: 'blob' }
            );
            const blob = await response.data;
            console.log("Blob URL:", URL.createObjectURL(blob));
            if (blob) {
                setConsumoDiario(URL.createObjectURL(blob));
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            console.log(error);
            return Promise.reject("Erro ao buscar dados do Consumo Diário");
        }
    };

    const relatoriosConsumoDiario = async (dataInicio: string, dataFim: string) => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/pdf/consumoDiario?dataInicio=${dataInicio}&dataFim=${dataFim}`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-Consumo.pdf'; // nome do arquivo
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
        consumoDiario,
        listarConsumoDiario,
        relatoriosConsumoDiario,
    };
};
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { RelatorioProduto } from "@/models/relatorioProduto";


export const    useRelatorioProdutoHook = () => {
    const [relatorioProduto, setRelatorioProduto] = useState<RelatorioProduto[] | null>(null);
    const [loading, setLoading] = useState(false);

    const listarRelatorioProduto = async (dataInicio: string, dataFim: string) => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/produtosSerial?dataInicio=${dataInicio}&dataFim=${dataFim}`);
            if (response.data) {
                setRelatorioProduto(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar consumo de Produto");
        }
    };

    const relatoriosRelatorioProduto = async (dataInicio: string, dataFim: string) => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/pdf/produtosSerial?dataInicio=${dataInicio}&dataFim=${dataFim}`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-consumo-produtos.pdf'; // nome do arquivo
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
        relatorioProduto,
        listarRelatorioProduto,
        relatoriosRelatorioProduto,
    };
};
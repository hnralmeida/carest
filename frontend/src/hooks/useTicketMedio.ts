import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useTicketMedioHook = () => {
    const [ticketMedio, setTicketMedio] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [dataInicio, setDataInicio] = useState<string>('');
    const [dataFim, setDataFim] = useState<string>('');

    const listarTicketMedio = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ticketMedio?dataInicio=2000/01/01&dataFim=${dataFim}`);
            if (response.data) {
                setTicketMedio(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    };

    const relatoriosTicketMedio = async () => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/ticket/pdf`, {
                responseType: 'blob', // importante para receber os dados como blob
            });

            // Cria um URL temporário para o blob
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);

            // Cria um link e aciona o clique automaticamente
            const link = document.createElement('a');
            link.href = url;
            link.download = 'relatorio-ticket-medio.pdf'; // nome do arquivo
            link.click();

            // Libera o objeto do URL após uso
            window.URL.revokeObjectURL(url);

            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao baixar o PDF");
        }
    };

    const ticketPorData = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ticket/${dataInicio}/${dataFim}`);
            if (response.data) {
                setTicketMedio(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    }

    return {
        loading,
        ticketMedio,
        listarTicketMedio,
        relatoriosTicketMedio,
        setDataInicio,
        setDataFim,
        ticketPorData,
    };
};
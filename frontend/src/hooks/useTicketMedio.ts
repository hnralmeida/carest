import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";
import { dateToISO } from "@/lib/utils";


export const useTicketMedioHook = () => {
    const [ticketMedio, setTicketMedio] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(false);

    const listarTicketMedio = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ticketMedio?dataInicio=2000-01-01&dataFim=${dateToISO(new Date())}`);
            if (response.data) {
                setTicketMedio(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            return Promise.reject("Erro ao buscar clientes");
        }
    };

    const relatoriosTicketMedio = async (dataInicio: Date, dataFim: Date) => {
        setLoading(true);

        try {
            const response = await axiosClient.get(`/relatorios/pdf/ticketMedio?dataInicio=${dateToISO(dataInicio)}&dataFim=${dateToISO(dataFim)}`, {
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

    const ticketPorData = async (dataInicio: Date, dataFim: Date) => {
        setLoading(true);
        try {
            const response = await axiosClient.get(`/relatorios/ticketMedio?dataInicio=${dateToISO(dataInicio)}&dataFim=${dateToISO(dataFim)}`);
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
        ticketPorData,
    };
};
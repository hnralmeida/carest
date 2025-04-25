import { Cliente } from "@/components/pageClienteComponents/columns";
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";


export const useRelatoriosHook = () => {
    const [clientesDiario, setClientesDiario] = useState<Cliente[] | null>(null);

    const listarClientesDiario = async () => {
        setClientesDiario([
            {
                "id": "Carregando...",
                "nome": "Carregando...",
                "codigo": "Carregando...",
                "email": "Carregando...",
                "nascimento": "Carregando...",
                "telefone": "Carregando...",
                "saldo": "Carregando...",
                "limite": "Carregando...",
                "em_uso": false,
                "dividaData": "Carregando..."
            }
        ])
        try {
            const hoje = new Date();
            const response = await axiosClient.get(`/relatorios/diario?mes=${hoje.getMonth()+1}`);
            if (response.data) {
                setClientesDiario(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    return { 
        clientesDiario,
        listarClientesDiario
    };
};
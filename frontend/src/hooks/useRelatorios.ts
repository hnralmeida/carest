import { Cliente } from "@/components/pageClienteComponents/columns";
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";


export const useRelatoriosHook = () => {
    const [clientesDiario, setClientesDiario] = useState<Cliente[] | null>(null);
    const [cliente, setCliente] = useState({} as Cliente);

    const listarClientesDiario = async () => {
        try {
            const response = await axiosClient.get(`/cliente/diario`);
            if (response.data) {
                setClientesDiario(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    async function buscarCliente(codigo: string) {
        const response = await axiosClient.get(`/cliente/codigo/${codigo}`);
        const cliente = response.data;
        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(cliente);
        }
    }

    return {
        clientesDiario,
        listarClientesDiario,
        cliente,
        buscarCliente
    };
};
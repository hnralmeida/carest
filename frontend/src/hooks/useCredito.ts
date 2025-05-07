import { Cliente } from "@/components/pageClienteComponents/columns";
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";


export const useCreditoHook = () => {
    const [cliente, setCliente] = useState({} as Cliente);

    async function buscarCliente(codigo: string) {
        const response = await axiosClient.get(`/cliente/codigo/${codigo}`);
        const cliente = response.data;
        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(cliente);
        }
    }

    async function fazerRecarga(valor: string, codigo: number) {
        const data = {
            valorRecarga: valor,
            codigoCliente: codigo
        }

        console.log(data)
        const response = await axiosClient.put(`/cliente/recarga`, data);
        const clienteRes = response.data;

        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(clienteRes);
        }
    }

        
    return {
        cliente,
        buscarCliente,
        fazerRecarga
    };
};
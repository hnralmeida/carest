import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useCreditoHook = () => {
    const [cliente, setCliente] = useState({} as Cliente);

    async function buscarCliente(codigo: string) {
        const response = await axiosClient.get(`/cliente/codigo/${codigo}`);
        const cliente = response.data;
        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(cliente);
            return cliente;
        }
    }

    async function fazerRecarga(valor: number, codigoCliente: string) {
        const data = {
            valorRecarga: valor,
            codigoCliente: codigoCliente
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
    
    const alterarLimite = async (limite: number, cliente: Cliente) => {
        const data = {
            ...cliente,
            limite: limite
        }

        console.log("alterarLimite", data)
        const response = await axiosClient.put(`/cliente/`+ cliente.id, data);
        const clienteRes = response.data;

        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(clienteRes);
        }
    }

    return {
        cliente,
        alterarLimite,
        buscarCliente,
        fazerRecarga
    };
};
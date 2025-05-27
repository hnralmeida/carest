import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";

export const useAcessoHook = () => {
    const [cliente, setCliente] = useState({} as Cliente);

    async function buscarAcesso(codigo: string) {
        const response = await axiosClient.post(`/acesso/entrada?codigo=${codigo}`);
        const acesso = response.data;

        if (response.status > 205) {
            return Promise.reject(response);
        } else {
            setCliente(acesso.cliente);
            return acesso;
        }
    }

    const saidaCliente = async (codigo: string) => {
        const response = await axiosClient.post(`/acesso/saida?codigo=${codigo}`);
        const saida = response.data;

        if (response.status > 205) {
            return Promise.reject("Erro ao registrar saída");
        } else {
            setCliente(saida.cliente);
            return saida;
        }
    }

    return {
        cliente,
        buscarAcesso,
        saidaCliente
    };
};
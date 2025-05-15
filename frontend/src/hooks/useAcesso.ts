import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";

export const useAcessoHook = () => {
    const [cliente, setCliente] = useState({} as Cliente);

    async function buscarAcesso(codigo: string) {
        const response = await axiosClient.get(`/acesso/codigo/${codigo}`);
        const acesso = response.data;
        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(acesso);
            return acesso;
        }
    }

    return {
        cliente,
        buscarAcesso,
    };
};
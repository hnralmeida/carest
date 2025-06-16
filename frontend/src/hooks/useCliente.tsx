'use client';   

import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useClienteHook = () => {
    const [cliente, setCliente] = useState<Cliente[]>({} as Cliente[]);
    const [loading, setLoading] = useState(true);

    const listarCliente = async () => {
        setLoading(true);
        try {
            const response = await axiosClient.get("/clientes");
            const res = response.data.sort((a: Cliente, b: Cliente) =>
                a.nome.localeCompare(b.nome)
            );
            if (response.data) {
                setCliente(res);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            console.error('Error fetching administrators:', error);
            return Promise.reject("Clientes não encontrados");
        }
    };

    const adicionarCliente = async (data: Cliente) => {
        try {
            const response = await axiosClient.post("/clientes", data);
            if (response.status < 205) {
                window.location.reload();
                return Promise.resolve("Cliente adicionado com sucesso");
            } else {
                return Promise.reject("Erro ao adicionar cliente");
            }
        } catch (error) {
            console.error('Error adding cliente:', error);
            return Promise.reject("Erro ao conectar com o servidor");
        }
    };

    const editCliente = async (data: Cliente, id: string) => {
        try {
            const response = await axiosClient.put("/clientes/" + id, data);
            if (response.status < 205) {
                return Promise.resolve("Cliente adicionado com sucesso");
            } else {
                return Promise.reject("Erro ao adicionar cliente");
            }
        } catch (error) {
            console.error('Error adding cliente:', error);
            return Promise.reject("Erro ao conectar com o servidor");
        }
    };


    return {
        loading,
        cliente,
        listarCliente,
        adicionarCliente,
        editCliente
    };
};
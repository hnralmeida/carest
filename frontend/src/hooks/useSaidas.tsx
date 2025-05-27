import { useState } from "react";
import { Usuario } from "@/models/usuario";
import { axiosClient } from "@/services/axiosClient";
import { Saidas } from "@/models/saidas";
import { Fornecedor } from "@/components/pageFornecedorComponents/columns";

export const useSaidasHook = () => {
    const [saidas, setSaidas] = useState<Saidas[] | null>(null);
    const [fornecedores, setFornecedores] = useState<Fornecedor[] | null>(null);

    const listarSaidas = async () => {
        try {
            const response = await axiosClient.get('/saidas');
            setSaidas(response.data);
        } catch (error) {
            console.error('Error fetching administrators:', error);
            setSaidas([]);
            return Promise.reject((error as Error).message);
        }
    };

    const editarSaidas = async (saidas: Saidas): Promise<Saidas | null> => {
        try {
            const response = await axiosClient.put(`/saidas/${saidas.id}`, saidas);
            if (response.data) {
                setSaidas(response.data);
                return response.data;
            }
        } catch (error) {
            return Promise.reject((error as Error).message);
        }
        return null;
    };

    const deletarSaidas = async (saidasId: string): Promise<void> => {
        try {
            await axiosClient.delete(`/saidas/${saidasId}`);
        } catch (error) {
            return Promise.reject((error as Error).message);
        }
    };

    const criarSaidas = async (saidas: Saidas): Promise<Saidas | null> => {
        try {
            const response = await axiosClient.post('/saidas', saidas);
        } catch (error) {
            console.error('Error creating Saidas:', error);
            return Promise.reject((error as Error).message);
        }
        return null;
    };

    const listarFornecedores = async () => {
        try {
            const response = await axiosClient.get('/fornecedor');
            setFornecedores(response.data);
        } catch (error) {
            console.error('Error fetching fornecedores:', error);
            setFornecedores([]);
            return Promise.reject((error as Error).message);
        }
    };


    return {
        fornecedores,
        listarFornecedores,
        saidas,
        listarSaidas,
        editarSaidas,
        deletarSaidas,
        criarSaidas
    };
};
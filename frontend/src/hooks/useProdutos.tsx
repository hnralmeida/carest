import { useState } from "react";
import { Produto } from "@/app/models/produto";
import { axiosClient } from "@/services/axiosClient";

export const useProdutoHook = () => {
    const [produtos, setProdutos] = useState<Produto[] | null>(null);
    const [produto, setProduto] = useState<Produto | null>(null);

    const listarProdutos = async () => {
        setProdutos([
            {
                "id": "Carregando...",
                "nome": "Carregando...",
                "valor": 0,
                "codigo": 0
            }
        ])
        try {
            const response = await axiosClient.get('/produto');
            if (response.data) {
                setProdutos(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    const selecionarProduto = async (produtoId: string) => {
        try {
            const response = await axiosClient.get(`/produto/${produtoId}`);
            if (response.data) {
                setProduto(response.data);
            }
        } catch (error) {
            console.error('Error fetching Produto:', error);
        }
    };

    const editarProduto = async (produto: Produto): Promise<Produto | null> => {
        try {
            const response = await axiosClient.put(`/produto/${produto.id}`, produto);
            if (response.data) {
                setProduto(response.data);
                return response.data;
            }
        } catch (error) {
            console.error('Error updating Produto:', error);
        }
        return null;
    };

    const deletarProduto = async (produtoId: string): Promise<string | null> => {
        try {
            await axiosClient.delete(`/produto/${produtoId}`);
            return "Sucesso"
        } catch (error) {
            console.error('Error deleting Produto:', error);
            return null;
        }
    };

    const criarProduto = async (produto: Produto): Promise<Produto | null> => {
        try {
            const response = await axiosClient.post('/produto', produto);
        } catch (error) {
            console.error('Error creating Produto:', error);
        }
        return null;
    };

    return { 
        produtos, 
        produto, 
        listarProdutos, 
        selecionarProduto, 
        editarProduto, 
        deletarProduto, 
        criarProduto 
    };
};
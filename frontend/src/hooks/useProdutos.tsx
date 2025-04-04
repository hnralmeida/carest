import { useState } from "react";
import { Produto } from "@/app/models/produto";
import { axiosClient } from "@/services/axiosClient";
import { ProdutoBalanca } from "@/app/models/balanca";
import { ISODateToDate } from "@/lib/utils";

export const useProdutoHook = () => {
    const [produtos, setProdutos] = useState<Produto[] | null>(null);
    const [produto, setProduto] = useState<Produto | null>(null);
    const [balanca, setBalanca] = useState<number>(0);

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
            const response = await axiosClient.get('/produtos/serial');
            if (response.data) {
                setProdutos(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    const selecionarProduto = async (produtoId: string) => {
        try {
            const response = await axiosClient.get(`/produtos/${produtoId}`);
            if (response.data) {
                setProduto(response.data);
            }
        } catch (error) {
            console.error('Error fetching Produto:', error);
        }
    };

    const editarProduto = async (produto: Produto): Promise<Produto | null> => {
        try {
            const response = await axiosClient.put(`/produtos/serial/${produto.id}`, produto);
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
            await axiosClient.delete(`/produtos/serial/${produtoId}`);
            return "Sucesso"
        } catch (error) {
            console.error('Error deleting Produto:', error);
            return null;
        }
    };

    const criarProduto = async (produto: Produto): Promise<Produto | null> => {
        try {
            const response = await axiosClient.post('/produtos/serial', produto);
        } catch (error) {
            console.error('Error creating Produto:', error);
        }
        return null;
    };

    const obterBalanca = async (): Promise<null> => {
        try {
            const response = await axiosClient.get('/produtos/balanca/preco');
            setBalanca(response.data.valor)
        } catch (error) {
            console.error('Error creating Produto:', error);
        }
        return null;
    }

    const atualizarBalanca = async (valor: number): Promise<null> => {
        
        const data = {
            "valor": valor,
            "data": ISODateToDate(new Date)
        }

        try {
            const response = await axiosClient.post('/produtos/balanca', data);
            setBalanca(response.data.valor)
        } catch (error: any) {
            return Promise.reject(error?.response?.data?.message || error.message || "Erro ao salvar balança");
        }
        return Promise.reject("Não foi possível atualizar o valor");
    }

    return {
        produtos,
        produto,
        balanca,
        atualizarBalanca,
        obterBalanca,
        listarProdutos,
        selecionarProduto,
        editarProduto,
        deletarProduto,
        criarProduto
    };
};
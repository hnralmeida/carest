"use client";

import React, { useState } from "react";
import { Produto } from "@/models/produto";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";
import { ProdutoBalanca } from "@/models/balanca";

interface VendaItem {
    produto: Produto | ProdutoBalanca;
    quantidade: number;
    valor: number;
}

export const useVendasHook = () => {
    const [produtos, setProdutos] = React.useState<VendaItem[]>([]);
    const [cliente, setCliente] = React.useState({} as Cliente);

    async function inserirBalanca(peso: string) {
        try {
            const response = await axiosClient.get('/produtos/balanca/preco');
            const data = response.data as ProdutoBalanca;
            const novoValor = data.valor * Number(peso);

            setProdutos(prev => {
                return [
                    ...prev,
                    {
                        produto: data,
                        quantidade: 1,
                        valor: Number(novoValor.toFixed(2)),
                    }
                ];
            });
        } catch (error) {
            console.error('Error creating Produto:', error);
        }
    }

    function isProduto(produto: Produto | ProdutoBalanca): produto is Produto {
        return 'codigo' in produto;
    }

    async function buscarProduto(codigo: string): Promise<void> {
        try {
            const response = await axiosClient.get(`/produtos/serial/codigo/${codigo}`);

            if (response.status > 205 || !response.data) {
                return Promise.reject("Produto não encontrado");
            }

            const produto = response.data as Produto; // ou Produto | ProdutoBalanca

            setProdutos(prev => {
                const index = prev.findIndex(item => {
                    // Só compara se o item for um Produto com código
                    return isProduto(item.produto) && item.produto.codigo === produto.codigo;
                });

                if (index !== -1) {
                    // Produto já existe, atualiza a quantidade e o valor total (opcional)
                    const updated = [...prev];
                    const itemExistente = updated[index];

                    updated[index] = {
                        ...itemExistente,
                        quantidade: itemExistente.quantidade + 1,
                        valor: Number((produto.valor * (itemExistente.quantidade + 1)).toFixed(2)),
                    };

                    return updated;
                } else {
                    // Produto novo, adiciona ao carrinho
                    return [
                        ...prev,
                        {
                            produto,
                            quantidade: 1,
                            valor: Number(produto.valor.toFixed(2))
                        }
                    ];
                }
            });
        } catch (error) {
            return Promise.reject(error);
        }
    }

    async function buscarCliente(codigo: string) {
        try {
            const response = await axiosClient.get(`/clientes/codigo/${codigo}`);
            setCliente(response.data);
        } catch (error) {
            // Aqui, lançamos o erro original para poder tratá-lo no nível superior
            return Promise.reject(error);
        }
    }

    const resetarVenda = () => {
        setProdutos([]);
        setCliente({} as Cliente);
    }

    const registrarVenda = async () => {
        const body = {
            clienteId: cliente.id,
            itens: produtos.map(item => ({
                produtoId: item.produto.id,
                quantidade: item.quantidade,
                preco_unitario: item.valor
            }))
        }
        console.log(body);

        const response = await axiosClient.post("/vendas", body);
        if (response.status > 205) {
            return Promise.reject("Erro ao registrar venda");
        } else {
            setProdutos([]);
            setCliente({} as Cliente);
        }
    }

    return {
        produtos,
        cliente,
        buscarProduto,
        buscarCliente,
        resetarVenda,
        registrarVenda,
        inserirBalanca
    };
};
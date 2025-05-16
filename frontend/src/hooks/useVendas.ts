"use client";

import React, { useState } from "react";
import { Produto } from "@/models/produto";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";

interface VendaItem {
    produto: Produto;
    quantidade: number;
    valor: number;
}

export const useVendasHook = () => {
    const [produtos, setProdutos] = React.useState<VendaItem[]>([]);
    const [cliente, setCliente] = React.useState({} as Cliente);

    async function buscarProduto(codigo: string): Promise<void> {
        const response = await axiosClient.get(`/produtos/serial/codigo/${codigo}`);
        const produto = response.data;

        if (response.status > 205) {
            return Promise.reject("Produto não encontrado");
        }

        setProdutos(prev => {
            const index = prev.findIndex(item => item.produto.codigo === produto.codigo);

            if (index !== -1) {
                // Produto já existe, atualiza a quantidade
                const updated = [...prev];
                updated[index] = {
                    ...updated[index],
                    quantidade: updated[index].quantidade + 1
                };
                return updated;
            } else {
                // Produto novo, adiciona ao carrinho
                return [
                    ...prev,
                    {
                        produto,
                        quantidade: 1,
                        valor: produto.valor
                    }
                ];
            }
        });
    }

    async function buscarCliente(codigo: string) {
        const response = await axiosClient.get(`/clientes/codigo/${codigo}`);
        const cliente = response.data;
        if (response.status > 205) {
            return Promise.reject("Cliente não encontrado");
        } else {
            setCliente(cliente);
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
                quantidade: item.quantidade
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
        registrarVenda
    };
};
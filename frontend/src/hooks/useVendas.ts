"use client";

import React, { useState } from "react";
import { Cliente } from "@/components/pageClienteComponents/columns";
import { Produto } from "@/app/models/produto";

export const useVendasHook = () => {
    const [produtos, setProdutos] = React.useState<Produto[]>([]);
    const [cliente, setCliente] = React.useState({} as Cliente);

    function buscarProduto(codigo: string) {
        const novoProduto = {
          id: "1",
          nome: "Produto 1",
          codigo: 123,
          valor: 10.00,
        };
      
        setProdutos(prev => [...prev, novoProduto]);
      }

    function buscarCliente(codigo: string) {
        setCliente ({
            id: "1",
            nome: "Cliente 1",
            saldo: "100.00",
            telefone: "123456789",
            dividaData: "2023-10-01",
            em_uso: true,
            nascimento: "1990-01-01",
            limite: "500.00",
            email: "email@email.com"
        });
    }

    const resetarVenda = () => {
        setProdutos([]);
        setCliente({} as Cliente);
    }

    return {
        produtos,
        cliente,
        buscarProduto,
        buscarCliente,
        resetarVenda
    };
};
"use client";

import React, { useState } from "react";
import { Button } from "../ui/button";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import { Label } from "../ui/label";
import { Input } from "../ui/input";
import { Toaster } from "sonner";
import { useCreditoHook } from "@/hooks/useCredito";
import { Cliente } from "@/models/cliente";

interface OptionButtonSaldoProps {
    cliente: Cliente;
}
const OptionButtonSaldo = ({cliente}: OptionButtonSaldoProps) => {
    const [loading, setLoading] = useState(false);
    const [open, setOpen] = useState(false);
    const [valor, setValor] = useState(0);

    const { fazerRecarga } = useCreditoHook(); // Importando o hook de crédito

    const formatarParaBRL = (valor: number) => {
        return new Intl.NumberFormat("pt-BR", {
            style: "currency",
            currency: "BRL",
        }).format(valor);
    };

    async function onFormSubmit(event: React.FormEvent) {
        event.preventDefault(); // Adicionado para evitar reload  
        setLoading(true);
        console.log("cliente: ", cliente);
        
        await fazerRecarga(valor, cliente.codigo); // Chama a função de recarga com o valor formatado

        setTimeout(() => {
            setValor(0); // Limpa o valor após o envio
            setLoading(false);
            setOpen(false);
            window.location.reload(); // Recarrega a página após 2 segundos
        }, 2000);
    }

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button
                    className="button-alt items-center text-35px w-[180px]"
                    onClick={() => setOpen(true)}
                    disabled={cliente.id==undefined}
                >
                    Adicionar Saldo
                </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Adicionar Saldo</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <div>
                        <Label htmlFor="valor">Valor a ser adicionado</Label>
                        <Input
                            id="valor"
                            value={formatarParaBRL(Number(valor))}
                            onChange={(e) => {
                                const entrada = e.target.value.replace(/\D/g, ""); // Remove tudo que não for número
                                const numero = parseFloat(entrada) / 100; // Divide por 100 para simular centavos
                                setValor(numero);
                            }}
                            placeholder="Digite o Valor"
                            autoComplete="off"
                            required
                        />
                    </div>

                    <Button type="submit" className="mt-4" disabled={loading}>
                        {loading ? "Carregando..." : "Adicionar Saldo"}
                    </Button>
                </form>
                <Toaster position="bottom-center" richColors />
            </DialogContent>
        </Dialog>
    );
};

export default OptionButtonSaldo;
"use client"

import { dateToReadable } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export type Cliente = {
    id: string;
    nome: string;
    saldo: string;
    telefone: string;
    email: string;
    codigo: string;
    limite: string;
    em_uso: boolean;
    nascimento: string;
    dividaData: string;
}

export const columns: ColumnDef<Cliente>[] = [
    {
        accessorKey: 'nome',
        header: 'Nome Completo',
    },
    {
        accessorKey: 'telefone',
        header: 'Telefone',
    },
    {
        accessorKey: 'email',
        header: 'Email',
    },
    {
        accessorKey: 'codigo',
        header: 'Código',
    },
    {
        accessorKey: 'nascimento',
        header: 'Data de Nascimento',
        cell: ({row}) =>{
            const date = new Date(row.getValue('nascimento') as string)
            const formated = dateToReadable(date)
            return <div className='text-right'>{formated}</div>
        },
    },
]
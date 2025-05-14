"use client"

import { dateToReadable } from "@/lib/utils";
import { Cliente } from "@/models/cliente";
import { ColumnDef } from "@tanstack/react-table";

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
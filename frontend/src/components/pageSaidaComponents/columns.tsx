"use client"

import { dateToReadable, formatarParaMoeda } from "@/lib/utils";
import { Saidas } from "@/models/saidas";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Saidas>[] = [
    {
        accessorKey: 'descricao',
        header: 'Descrição',
    },
    {
        accessorFn: (row) => row.fornecedor.nome, // acessa diretamente o nome
        id: "fornecedor",
        header: "Fornecedor",
        cell: ({ row }) => {
            const nome = row.getValue("fornecedor") as string;
            return <div className="text-right">{nome}</div>;
        },
    },
    {
        accessorKey: "valor",
        header: "Valor",
        cell: ({ row }) => {
            const formated = formatarParaMoeda(`${row.getValue('valor')}`, true)
            return <div className='text-right'>{formated}</div>
        },
    },
    {
        accessorKey: 'dataPagamento',
        header: 'Data de Pagamento',
        cell: ({ row }) => {
            const date = new Date(row.getValue('dataPagamento') as string)
            const formated = dateToReadable(date)
            return <div className='text-right'>{formated}</div>
        },
    }, {
        accessorKey: 'dataVencimento',
        header: 'Data de Vencimento',
        cell: ({ row }) => {
            const date = new Date(row.getValue('dataVencimento') as string)
            const formated = dateToReadable(date)
            return <div className='text-right'>{formated}</div>
        },
    },
]
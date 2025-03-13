"use client"

import { ColumnDef } from "@tanstack/react-table";

export type Fornecedor = {
    id: string;
    descricao: string;
}

export const columns: ColumnDef<Fornecedor>[] = [
{
        accessorKey: 'descricao',
        header: 'Descrição',
    }
]
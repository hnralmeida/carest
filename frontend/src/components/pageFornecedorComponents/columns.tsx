"use client"

import { ColumnDef } from "@tanstack/react-table";

export type Fornecedor = {
    id: string;
    nome: string;
}

export const columns: ColumnDef<Fornecedor>[] = [
{
        accessorKey: 'nome',
        header: 'Descrição',
    }
]
"use client";

import { Produto } from "@/models/produto";
import { formatarParaMoeda } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Produto>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "valor",
    header: "Valor",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('valor')}`)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "custo",
    header: "Custo",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('custo')}`)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "codigo",
    header: "Codigo",
  }
];

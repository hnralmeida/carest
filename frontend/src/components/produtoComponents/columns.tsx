"use client";

import { Produto } from "@/app/models/produto";
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
    accessorKey: "codigo",
    header: "Codigo",
  }
];

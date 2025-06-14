"use client";

import { formatarParaMoeda } from "@/lib/utils";
import { RelatorioProduto } from "@/models/relatorioProduto";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<RelatorioProduto>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "codigo",
    header: "Código",
  },
  {
    accessorKey: "quantidade",
    header: "Quantidade",
  },
  {
    accessorKey: "valor",
    header: "Valor Bruto Total",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${Number(row.getValue('valor')) * Number(row.getValue('quantidade'))}`, true)
      return <div className='text-right'>{formated}</div>
    },
  }

];

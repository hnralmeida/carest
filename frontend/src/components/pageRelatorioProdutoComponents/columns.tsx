"use client";

import { formatarParaMoeda } from "@/lib/utils";
import { RelatorioProduto } from "@/models/relatorioProduto";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<RelatorioProduto>[] = [
  {
    accessorKey: "nomeProduto",
    header: "Nome",
  },
  {
    accessorKey: "codigoProduto",
    header: "Código",
  },
  {
    accessorKey: "quantidadeVendida",
    header: "Quantidade",
  },
  {
    accessorKey: "valorUnitario",
    header: "Valor Bruto Total",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${Number(row.getValue('valorUnitario')) * Number(row.getValue('quantidadeVendida'))}`, true)
      return <div className='text-right'>{formated}</div>
    },
  }

];

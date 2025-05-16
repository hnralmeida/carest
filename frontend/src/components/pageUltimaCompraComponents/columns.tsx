"use client";

import { Cliente } from "@/models/cliente";
import { dateToReadable, formatarParaMoeda } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "clienteNome",
    header: "Nome",
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
    accessorKey: 'dataVenda',
    header: 'Última Compra',
    cell: ({ row }) => {
      const date = new Date(row.getValue('dataVenda') as string)
      const formated = dateToReadable(date)
      return <div className='text-right'>{formated}</div>
    },
  },


];

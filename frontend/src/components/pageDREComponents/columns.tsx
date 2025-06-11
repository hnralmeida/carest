"use client";

import { dateToReadable, formatarParaMoeda } from "@/lib/utils";
import { DRE } from "@/models/dre";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<DRE>[] = [
  {
    accessorKey: 'data',
    header: 'Data',
    cell: ({ row }) => {
      const date = new Date(row.getValue('data') as string)
      const formated = dateToReadable(date)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "entrada",
    header: "Entrada",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('entrada')}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "saida",
    header: "Saída",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('saida')}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "resultado",
    header: "Resultado",
    cell: ({ row }) => {
      const entrada = row.getValue('entrada') as number
      const saida = row.getValue('saida') as number
      const saldo = entrada - saida
      const formated = formatarParaMoeda(`${saldo}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "saldoAnterior",
    header: "Saldo",
    cell: ({ row }) => {
      const entrada = row.getValue('entrada') as number
      const saida = row.getValue('saida') as number
      const saldoAnterior = row.getValue('saldoAnterior') as number
      const saldo = entrada - saida + saldoAnterior
      const formated = formatarParaMoeda(`${saldo}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  {
    accessorKey: "clientes",
    header: "Clientes",
  }

];

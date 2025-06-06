"use client";

import { dateToReadable } from "@/lib/utils";
import { Cliente } from "@/models/cliente";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "nascimento",
    header: "Data",
    cell: ({ row }) => {
      const date = new Date(row.getValue('nascimento') as string)
      const formated = dateToReadable(date)
      return <div className='text-right'>{formated}</div>
    },
  }
];

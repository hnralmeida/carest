"use client";

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
      const dia = String(date.getDate()).padStart(2, '0'); // getDate() pega o dia do mês
      const mes = String(date.getMonth() + 1).padStart(2, '0'); // getMonth() começa do 0, então soma 1
      const formated = `${dia}/${mes}`;
      return <div className='text-right'>{formated}</div>
    },
  }
];

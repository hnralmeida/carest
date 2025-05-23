"use client";

import { formatarParaMoeda } from "@/lib/utils";
import { Cliente } from "@/models/cliente";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "saldo",
    header: "Valor da Dívida",
    cell: ({ row }) => {
          const formated = formatarParaMoeda(`${row.getValue('saldo')}`, true)
          return <div className='text-right'>{formated}</div>
        },
  }

];

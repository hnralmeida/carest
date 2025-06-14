"use client";

import { Cliente } from "@/models/cliente";
import { formatarParaMoeda } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "cliente",
    header: "Nome",
  },
  {
    accessorKey: "ticket_medio",
    header: "Gasto",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('ticket_medio')}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  

];

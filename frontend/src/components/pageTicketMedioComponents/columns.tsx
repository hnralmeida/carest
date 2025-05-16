"use client";

import { Cliente } from "@/models/cliente";
import { formatarParaMoeda } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "cliente.nome",
    header: "Nome",
  },
  {
    accessorKey: "valorMedio",
    header: "Gasto",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('valorMedio')}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  

];

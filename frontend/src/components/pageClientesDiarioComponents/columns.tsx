"use client";

import { Cliente } from "@/models/cliente";
import { formatarParaMoeda } from "@/lib/utils";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "horaVenda",
    header: "Horário",
  },
  {
    accessorKey: "valorTotal",
    header: "Gasto",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('valorTotal')}`)
      return <div className='text-right'>{formated}</div>
    },
  },
  

];

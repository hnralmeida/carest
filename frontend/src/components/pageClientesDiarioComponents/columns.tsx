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
    accessorKey: "horario",
    header: "Horário",
  },
  {
    accessorKey: "valor",
    header: "Gasto",
    cell: ({ row }) => {
      const formated = formatarParaMoeda(`${row.getValue('valor')}`, true)
      return <div className='text-right'>{formated}</div>
    },
  },
  

];

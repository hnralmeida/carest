"use client";

import { Produto } from "@/app/models/balanca";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Produto>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "valor",
    header: "Valor",
  },
  {
    accessorKey: "codigo",
    header: "Codigo",
  }
];

"use client";

import { Tela } from "@/app/models/tela";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Tela>[] = [
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

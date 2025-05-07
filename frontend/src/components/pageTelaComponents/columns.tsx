"use client";

import { Tela } from "@/models/tela";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Tela>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "rota",
    header: "Rota",
  }
];

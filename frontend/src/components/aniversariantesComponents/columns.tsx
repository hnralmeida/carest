"use client";

import { Cliente } from "@/app/models/cliente";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Cliente>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "nascimento",
    header: "Data",
  }
];

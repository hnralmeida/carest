"use client";

import { Usuario } from "@/models/usuario";
import { ColumnDef } from "@tanstack/react-table";

export const columns: ColumnDef<Usuario>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "email",
    header: "Email",
  },
];

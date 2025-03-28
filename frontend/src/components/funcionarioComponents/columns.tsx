"use client";

import { Usuario } from "@/app/models/usuario";
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

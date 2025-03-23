"use client";

import { ColumnDef } from "@tanstack/react-table";

export type Funcionario = {
  id: string;
  nome: string;
  email: string;
};

export const columns: ColumnDef<Funcionario>[] = [
  {
    accessorKey: "nome",
    header: "Nome",
  },
  {
    accessorKey: "email",
    header: "Email",
  },
];

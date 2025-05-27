"use client";

import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  getPaginationRowModel,
  useReactTable,
} from "@tanstack/react-table";

import {
  Table,
  TableBody,
  TableCell,
  TableFooter,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import { Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import EditSaida from "./editSaida";
import { axiosClient } from "@/services/axiosClient";
import { Saidas } from "../../models/saidas";
import FooterPagination from "../footerPagination";

interface DataTableProps<TValue> {
  columns: ColumnDef<Saidas, TValue>[];
  data: Saidas[];
}

export function DataTable<TValue>({
  columns,
  data,
}: DataTableProps<TValue>) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
  });

  function onDelete(id: string) {
    const confirmDelete = window.confirm(
      "Tem certeza que deseja excluir este Saida?"
    );

    if (confirmDelete) {
      try {
        axiosClient
          .delete(`/Saida/${id}`)
          .then(() => {
            alert("Saida excluído com sucesso!");
            window.location.reload(); // Atualiza a lista após excluir
          })
          .catch((error) => {
            console.error("Erro ao excluir:", error);
            alert("Falha ao excluir o Saida.");
          });
      } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro ao conectar com o servidor.");
      }
    }
  }

  return (
    <div className="rounded-md border overflow-x-auto">
      <Table>
        <TableHeader className="bg-[var(--primary-color)]">
          {table.getHeaderGroups().map((headerGroup) => (
            <TableRow key={headerGroup.id} className="flex w-full">
              {headerGroup.headers.map((column) => (
                <TableHead
                  key={column.id}
                  className="flex flex-1 text-[var(--white-color)] justify-center items-center"
                >
                  {column.isPlaceholder
                    ? null
                    : flexRender(
                      column.column.columnDef.header,
                      column.getContext()
                    )}
                </TableHead>
              ))}
              <TableHead className="w-[96px]" />
            </TableRow>
          ))}
        </TableHeader>
        <TableBody>
          {table.getRowModel().rows.length ? (
            table.getRowModel().rows.map((row) => (
              <TableRow
                key={row.id}
                data-state={row.getIsSelected() && "selected"}
                className="flex w-full "
              >
                {row.getVisibleCells().map((cell) => (
                  <TableCell
                    key={cell.id}
                    className="flex w-full  justify-center"
                  >
                    {flexRender(cell.column.columnDef.cell, cell.getContext())}
                  </TableCell>
                ))}
                <TableCell className="flex w-32 gap-2">
                  <EditSaida
                    id={row.original.id}
                    item={row.original}
                  />
                  <Button
                    className="button-table"
                    variant="outline"
                    size="icon"
                    onClick={() => onDelete(row.original.id)}
                  >
                    <Trash2 size={24} />
                  </Button>
                </TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={columns.length} className="text-center">
                Sem resultados.
              </TableCell>
            </TableRow>
          )}
        </TableBody>
        <FooterPagination
          table={table}
        />
      </Table>
    </div>
  );
}

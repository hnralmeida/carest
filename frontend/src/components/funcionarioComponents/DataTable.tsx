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
import { axiosClient } from "@/services/axiosClient";
import EditFuncionario from "./editFuncionario";

interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[];
  data: TData[];
}

export function DataTable<TData, TValue>({
  columns,
  data,
}: DataTableProps<TData, TValue>) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
  });

  function onDelete(id: string) {
    const confirmDelete = window.confirm(
      "Tem certeza que deseja excluir este funcionario?"
    );

    if (confirmDelete) {
      try {
        axiosClient
          .delete(`/funcionario/${id}`)
          .then(() => {
            alert("Funcionário excluído com sucesso!");
            window.location.reload(); // Atualiza a lista após excluir
          })
          .catch((error) => {
            console.error("Erro ao excluir:", error);
            alert("Falha ao excluir o funcionario.");
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
                  <EditFuncionario
                    id={row.original.id}
                    nome={row.original.nome}
                    email={row.original.email}
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
        <TableFooter className="flex justify-end gap-2 p-4">
          {Array.from({ length: table.getPageCount() }, (_, i) => (
            <button
              key={i}
              className={`px-3 py-1 border rounded cursor-pointer btn-hover-scale hover:bg-[var(--secondary-color)] hover:text-[var(--white-color)] ${
                table.getState().pagination.pageIndex === i
                  ? "bg-[var(--primary-color)] text-[var(--white-color)]"
                  : ""
              }`}
              onClick={() => table.setPageIndex(i)}
            >
              {i + 1}
            </button>
          ))}
        </TableFooter>
      </Table>
    </div>
  );
}

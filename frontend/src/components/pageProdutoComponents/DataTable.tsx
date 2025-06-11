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

import { BarChart, Barcode, Printer, Text, Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import EditProduto from "./editProduto";
import { useProdutoHook } from "@/hooks/useProdutos";
import { Produto } from "@/models/produto";
import FooterPagination from "../footerPagination";

interface DataTableProps<TValue> {
  columns: ColumnDef<Produto, TValue>[];
  data: Produto[];
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

  const { deletarProduto } = useProdutoHook();

  async function onDelete(id: string) {
    const confirmDelete = window.confirm(
      "Tem certeza que deseja excluir este produto?"
    );

    if (confirmDelete) {
      try {

        const response = await deletarProduto(id);

        if (response) {
          window.location.reload(); // Atualiza a lista após excluir
          toast.success("Produto excluido com sucesso!");
        } else {
          toast.error("Falha ao excluir o Produto.");
        }
      } catch (error) {
        console.error("Erro na requisição:", error);
        toast.error("Erro ao conectar com o servidor.");
      }
    }
  }

  async function onPrint(produto: Produto) {
    try {
      toast(`${produto.nome}`, {
        description: `${produto.codigo}`,
        action: {
          label: "Imprimir",
          onClick: async () => {
            const quantidadeStr = prompt("Informe a quantidade a imprimir:");
            const quantidade = parseInt(quantidadeStr || "0", 10);

            if (isNaN(quantidade) || quantidade <= 0) {
              toast.error("Quantidade inválida.");
              return;
            }

            await fetch(`/api/impressora?code=${produto.codigo}&quantidade=${quantidade}`);
            toast.success("Impressão enviada.");
          },
        },
      });
    } catch (error) {
      toast.error(error instanceof Error ? error.message : "Erro ao imprimir");
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
                <TableCell className="flex w-48 gap-2">
                  <Button
                    className="button-table"
                    variant="outline"
                    size="icon"
                    onClick={() => onPrint(row.original)}
                  >
                    <Barcode size={24} />
                  </Button>
                  <EditProduto
                    id={row.original.id}
                    nome={row.original.nome}
                    valor={row.original.valor}
                    custo={row.original.custo}
                    codigo={row.original.codigo}
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
        <FooterPagination table={table} />
      </Table>
    </div>
  );
}

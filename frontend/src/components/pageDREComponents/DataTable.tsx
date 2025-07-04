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
import FooterPagination from "../footerPagination";
import { DRE } from "@/models/dre";

interface DataTableProps<TValue> {
  columns: ColumnDef<DRE, TValue>[];
  data: DRE[];
}

export function DataTable<DRE>({
  columns,
  data,
}: DataTableProps<DRE>) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
  });

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
            </TableRow>
          ))}
        </TableHeader>
        <TableBody>
          {table.getRowModel().rows.length ? (
            table.getRowModel().rows.map((row) => {
              const dataStr = row.getValue('data') as string;
              const date = new Date(dataStr);
              const hoje = new Date();
              hoje.setHours(0, 0, 0, 0);
              date.setHours(0, 0, 0, 0);
              const isHoje = date.getTime() === hoje.getTime();

              return (
                <TableRow
                  key={row.id}
                  data-state={row.getIsSelected() && "selected"}
                  className={`flex w-full ${isHoje ? "bg-yellow-100" : ""}`}
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell
                      key={cell.id}
                      className="flex w-full justify-center"
                    >
                      {flexRender(cell.column.columnDef.cell, cell.getContext())}
                    </TableCell>
                  ))}
                </TableRow>
              );
            })
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

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

interface DataTableProps<Cliente, TValue> {
  columns: ColumnDef<Cliente, TValue>[];
  data: Cliente[];
}

export function DataTable<Cliente, TValue>({
  columns,
  data,
}: DataTableProps<Cliente, TValue>) {
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
        <TableFooter>
          {Array.from({ length: table.getPageCount() }, (_, i) => (
            <tr key={i}>
              <td>
                <button
                  className={`px-3 py-1 border rounded cursor-pointer btn-hover-scale hover:bg-[var(--secondary-color)] hover:text-[var(--white-color)] ${table.getState().pagination.pageIndex === i
                      ? "bg-[var(--primary-color)] text-[var(--white-color)]"
                      : ""
                    }`}
                  onClick={() => table.setPageIndex(i)}
                >
                  {i + 1}
                </button>
              </td>
            </tr>
          ))}
        </TableFooter>

      </Table>
    </div>
  );
}

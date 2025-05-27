import { TableFooter } from "./ui/table";

interface FooterPaginationProps {
    table: any; // Substitua 'any' pelo tipo real da instância da tabela
}

const FooterPagination = ({ table }: FooterPaginationProps) => {
    return (
        <TableFooter className="flex flex-row justify-end gap-2 p-4">
            <tr>
                <td>
                    <div className="flex flex-row justify-end gap-2 p-4 w-full">
                        {Array.from({ length: table.getPageCount() }, (_, i) => (
                            <button
                                key={i}
                                className={`px-3 py-1 border rounded cursor-pointer btn-hover-scale hover:bg-[var(--secondary-color)] hover:text-[var(--white-color)] ${table.getState().pagination.pageIndex === i
                                    ? "bg-[var(--primary-color)] text-[var(--white-color)]"
                                    : ""
                                    }`}
                                onClick={() => table.setPageIndex(i)}
                            >
                                {i + 1}
                            </button>
                        ))}
                    </div>
                </td>
            </tr>
        </TableFooter >
    );
};

export default FooterPagination;

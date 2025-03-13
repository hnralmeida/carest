"use client"

import { ColumnDef } from "@tanstack/react-table";

export type Fornecedor = {
    id: string;
    amount: number;
    status: 'pending' | 'paid';
    email: string;
}

export const columns: ColumnDef<Fornecedor>[] = [
    {
        accessorKey: 'status',
        header: 'Status',
    },{
        accessorKey: 'email',
        header: 'Email',
    },
    {
        accessorKey: 'amount',
        header: () => <div className='text-right'>Amount</div>,
        cell: ({row}) =>{
            const amount = parseFloat(row.getValue('amount'))
            const formated = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
            }).format(amount)
            return <div className='text-right'>{formated}</div>
        },
    }
]
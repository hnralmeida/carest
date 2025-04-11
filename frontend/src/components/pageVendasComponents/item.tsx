import { formatarParaMoeda } from "@/lib/utils";

interface ItemProps {
    id: string;
    nome: string;
    preco: number;
    quantidade: number;
}

export const ItemVenda = ({ id, nome, preco, quantidade }: ItemProps) => {
    return (
        <div className="flex flex-row justify-between items-center w-full gap-[16px]">
            <p className="flex flex-col justify-center items-center w-[164px] h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]">{quantidade}</p>
            <p className="flex flex-col justify-center items-center w-full h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]">{nome}</p>
            <p className="flex flex-col justify-center items-center w-full h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]">{formatarParaMoeda(String(preco))}</p>
        </div>
    );
}
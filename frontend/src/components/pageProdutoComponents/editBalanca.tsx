import { Barcode, Pencil } from "lucide-react";
import { Button } from "../ui/button";
import { useEffect, useState } from "react";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import { Label } from "@/components/ui/label";
import { Input } from "../ui/input";
import { useProdutoHook } from "@/hooks/useProdutos";
import { toast } from "sonner";
import { formatarParaMoeda, moedaParaNumero } from "@/lib/utils";
import { Produto } from "@/models/produto";

interface EditBalancaProps {
    preco: number;
}

export default function ButtonEditBalanca({ preco }: EditBalancaProps) {
    const [open, setOpen] = useState(false);
    const [valor, setValor] = useState("");
    const { atualizarBalanca } = useProdutoHook();

    const onFormSubmit = async () => {
        try {
            await atualizarBalanca(Number(moedaParaNumero(valor).toFixed(2)));
            toast.success("Preço Atualizado")
        } catch (e: any) {
            toast.error(e);
        }
    }

    useEffect(() => {
        setValor(formatarParaMoeda(preco.toString(), true));
    }, [open])

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const valorFormatado = formatarParaMoeda(e.target.value);
        setValor(valorFormatado);
    };

    async function onPrint() {
        try {
            // Código da balanca vai ser 10004800
            fetch(`/api/impressora?code=10004800`)
        } catch (error) {
            toast.error(error instanceof Error ? error.message : "Erro ao imprimir");
        }
    }

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button
                    className="button-table w-[48px] h-[48px]"
                    variant="outline"
                    size="lg"
                    onClick={() => setOpen(true)}
                >
                    <Pencil size={24} />
                </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Editar Produto Balança</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <div>
                        <Label htmlFor="valor">Preço da Balança</Label>
                        <div className="flex flex-row gap-2 mt-2">
                            <Input
                                id="valor"
                                value={valor}
                                onChange={handleChange}
                                placeholder="R$ 0,00"
                                autoFocus={false}
                                required
                            />

                            <Button
                                className="button-table"
                                variant="outline"
                                size="icon"
                                onClick={() => onPrint()}
                            >
                                <Barcode size={24} />
                            </Button>
                        </div>
                    </div>

                    <Button type="submit" className="mt-4">
                        Salvar
                    </Button>
                </form>
            </DialogContent>
        </Dialog>
    )
}
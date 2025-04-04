import { Pencil } from "lucide-react";
import { Button } from "./ui/button";
import { useEffect, useState } from "react";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import { Label } from "@/components/ui/label";
import { Input } from "./ui/input";
import { useProdutoHook } from "@/hooks/useProdutos";
import { toast } from "sonner";

interface EditBalancaProps {
    preco: number;
}

export default function ButtonEditBalanca({ preco }: EditBalancaProps) {
    const [open, setOpen] = useState(false);
    const [valor, setValor] = useState<number>(0);
    const { atualizarBalanca } = useProdutoHook();

    const onFormSubmit = async () => {
        try {
            await atualizarBalanca(valor);
            toast.success("Preço Atualizado")
        } catch (e: any) {
            toast.error(e);
        }
    }

    useEffect(() => {
        setValor(preco)
    }, [])

    const formatarMoeda = (valorDigitado: string) => {
        // Remove tudo que não for número
        const numero = valorDigitado.replace(/\D/g, "");

        // Converte para float com 2 casas
        const valorFloat = (Number(numero) / 100).toFixed(2);

        // Formata como moeda BRL
        return new Intl.NumberFormat("pt-BR", {
            style: "currency",
            currency: "BRL",
        }).format(Number(valorFloat));
    };

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
                        <Input
                            id="valor"
                            value={valor}
                            onChange={(e) => setValor(Number(e.target.value))}
                            placeholder="Digite o nome do Produto"
                            autoFocus={false}
                            required
                        />
                    </div>

                    <Button type="submit" className="mt-4">
                        Salvar
                    </Button>
                </form>
            </DialogContent>
        </Dialog>
    )
}
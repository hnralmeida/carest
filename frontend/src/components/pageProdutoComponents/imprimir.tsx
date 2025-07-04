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
import { toast } from "sonner";
import { Produto } from "@/models/produto";

interface ImprimirProps {
    produto: Produto;
}

export default function ButtonPrint({ produto }: ImprimirProps) {
    const [open, setOpen] = useState(false);
    const [valor, setValor] = useState("");

    const handleClick = async () => {
        try {

            const quantidade = parseInt(valor || "0", 10);

            if (isNaN(quantidade) || quantidade <= 0) {
                toast.error("Quantidade inválida.");
                return;
            }

            await fetch(`/api/impressora?code=${produto.codigo}&quantidade=${quantidade}`);
            toast.success("Impressão enviada.");
        } catch (error) {
            toast.error(error instanceof Error ? error.message : "Erro ao imprimir");
        }
        setOpen(false);
    }

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button
                    className="button-table"
                    variant="outline"
                    size="icon"
                >
                    <Barcode size={24} />
                </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Quantidade de etiquetas</DialogTitle>
                </DialogHeader>
                <div>
                    <div className="flex flex-row gap-2 mt-2">
                        <Input
                            id="valor"
                            value={valor}
                            onChange={(e) => { setValor(e.target.value) }}
                            placeholder="0"
                            autoFocus={false}
                            required
                        />
                    </div>
                </div>

                <Button onClick={handleClick} className="mt-4">
                    Imprimir
                </Button>
            </DialogContent>
        </Dialog>
    )
}
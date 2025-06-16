import React, { useState } from "react";
import { Button } from "../ui/button";
import { PlusCircle } from "lucide-react";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import { Label } from "../ui/label";
import { Input } from "../ui/input";
import { Toaster } from "sonner";
import { Cliente } from "@/models/cliente";
import { useCreditoHook } from "@/hooks/useCredito";
import { formatarParaMoeda, moedaParaNumero } from "@/lib/utils";

interface OptionButtonLimiteProps {
    cliente: Cliente;
}

const OptionButtonLimite = ({ cliente }: OptionButtonLimiteProps) => {

    const [loading, setLoading] = useState(false);
    const [open, setOpen] = useState(false);
    const [valor, setValor] = useState("");

    const { alterarLimite } = useCreditoHook(); // Importando o hook de crédito

    React.useEffect(() => {
        setValor(formatarParaMoeda(valor.toString(), true));
    }, []);

    const formatarParaBRL = (valor: number) => {
        return new Intl.NumberFormat("pt-BR", {
            style: "currency",
            currency: "BRL",
        }).format(valor);
    };

    const handleChangeValor = (e: React.ChangeEvent<HTMLInputElement>) => {
        const valorFormatado = formatarParaMoeda(e.target.value);
        setValor(valorFormatado);
    };

    async function onFormSubmit(event: React.FormEvent) {
        event.preventDefault(); // Adicionado para evitar reload
        setLoading(true);

        await alterarLimite(Number(moedaParaNumero(valor).toFixed(2)), cliente); // Chama a função de alteração de limite com o valor formatado

        setTimeout(() => {
            setValor(""); // Limpa o valor após o envio
            setLoading(false);
            setOpen(false);
            window.location.reload(); // Recarrega a página após 2 segundos
        }, 2000);
    }

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button
                    className="button-alt items-center text-35px w-[180px]"
                    onClick={() => setOpen(true)}
                    disabled={cliente.id == undefined}
                >
                    Alterar limite
                </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Alterar limite</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <div>
                        <Label htmlFor="valor">Novo Limite</Label>
                        <Input
                            id="valor"
                            value={valor}
                            onChange={handleChangeValor}
                            placeholder="R$ 0,00"
                            required
                        />
                    </div>

                    <Button type="submit" className="mt-4" disabled={loading}>
                        {loading ? "Carregando..." : "Alterar limite"}
                    </Button>
                </form>
                <Toaster position="bottom-center" richColors />
            </DialogContent>
        </Dialog>
    );
};

export default OptionButtonLimite;
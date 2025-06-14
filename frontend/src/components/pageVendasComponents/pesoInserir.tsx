"use client";

import { useState } from "react";
import { PlusCircle } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { toast, Toaster } from "sonner";
import { useTelaHook } from "@/hooks/useTela";
import { Tela } from "@/models/tela";
import { useSession } from "next-auth/react";
import { on } from "events";
import { formatarParaMoeda, moedaParaNumero } from "@/lib/utils";

interface InserirPesoProps {
    open: boolean;
    onClose: () => void;
    feedback: (peso: string) => void;
}

export default function InserirPeso({ open, onClose, feedback }: InserirPesoProps) {
    const [peso, setPeso] = useState("");

    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState(false);

    const onFormSubmit = async (event: React.FormEvent) => {
        event.preventDefault(); // Evita que o formulário recarregue a página
        setLoading(true); // Inicia o carregamento
        setError(null); // Reseta o erro

        if (!peso) {
            setError("Digite o peso");
            toast.error("Digite o peso!");
            setLoading(false);
        }

        try {
            feedback(peso); // Chama o callback de feedback com o peso
            setLoading(false); // Inicia o carregamento
            setPeso(""); // Limpa o campo do formulário
            onClose(); // Fecha o modal após sucesso

        } catch (error: any) {
            setLoading(false); // Inicia o carregamento
            toast.error(error.response.data.message);
        }
    };

    return (
        <Dialog open={open} onOpenChange={onClose}>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Adicionar Tela</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <div>
                        <Label htmlFor="peso">Peso</Label>
                        <Input
                            id="peso"
                            value={peso}
                            onChange={(e) => {
                                const input = e.target.value;

                                // Regex para permitir somente números com ponto e até 3 casas decimais
                                const regex = /^\d*\.?\d{0,3}$/;

                                if (input === "" || regex.test(input)) {
                                    setPeso(input);
                                }
                            }}
                            placeholder="Digite o peso"
                            autoComplete="off"
                            required
                        />
                    </div>

                    <Button type="submit" className="mt-4" disabled={loading}>
                        {loading ? "Carregando..." : "Enviar Peso"}
                    </Button>
                </form>
                <Toaster position="bottom-center" richColors />
            </DialogContent>
        </Dialog>
    );
}

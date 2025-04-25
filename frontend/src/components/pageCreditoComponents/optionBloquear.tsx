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
import { Toaster } from "sonner";

const OptionButtonBloquear = () => {
    const [loading, setLoading] = useState(false);
    const [open, setOpen] = useState(false);

    async function onFormSubmit(event: React.FormEvent) {
        // Handle form submission logic here
        setLoading(true);
        // Simulate an API call or any other async operation
        setTimeout(() => {
            setLoading(false);
            setOpen(false); // Close the dialog after submission
        }, 2000);
    }

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button
                    className="button-alt items-center text-35px w-[180px]"
                    onClick={() => setOpen(true)}
                >
                    Bloquear cartão
                </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Adicionar Tela</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <p>
                        Deseja bloquear o cartão do cliente?
                    </p>
                    <p className="w-full text-center">
                        <p className="font-bold">John Doe</p>
                        <p className="text-xs">SALDO: R$ -2,00</p>
                        <p className="text-xs">Limite: R$ 100,00</p>
                    </p>
                    <Button type="submit" className="mt-4" disabled={loading}>
                        {loading ? "Carregando..." : "Bloquear Cartão"}
                    </Button>
                </form>
                <Toaster position="bottom-center" richColors />
            </DialogContent>
        </Dialog>
    )
}

export default OptionButtonBloquear;
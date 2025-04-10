"use client";

import { Pencil, User } from "lucide-react";
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
import { formatarParaMoeda } from "@/lib/utils";
import { DropdownMenuItem } from "@radix-ui/react-dropdown-menu";
import { useSession } from "next-auth/react";


export default function ProfileButton() {
    const [open, setOpen] = useState(false);
    const [nome, setNome] = useState("");
    const { atualizarBalanca } = useProdutoHook();

    const { data: session } = useSession()

    const onFormSubmit = async () => {
        try {
            await atualizarBalanca(Number(nome));
            toast.success("Preço Atualizado")
        } catch (e: any) {
            toast.error(e);
        }
    }

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const valorFormatado = formatarParaMoeda(e.target.value);
        setNome(valorFormatado);
    };

    useEffect(()=>{
        if (session?.user?.nome) {
            setNome(`${session.user.nome}`)
        }
    }, [])

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <DropdownMenuItem onClick={() => setOpen(true)} className="text-[var(--black-color)] flex items-center btn-hover-scale cursor-pointer w-fit ml-4 mr-4">
                    <User className="size-4 mr-2" /> Perfil
                </DropdownMenuItem>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Perfil</DialogTitle>
                </DialogHeader>
                <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
                    <div>
                        <Label htmlFor="valor">Preço da Balança</Label>
                        <Input
                            id="valor"
                            value={nome}
                            onChange={handleChange}
                            placeholder="R$ 0,00"
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
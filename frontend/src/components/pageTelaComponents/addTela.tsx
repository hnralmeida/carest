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

export default function AddTela() {
  const [open, setOpen] = useState(false);
  const [nome, setNome] = useState("");
  const [rota, setrota] = useState("");

  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const { criarTela } = useTelaHook();

  const [rotaTocada, setRotaTocada] = useState(false);

  const { data: session, update } = useSession()
  const usuario = session?.user;

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    setLoading(true); // Inicia o carregamento
    setError(null); // Reseta o erro

    const data: Tela = {
      id: "",
      nome,
      rota
    };

    if (!nome || !rota) {
      setError("Digite Nome e Rota!");
      toast.error("Digite Nome e Rota!");
      setLoading(false);
    }

    try {
      if (!usuario?.id) {
        throw new Error("Usuário não encontrado.");
      }
      await criarTela(usuario.id, data).catch((error: any) => {
        setLoading(false); // Inicia o carregamento

        if (error.response.status === 404) {
          toast.error(error.response.data.message);
          return
        } else {
          toast.error("Erro ao criar tela");
          return
        }
      });

      const permissoesAtualizadas = usuario.permissoes.map((permissao: any) => {
        if (permissao.tela.nome === nome) {
          return {
            ...permissao,
            read: true,
          };
        }
        return permissao;
      })

      update({ permissoes: permissoesAtualizadas })
      toast.success("Tela adicionada com sucesso!")

      setOpen(false); // Fecha o modal após sucesso
      setNome(""); // Limpa o campo do formulário
      setrota(""); // Limpa o campo do formulário
      setLoading(false); // Inicia o carregamento

      // setTimeout(() => {
      //   window.location.reload();
      // }, 2000);

    } catch (error: any) {
      setLoading(false); // Inicia o carregamento
      toast.error(error.response.data.message);
    }
  };

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button
          className="button-class items-center text-2xl"
          onClick={() => setOpen(true)}
        >
          <PlusCircle size={48} />
          Adicionar
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Adicionar Tela</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome de exibição da tela</Label>
            <Input
              id="name"
              value={nome}
              onChange={(e) => {
                const novoNome = e.target.value;
                setNome(novoNome);

                if (!rotaTocada) {
                  setrota("/" + novoNome.toLowerCase().replace(/\s+/g, "_").normalize("NFD").replace(/[\u0300-\u036f]/g, ""));
                }
              }}
              placeholder="Digite o nome"
              autoComplete="off"
              required
            />
          </div>

          <div>
            <Label htmlFor="rota">Rota</Label>
            <Input
              id="rota"
              value={rota}
              onChange={(e) => setrota(e.target.value)}
              placeholder="Digite um rota"
              required
            />
          </div>

          <Button type="submit" className="mt-4" disabled={loading}>
            {loading ? "Carregando..." : "Adicionar tela"}
          </Button>
        </form>
        <Toaster position="bottom-center" richColors />
      </DialogContent>
    </Dialog>
  );
}

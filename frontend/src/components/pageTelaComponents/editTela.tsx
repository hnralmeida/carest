"use client";

import { useEffect, useState } from "react";
import { Pencil } from "lucide-react";
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
import { useTelaHook } from "@/hooks/useTela";

interface EditRotaProps {
  id: string;
  nome: string;
  rota: string;
}

export default function EditRota({ id, nome, rota }: EditRotaProps) {
  const [open, setOpen] = useState(false);
  const [formNome, setFormNome] = useState("");
  const [formRota, setFormRota] = useState("");

  const { editarTela } = useTelaHook();

  useEffect(() => {
    setFormNome(nome);
    setFormRota(rota);
  }, [nome]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const data = {
        "id": id,
        "nome": formNome,
        "rota": formRota,
      };

      const response = await editarTela(data);

      if (response) {
        alert("Tela alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setFormNome(""); // Limpa o campo do formulário
        setFormRota(""); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo Rota
      } else {
        alert("Erro ao alterar Tela.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Falha ao conectar com o servidor. ");
    }
  };

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button
          className="button-table"
          variant="outline"
          size="icon"
          onClick={() => setOpen(true)}
        >
          <Pencil size={24} />
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Editar Rota</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
        <div>
            <Label htmlFor="name">Nome do Rota</Label>
            <Input
              id="name"
              value={formNome}
              onChange={(e) => setFormNome(e.target.value)}
              placeholder="Digite o nome do Rota"
              required
            />
          </div>

          <div>
            <Label htmlFor="rota">rota</Label>
            <Input
              id="rota"
              value={formRota}
              onChange={(e) => setFormRota(e.target.value)}
              placeholder="Digite o rota do Rota"
              required
            />
          </div>

          <Button type="submit" className="mt-4">
            Salvar
          </Button>
        </form>
      </DialogContent>
    </Dialog>
  );
}

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
import { axiosClient } from "@/services/axiosClient";
import { toast } from "sonner";

interface EditFornecedorProps {
  id: string;
  nome: string;
}

export default function EditFornecedor({ id, nome }: EditFornecedorProps) {
  const [open, setOpen] = useState(false);
  const [formNome, setFormNome] = useState("");

  useEffect(() => {
    setFormNome(nome);
  }, [nome]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const data = {
        "id": id,
        "nome": formNome,
      };

      const response = await axiosClient.put("/fornecedor/" + id, data);

      if (response.status < 205) {
        toast.success("Fornecedor alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setFormNome(""); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo fornecedor
      } else {
        toast.error("Erro ao alterar fornecedor. " + response.statusText.toString());
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error("Falha ao conectar com o servidor. ");
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
          <DialogTitle>Editar Fornecedor</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Fornecedor</Label>
            <Input
              id="name"
              value={formNome}
              onChange={(e) => setFormNome(e.target.value)}
              placeholder="Digite o nome do fornecedor"
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

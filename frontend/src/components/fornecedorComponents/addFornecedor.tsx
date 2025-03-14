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
import { axiosClient } from "@/services/axiosClient";

export default function AddFornecedor() {
  const [open, setOpen] = useState(false);
  const [nome, setNome] = useState("");

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const response = await axiosClient.post("/fornecedor", { nome });

      if (response.status === 201) {
        alert("Fornecedor adicionado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setNome(""); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo fornecedor
      } else {
        alert("Erro ao adicionar fornecedor.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Falha ao conectar com o servidor.");
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
          <DialogTitle>Adicionar Fornecedor</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Fornecedor</Label>
            <Input
              id="name"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
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

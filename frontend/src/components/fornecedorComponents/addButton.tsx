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

  const onFormSubmit = async () => {
    const response = await axiosClient.post("/fornecedor", {
      descricao: "Teste",
    });

    alert(response.data ? "Fornecedor adicionado" : "Erro ao adicionar fornecedor");
  }

  return (
    <Dialog open={open} onOpenChange={setOpen} >
      <DialogTrigger asChild>
        <Button
          className="button-class items-center text-2xl"
          onClick={() => setOpen(true)}
        >
          <PlusCircle size={48} />
          Adicionar
        </Button>
      </DialogTrigger>
      <form onSubmit={onFormSubmit}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Adicionar Fornecedor</DialogTitle>
          </DialogHeader>
          <form className="flex flex-col gap-4">
            <div>
              <Label htmlFor="name">Descrição</Label>
              <Input id="name" placeholder="Digite o nome do fornecedor" />
            </div>
            <Button type="submit" className="mt-4">
              Salvar
            </Button>
          </form>
        </DialogContent>
      </form>
    </Dialog>
  );
}

"use client";

import React, { useEffect, useState } from "react";
import { Settings } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { usePermissaoHook } from "@/hooks/usePermissoes";

interface EditPermissoesProps {
  id: string;
}

export default function EditPermissoes({ id }: EditPermissoesProps) {
  const [open, setOpen] = useState(false);

  const { permissoes, selecionarpermissao } = usePermissaoHook();

  useEffect(() => {
    console.log("id do funcionario: ");
    console.log(id);
    selecionarpermissao(id);
  }, [open, id]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      console.log(id);
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Falha ao conectar com o servidor. ");
    }
  };

  return (
    <>
      <Dialog open={open} onOpenChange={setOpen}>
        <DialogTrigger asChild>
          <Button
            className="button-table"
            variant="outline"
            size="icon"
            onClick={() => setOpen(true)}
          >
            <Settings size={24} />
          </Button>
        </DialogTrigger>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Gerenciar Permissões</DialogTitle>
          </DialogHeader>
          <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
            {permissoes?.map((permissao) => (
              <div
                className="flex justify-between"
                key={permissao?.tela?.id}
              >
                <p className="w-[128px]">{permissao?.tela?.nome}</p>
                <Checkbox id="create-first" className="cursor-pointer"/>
                <Checkbox id="read-first" className="cursor-pointer"/>
                <Checkbox id="update-first" className="cursor-pointer"/>
                <Checkbox id="delete-first" className="cursor-pointer"/>
              </div>
            ))} 

            <Button type="submit" className="mt-4 cursor-pointer">
              Salvar
            </Button>
          </form>
        </DialogContent>
      </Dialog>
    </>
  );
}

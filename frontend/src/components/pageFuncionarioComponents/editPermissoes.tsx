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
import { UsuarioMock } from "@/models/usuario";
import { Permissao } from "@/models/permissao";

interface EditPermissoesProps {
  id: string;
}

export default function EditPermissoes({ id }: EditPermissoesProps) {
  const [open, setOpen] = useState(false);
  const [localPermissoes, setLocalPermissoes] = useState<Permissao[]>([]);

  const [loading, setLoading] = useState(false);

  const { permissoes, selecionarpermissao, editarpermissao } =
    usePermissaoHook();

  useEffect(() => {
    async function fetchPermissoes() {
      await selecionarpermissao(id);
      setLocalPermissoes(permissoes || []);
    }

    fetchPermissoes();
  }, [open, id]);

  //event: React.FormEvent
  const onFormSubmit = async () => {
    setLoading(true); // Inicia o carregamento

    if (localPermissoes) {
      const updatedPermissoes = localPermissoes.map((permissao, index) => ({
        ...permissao,
        usuario: UsuarioMock,
        create: localPermissoes[index].create,
        read: localPermissoes[index].read,
        update: localPermissoes[index].update,
        delete: localPermissoes[index].delete,
      }));

      try {
        const response = await editarpermissao(updatedPermissoes, id);
        setLoading(false); // Finaliza o carregamento

        if (!response) {
          throw new Error("Erro ao atualizar permissões");
        }
      } catch (error) {
        console.error("Erro na requisição:", error);
        setLoading(false); // Finaliza o carregamento
        alert("Falha ao conectar com o servidor. ");
      }
    }
  };

  const handleCheckboxChange = (index: number, field: "create" | "read" | "update" | "delete") => {
    setLocalPermissoes((prev) =>
      prev.map((perm, i) =>
        i === index ? { ...perm, [field]: !perm[field] } : perm
      )
    );
    console.log("localPermissoes", localPermissoes);
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
            {localPermissoes
              ? localPermissoes.map((permissao, index) => (
                <>
                  {index==0&&<div className="grid grid-cols-5 gap-4 mb-2">
                    <div className="font-semibold" /> {/* espaço para o nome da tela */}
                    <p className="font-semibold">Criar</p>
                    <p className="font-semibold">Editar</p>
                    <p className="font-semibold">Visualizar</p>
                    <p className="font-semibold">Excluir</p>
                  </div>}
                  <div
                    className="grid grid-cols-5 items-center gap-4"
                    key={permissao?.tela?.id}
                  >
                    <p className="font-medium">{permissao?.tela?.nome}</p>

                    <Checkbox
                      id={`create-${permissao.tela.id}`}
                      className="cursor-pointer border-[var(--black-color)]"
                      checked={localPermissoes[index].create}
                      onCheckedChange={() =>
                        handleCheckboxChange(index, "create")
                      }
                    />
                    <Checkbox
                      id={`read-${permissao.tela.id}`}
                      className="cursor-pointer border-[var(--black-color)]"
                      checked={localPermissoes[index].read}
                      onCheckedChange={() =>
                        handleCheckboxChange(index, "read")
                      }
                    />
                    <Checkbox
                      id={`update-${permissao.tela.id}`}
                      className="cursor-pointer border-[var(--black-color)]"
                      checked={localPermissoes[index].update}
                      onCheckedChange={() =>
                        handleCheckboxChange(index, "update")
                      }
                    />
                    <Checkbox
                      id={`delete-${permissao.tela.id}`}
                      className="cursor-pointer border-[var(--black-color)]"
                      checked={localPermissoes[index].delete}
                      onCheckedChange={() =>
                        handleCheckboxChange(index, "delete")
                      }
                    />
                  </div>
                </>
              ))
              : null}

            <Button type="submit" className="mt-4 cursor-pointer" disabled={loading}>
              {loading ? (
                <span className="animate-pulse">Salvando...</span>
              ) : (
                "Salvar"
              )}
            </Button>
          </form>
        </DialogContent>
      </Dialog>
    </>
  );
}

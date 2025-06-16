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
import { Permissao } from "@/models/permissao";
import { toast } from "sonner";
import { useTelaHook } from "@/hooks/useTela";
import { Tela } from "@/models/tela";
import { useSession } from "next-auth/react";
import { Usuario } from "@/models/usuario";
import { useFuncionarioHook } from "@/hooks/useFuncionario";

interface EditPermissoesProps {
  id: string;
}

export default function EditPermissoes({ id }: EditPermissoesProps) {
  const [open, setOpen] = useState(false);
  const [localPermissoes, setLocalPermissoes] = useState<Permissao[]>([]);
  const [PermissoesADM, setPermissoesADM] = useState<Permissao[]>([]);

  const { data: session } = useSession()

  const [loading, setLoading] = useState(false);

  const { permissoes, selecionarpermissao, editarpermissao, buscarFuncionario } =
    usePermissaoHook();

  useEffect(() => {
    async function fetchPermissoes() {
      await id != "carregando..." && selecionarpermissao(id);
      setLocalPermissoes(permissoes || []);
    }
    setPermissoesADM(session?.user?.permissoes || []);

    fetchPermissoes();
  }, [open, id]);

  //event: React.FormEvent
  const onFormSubmit = async () => {
    setLoading(true); // Inicia o carregamento

    const user = await buscarFuncionario(id).catch((error: any) => {
      setLoading(false); // Finaliza o carregamento
      console.error("Erro ao buscar usuário:", error);
      toast.error(error.message || error.response.data.message || error.response.data || "Falha ao conectar com o servidor");
      return null;
    });

    if (localPermissoes) {
      const updatedPermissoes = localPermissoes.map((permissao, index) => ({
        ...permissao,
        id: localPermissoes[index].id,
        tela: localPermissoes[index].tela,
        create: localPermissoes[index].create,
        read: localPermissoes[index].read,
        update: localPermissoes[index].update,
        delete: localPermissoes[index].delete,
        authority: localPermissoes[index].authority,
        usuario: user as Usuario, // Preenche o user  com o ID do usuário atual
      }));

      try {
        await editarpermissao(updatedPermissoes, id).then((res) => {
          toast.success(res);
        }).catch((error) => {
          toast.error(error);
        });
        setLoading(false); // Finaliza o carregamento
      } catch (error: any) {
        console.error("Erro na requisição:", error);
        setLoading(false); // Finaliza o carregamento
        toast.error(error.message || error.response.data.message || error.response.data || "Falha ao conectar com o servidor");
      }
    }
  };

  const handleCheckboxChange = (index: number, field: "create" | "read" | "update" | "delete") => {
    console.log("Permissões antes da atualização: ", localPermissoes);

    setLocalPermissoes((prev) =>
      prev.map((perm, i) =>
        i === index ? { ...perm, [field]: !perm[field] } : perm
      )
    );
  };

  const { criarTela } = useTelaHook();

  const [novaTela, setNovaTela] = useState<Tela>({} as Tela);

  const NovaTela = () => {
    return (
      < div className="border p-4 rounded mb-4" >
        <h4 className="font-semibold mb-2">Adicionar Tela</h4>
        <div className="flex gap-4 items-center">
          <select
            className="border p-2 rounded flex-1"
            value={novaTela?.id || ""}
            onChange={(e) => {
              const permissaoSelecionada = PermissoesADM?.find(p => p.tela?.id === e.target.value);
              if (permissaoSelecionada) {
                setNovaTela(permissaoSelecionada.tela);
              }
            }}
          >
            <option value="">Selecione uma tela</option>
            {PermissoesADM
              ?.filter(telaDisponivel => !localPermissoes.some(p => p.tela?.id === telaDisponivel.id))
              .map((item) => (
                <option key={item.id} value={item.tela.id}>
                  {item.tela.nome}
                </option>
              ))}
          </select>

          <Button
            type="button"
            onClick={() => {
              if (!novaTela?.id) {
                toast.error("Selecione uma tela.");
                return;
              }

              // Impede duplicidade
              if (localPermissoes.some(p => p.tela?.id === novaTela.id)) {
                toast.warning("Essa tela já foi adicionada.");
                return;
              }

              const data: Tela = {
                id: "",
                nome: novaTela.nome,
                rota: novaTela.rota
              };

              criarTela(id, data).then(() => {
                setLocalPermissoes((prev) => [
                  ...prev,
                  {
                    id: "",
                    usuario: {} as Usuario, // Preenche o usuário com o ID do usuário atual
                    tela: novaTela,
                    create: false,
                    read: true,
                    update: false,
                    delete: false,
                    authority: "",
                  },
                ]);
              }).catch((error: any) => {
                setLoading(false); // Inicia o carregamento

                if (error.response.status === 404) {
                  toast.error(error.response.data.message);
                  return
                } else {
                  toast.error("Erro ao criar tela");
                  return
                }
              });

              setNovaTela({} as Tela); // limpa seleção
            }}
          >
            Adicionar
          </Button>
        </div>
      </div >
    )
  }

  return (
    <>
      <Dialog open={open} onOpenChange={setOpen} >
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
        <DialogContent className="sm:max-w-[800px]">
          <DialogHeader>
            <DialogTitle>Gerenciar Permissões</DialogTitle>
          </DialogHeader>
          <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
            <NovaTela />
            <div className="max-h-[400px] overflow-y-auto pr-2">
              {localPermissoes
                ? localPermissoes.map((permissao, index) => (
                  <div key={index}>
                    {index == 0 && <div className="grid grid-cols-5 gap-4 mb-2">
                      <div className="font-semibold" /> {/* espaço para o nome da tela */}
                      <p className="font-semibold">Criar</p>
                      <p className="font-semibold">Visualizar</p>
                      <p className="font-semibold">Editar</p>
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
                  </div>
                ))
                : null}

              <Button type="submit" className="mt-4 cursor-pointer" disabled={loading}>
                {loading ? (
                  <span className="animate-pulse">Salvando...</span>
                ) : (
                  "Salvar"
                )}
              </Button>
            </div>
          </form>
        </DialogContent>
      </Dialog>
    </>
  );
}

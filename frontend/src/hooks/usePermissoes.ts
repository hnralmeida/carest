import { useState } from "react";
import { Permissao } from "@/app/models/permissao";
import { axiosClient } from "@/services/axiosClient";
import { PermissaoDTO } from "@/app/models/permissaoDTO";

export const usePermissaoHook = () => {
  const [permissoes, setPermissoes] = useState<Permissao[] | null>(null);

  const selecionarpermissao = async (userId: string) => {
    setPermissoes([
      {
        tela: {
          id: "1",
          nome: "funcionarios",
          rota: "/funcionarios",
        },
        create: true,
        read: true,
        update: true,
        delete: true,
      },
    ]);
    try {
      const response = await axiosClient.get(`/usuario/${userId}/permissoes`);
      if (response.data) {
        setPermissoes(response.data);
      }
    } catch (error) {
      console.error("Error fetching permissoes:", error);
    }
  };

  const editarpermissao = async (
    updatedPermissoes: PermissaoDTO[],
    id: string
  ): Promise<string> => {

    try {
      const response = await axiosClient.post(
        `/usuario/atualizarPermissoes/${id}`,
        updatedPermissoes
      );
      alert("response" + response.data);

      if (response.status < 205) {
        setPermissoes(response.data);
        return response.data;
      }
    } catch (error) {
      console.error("Error updating permissoes:", error);
    }
    return "Erro ao atualizar permissões";
  };

  const deletarpermissao = async (permissaoId: string): Promise<void> => {
    try {
      await axiosClient.delete(`/permissoes/${permissaoId}`);
    } catch (error) {
      console.error("Error deleting permissoes:", error);
    }
  };

  const criarpermissao = async (
    permissao: Permissao
  ): Promise<Permissao | null> => {
    try {
      const response = await axiosClient.post("/permissoes", permissao);
      if (response.data) {
        setPermissoes(response.data);
        return response.data;
      }
    } catch (error) {
      console.error("Error creating permissoes:", error);
    }
    return null;
  };

  return {
    permissoes,
    selecionarpermissao,
    editarpermissao,
    deletarpermissao,
    criarpermissao,
  };
};

import { useState } from "react";
import { Permissao } from "@/models/permissao";
import { axiosClient } from "@/services/axiosClient";
import { PermissaoDTO } from "@/models/permissaoDTO";

export const usePermissaoHook = () => {
  const [permissoes, setPermissoes] = useState<Permissao[] | null>(null);

  const selecionarpermissao = async (userId: string) => {
    try {
      const response = await axiosClient.get(`/usuario/${userId}/permissoes`);
      if (response.data) {
        setPermissoes(response.data);
      }
    } catch (error: any) {
      return Promise.reject(error.response?.data.message || error.response?.data || "Erro ao buscar permissões");
    }
  };

  const editarpermissao = async (
    updatedPermissoes: PermissaoDTO[],
    personId: string,
  ): Promise<string> => {

    try {
      axiosClient.post("/usuario/atualizarPermissoes/" + personId, updatedPermissoes).catch((error) => {
        return Promise.reject(error.response?.data.message || error.response?.data || "Erro ao atualizar permissões");
      });
    } catch (error: any) {
      return Promise.reject(error.response?.data.message || error.response?.data || "Erro ao atualizar permissões");
    }
    return Promise.resolve("Permissões atualizadas com sucesso");
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

import { useState } from "react";
import { Permissao } from "@/models/permissao";
import { axiosClient } from "@/services/axiosClient";
import { PermissaoDTO } from "@/models/permissaoDTO";
import { Usuario } from "@/models/usuario";

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

  const buscarFuncionario = async (FuncionarioId: string): Promise<Usuario> => {
        try {
            const response = await axiosClient.get(`/usuario/${FuncionarioId}`);
            if (response.data) {
                return response.data;
            }
        } catch (error) {
            console.error('Error fetching usuario:', error);
            return Promise.reject("Erro ao buscar funcionário");
        }
        return Promise.reject("Funcionário não encontrado");
    };

  const editarpermissao = async (
    updatedPermissoes: PermissaoDTO[],
    personId: string,
  ): Promise<string> => {
    try {
      for (const permissao of updatedPermissoes) {
        if (!permissao.id) {
          throw new Error("Permissão ID não fornecido");
        }
        await axiosClient.patch(`/permissao/${permissao.id}?userId=${permissao.usuario.id}`, permissao);
      }
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
    buscarFuncionario
  };
};

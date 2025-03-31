import { useState } from "react";
import { Usuario } from "@/app/models/usuario";
import { axiosClient } from "@/services/axiosClient";

export const useFuncionarioHook = () => {
    const [funcionarios, setFuncionarios] = useState<Usuario[] | null>(null);
    const [funcionario, setFuncionario] = useState<Usuario | null>(null);

    const listarFuncionarios = async () => {
        setFuncionarios([
            {
                "id": "string",
                "nome": "string",
                "email": "string",
            }
        ])
        try {
            const response = await axiosClient.get('/usuario');
            if (response.data) {
                setFuncionarios(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    const selecionarFuncionario = async (FuncionarioId: string) => {
        try {
            const response = await axiosClient.get(`/usuario/${FuncionarioId}`);
            if (response.data) {
                setFuncionario(response.data);
            }
        } catch (error) {
            console.error('Error fetching usuario:', error);
        }
    };

    const editarFuncionario = async (Funcionario: Usuario): Promise<Usuario | null> => {
        try {
            const response = await axiosClient.put(`/usuario/${Funcionario.id}`, Funcionario);
            if (response.data) {
                setFuncionario(response.data);
                return response.data;
            }
        } catch (error) {
            console.error('Error updating usuario:', error);
        }
        return null;
    };

    const deletarFuncionario = async (FuncionarioId: string): Promise<void> => {
        try {
            await axiosClient.delete(`/usuario/${FuncionarioId}`);
        } catch (error) {
            console.error('Error deleting usuario:', error);
        }
    };

    const criarFuncionario = async (Funcionario: Usuario): Promise<Usuario | null> => {
        try {
            const response = await axiosClient.post('/usuario', Funcionario);
            if (response.data) {
                axiosClient.post("/usuario/criarPermissoes/" + response.data.id);
                setFuncionario(response.data);
                return response.data;
            }
        } catch (error) {
            console.error('Error creating usuario:', error);
        }
        return null;
    };

    return { 
        funcionarios, 
        funcionario, 
        listarFuncionarios, 
        selecionarFuncionario, 
        editarFuncionario, 
        deletarFuncionario, 
        criarFuncionario 
    };
};
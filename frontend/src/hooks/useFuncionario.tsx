import { useState } from "react";
import { Usuario } from "@/models/usuario";
import { axiosClient } from "@/services/axiosClient";

export const useFuncionarioHook = () => {
    const [funcionarios, setFuncionarios] = useState<Usuario[] | null>(null);
    const [funcionario, setFuncionario] = useState<Usuario | null>(null);

    const listarFuncionarios = async () => {
        setFuncionarios([
            {
                "id": "carregando...",
                "nome": "carregando...",
                "email": "carregando...",
            }
        ])
        try {
            const response = await axiosClient.get('/usuario');
            if (response.data) {
                const formated = response.data.sort((a: Usuario, b: Usuario) => a.nome.localeCompare(b.nome));
                setFuncionarios(formated);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
            setFuncionarios([]);
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
            return Promise.reject((error as Error).message);
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
            const data = {
                "userId": response.data.id,
                "nomeTela": "Telas",
                "rotaTela": "/tela"
            }

            if (response.data) {
                axiosClient.post("/usuario/permitir", data).catch((error) => {
                    return Promise.reject('Error associating user with screen: ' + error)
                });
                setFuncionario(response.data);
                return response.data;
            }
        } catch (error) {
            return Promise.reject('Error creating usuario: ' + error);
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
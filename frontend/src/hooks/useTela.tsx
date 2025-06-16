import { useState } from "react";
import { Tela } from "@/models/tela";
import { axiosClient } from "@/services/axiosClient";
import { useSession } from "next-auth/react";

export const useTelaHook = () => {
    const [telas, setTelas] = useState<Tela[] | null>(null);
    const [tela, setTela] = useState<Tela | null>(null);

    const listarTelas = async (id: string) => {

        try {
            const response = await axiosClient.get('/tela/usuario/' + id);
            if (response.data) {
                // ordenar resposta por ordem alfabética de nome
                response.data.sort((a: Tela, b: Tela) => a.nome.localeCompare(b.nome));
                setTelas(response.data);
                console.log("telas", response.data);
            } else {
                setTelas([]);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
            setTelas([]);
        }
    };

    const selecionarTela = async (TelaId: string) => {
        try {
            const response = await axiosClient.get(`/tela/${TelaId}`);
            if (response.data) {
                setTela(response.data);
            }
        } catch (error) {
            console.error('Error fetching tela:', error);
        }
    };

    const editarTela = async (Tela: Tela): Promise<Tela | null> => {
        try {
            const response = await axiosClient.put(`/tela/${Tela.id}`, Tela);
            if (response.data) {
                setTela(response.data);
                return response.data;
            }
        } catch (error) {
            console.error('Error updating tela:', error);
        }
        return null;
    };

    const deletarTela = async (TelaId: string): Promise<void> => {
        try {
            await axiosClient.delete(`/tela/${TelaId}`);
        } catch (error) {
            console.error('Error deleting tela:', error);
        }
    };

    const criarTela = async (userId: String, Tela: Tela): Promise<Tela> => {

        const body = {
            userId,
            nomeTela: Tela.nome,
            rotaTela: Tela.rota
        };
        try {
            const response = await axiosClient.post('/usuario/permitir', body);
            return response.data;
        } catch (error: any) {
            return Promise.reject(error);
        }
    };

    return {
        telas,
        tela,
        listarTelas,
        selecionarTela,
        editarTela,
        deletarTela,
        criarTela
    };
};
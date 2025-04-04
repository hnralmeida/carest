import { useState } from "react";
import { Tela } from "@/app/models/tela";
import { axiosClient } from "@/services/axiosClient";

export const useTelaHook = () => {
    const [telas, setTelas] = useState<Tela[] | null>(null);
    const [tela, setTela] = useState<Tela | null>(null);

    const listarTelas = async () => {
        setTelas([
            {
                "id": "Carregando...",
                "nome": "Carregando...",
                "rota": "Carregando...",
            }
        ])
        try {
            const response = await axiosClient.get('/tela');
            if (response.data) {
                setTelas(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
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

    const criarTela = async (Tela: Tela): Promise<Tela | null> => {
        try {
            const response = await axiosClient.post('/tela', Tela);
        } catch (error) {
            console.error('Error creating tela:', error);
        }
        return null;
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
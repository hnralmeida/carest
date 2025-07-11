import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useAniversariantesHook = () => {
    const [aniversariantes, setAniversariantes] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(true);

    const listarAniversariantes = async (month: Number) => {
        setLoading(true);
        try {
            const hoje = new Date();
            const response = await axiosClient.get(`/relatorios/aniversariantes?mes=${month}`);
            if (response.data) {
                setAniversariantes(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            console.error('Error fetching administrators:', error);
            return Promise.reject("Clientes não encontrados");
        }
    };

    return { 
        loading,
        aniversariantes,
        listarAniversariantes
    };
};
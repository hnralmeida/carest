import { Cliente } from "@/components/clienteComponents/columns";
import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";


export const useAniversariantesHook = () => {
    const [aniversariantes, setAniversariantes] = useState<Cliente[] | null>(null);

    const listarAniversariantes = async () => {
        setAniversariantes([
            {
                "id": "Carregando...",
                "nome": "Carregando...",
                "codigo": "Carregando...",
                "email": "Carregando...",
                "nascimento": "Carregando...",
                "telefone": "Carregando..."
            }
        ])
        try {
            const hoje = new Date();
            const response = await axiosClient.get(`/aniversariantes/mes?mes=${hoje.getMonth()+1}`);
            if (response.data) {
                setAniversariantes(response.data);
            }
        } catch (error) {
            console.error('Error fetching administrators:', error);
        }
    };

    return { 
        aniversariantes,
        listarAniversariantes
    };
};
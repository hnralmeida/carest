import { useState } from "react";
import { axiosClient } from "@/services/axiosClient";
import { Cliente } from "@/models/cliente";


export const useDreHook = () => {
    const [dre, setDre] = useState<Cliente[] | null>(null);
    const [loading, setLoading] = useState(true);

    const listarDre = async () => {
        setLoading(true);
        try {
            const hoje = new Date();
            const response = await axiosClient.get(`/aniversariantes/mes?mes=${hoje.getMonth()+1}`);
            if (response.data) {
                setDre(response.data);
            }
            setLoading(false);
        } catch (error) {
            setLoading(false);
            console.error('Error fetching administrators:', error);
            return Promise.reject("Clientes não encontrados");
        }
    };

    return { 
        dre,
        listarDre,
        loading
    };
};
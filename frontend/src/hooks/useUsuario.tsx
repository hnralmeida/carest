"use client";

import { useState } from "react";
import { Usuario } from "@/models/usuario";
import { axiosClient } from "@/services/axiosClient";

export const useUsuarioHook = () => {
    const [usuario, setUsuario] = useState<Usuario | null>(null);

    const getUsuario = (email: String): Promise<Usuario> => {
        console.log("getUsuario ", email);
        if (!email) {
            return Promise.reject("Email não encontrado na sessão.");
        }
        const route = `/usuario/email/${email}`;

        axiosClient.get(route).then((response) => {
            const data = response.data;
            setUsuario(data);
        }
        ).catch((error) => {
            Promise.reject(error);
        });
        if (!usuario) {
            return Promise.reject("Usuário não encontrado.");
        }
        return Promise.resolve(usuario);
    };

    return {
        usuario,
        getUsuario,
        setUsuario
    };
};

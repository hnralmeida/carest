"use client";
 
import { useState } from "react";
import { Usuario } from "@/app/models/usuario";

export const useUsuarioHook = () => {
    const [usuario, setUsuario] = useState<Usuario | null>(null);

    const getUsuario = (): Usuario | null => {
        return usuario;
    };
    return { 
        usuario,
        getUsuario,
        setUsuario
    };
};

import { AxiosResponse } from "axios";
import { axiosClient } from "./axiosClient";
import { Usuario } from "@/app/models/usuario";

export default class UsuarioService {
  async autenticar(credentials: {
    email: string;
    password: string;
  }): Promise<AxiosResponse<Usuario>> {
    const response = await axiosClient.post("/auth/login", credentials);

    if (response.status === 401) {
      throw new Error("Credenciais inválidas");
    }
    if (response.status === 404) {
      throw new Error("Usuário não encontrado");
    }
    if (response.status === 500) {
      throw new Error("Erro interno no servidor");
    }
    if (response.status <= 205) {
      return response;
    }
    throw new Error("Erro desconhecido");
  }
}

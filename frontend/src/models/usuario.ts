// Definição da interface do usuário autenticado
export interface Usuario {
  id: string;
  nome: string;
  senha?: string;
  email: string;
  permissoes?: string[];
  codigo?: string;
}

export const UsuarioMock: Usuario = {
  id: "",
  nome: "",
  email: "",
};
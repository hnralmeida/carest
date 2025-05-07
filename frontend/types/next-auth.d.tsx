import { Usuario } from "@/models/usuario";

declare module "next-auth" {
  interface Session {
    user: Usuario;
  }

  interface User {
    id: string;
    nome: string;
    email: string;  
    permissoes: string[];
  }

  interface JWT {
    id: string;
    nome: string;
    email: string;  
    permissoes: string[];
  }
}
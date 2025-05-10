import { Tela } from "./tela";
import { Usuario } from "./usuario";

export interface PermissaoDTO {
    id: string;
    usuario: Usuario;
    tela: Tela;
    create: boolean;
    read: boolean;
    update: boolean;
    delete: boolean;
  }
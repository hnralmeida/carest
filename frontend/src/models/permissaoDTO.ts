import { Tela } from "./tela";
import { Usuario } from "./usuario";

export interface PermissaoDTO {
    id: string;
    tela: Tela;
    create: boolean;
    read: boolean;
    update: boolean;
    delete: boolean;
    authority: string;
    usuario: Usuario;
  }
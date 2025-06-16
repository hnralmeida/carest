import { Tela } from "./tela";
import { Usuario } from "./usuario";

export interface Permissao {
    id: string;
    tela: Tela;
    create: boolean;
    read: boolean;
    update: boolean;
    delete: boolean;
    authority: string;
  }
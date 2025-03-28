import { Tela } from "./tela";

export interface Permissao {
    tela: Tela;
    create: boolean;
    read: boolean;
    update: boolean;
    delete: boolean;
  }
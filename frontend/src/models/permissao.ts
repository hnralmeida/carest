import { Tela } from "./tela";

export interface Permissao {
    id: string;
    tela: Tela;
    create: boolean;
    read: boolean;
    update: boolean;
    delete: boolean;
  }
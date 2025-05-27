import { Fornecedor } from "@/components/pageFornecedorComponents/columns";

export interface Saidas {
    id: string;
    descricao: string;
    valor: number;
    dataVencimento: string;
    dataPagamento: string;
    fornecedor: Fornecedor;
}
"use client";

import { useEffect, useState } from "react";
import { Pencil } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useProdutoHook } from "@/hooks/useProdutos";
import { toast, Toaster } from "sonner";
import { formatarParaMoeda, moedaParaNumero } from "@/lib/utils";

interface EditProdutoProps {
  id: string;
  nome: string;
  valor: number;
  custo: number;
  codigo: string;
}

export default function EditProduto({ id, nome, valor, codigo, custo }: EditProdutoProps) {
  const [open, setOpen] = useState(false);
  const [formNome, setFormNome] = useState("");
  const [formValor, setFormValor] = useState("");
  const [formCusto, setFormCusto] = useState("");
  const [formCodigo, setFormCodigo] = useState("");

  const { editarProduto } = useProdutoHook();

  useEffect(() => {
    setFormNome(nome);
    setFormValor(formatarParaMoeda(valor.toString()));
    setFormCusto(formatarParaMoeda(custo.toString()));
    setFormCodigo(codigo);
  }, [nome, valor, codigo, custo]);

  useEffect(() => {
    const handleKeyPress = (e: KeyboardEvent) => {

      // Impede Ctrl+J de abrir a aba de downloads
      if (e.ctrlKey && e.key.toLowerCase() === "j") {
        e.preventDefault();
        return; // se quiser ignorar completamente essa combinação
      }
    };
    window.addEventListener("keydown", handleKeyPress);
    return () => {
      window.removeEventListener("keydown", handleKeyPress);
    };
  }, []);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const data = {
        "id": id,
        "nome": formNome,
        "valor": moedaParaNumero(formValor),
        "custo": moedaParaNumero(formCusto),
        "codigo": formCodigo
      };

      const response = await editarProduto(data);

      if (response) {
        toast.success("Produto alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setFormNome(""); // Limpa o campo do formulário
        setFormValor(""); // Limpa o campo do formulário
        setFormCusto(""); // Limpa o campo do formulário
        setFormCodigo(""); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo Produto
      } else {
        toast.error("Erro ao alterar Produto.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error("Falha ao conectar com o servidor. ");
    }
  };

  const handleChangeValor = (e: React.ChangeEvent<HTMLInputElement>) => {
    const valorFormatado = formatarParaMoeda(e.target.value);
    setFormValor(valorFormatado);
  };

  const handleChangeCusto = (e: React.ChangeEvent<HTMLInputElement>) => {
    const valorFormatado = formatarParaMoeda(e.target.value);
    setFormCusto(valorFormatado);
  };

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button
          className="button-table"
          variant="outline"
          size="icon"
          onClick={() => setOpen(true)}
        >
          <Pencil size={24} />
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Editar Produto</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Produto</Label>
            <Input
              id="name"
              value={formNome}
              onChange={(e) => setFormNome(e.target.value)}
              placeholder="Digite o nome do Produto"
              required
            />
          </div>

          <div>
            <Label htmlFor="valor">Valor</Label>
            <Input
              id="valor"
              value={formValor}
              onChange={handleChangeValor}
              placeholder="R$ 0,00"
              required
            />
          </div>

          <div>
            <Label htmlFor="custo">Custo</Label>
            <Input
              id="custo"
              value={formCusto}
              onChange={handleChangeCusto}
              placeholder="R$ 0,00"
              required
            />
          </div>

          <div>
            <Label htmlFor="codigo">Código</Label>
            <Input
              id="codigo"
              value={formCodigo}
              onChange={(e) => setFormCodigo(e.target.value)}
              placeholder="Insira um codigo"
              required
            />
          </div>

          <Button type="submit" className="mt-4">
            Salvar
          </Button>
        </form>
      </DialogContent>
      <Toaster richColors position="bottom-center" closeButton />
    </Dialog>
  );
}

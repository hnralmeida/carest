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

interface EditProdutoProps {
  id: string;
  nome: string;
  valor: number;
  codigo: number;
}

export default function EditProduto({ id, nome, valor, codigo }: EditProdutoProps) {
  const [open, setOpen] = useState(false);
  const [formNome, setFormNome] = useState("");
  const [formValor, setFormValor] = useState(0);
  const [formCodigo, setFormCodigo] = useState(0);

  const { editarProduto } = useProdutoHook();

  useEffect(() => {
    setFormNome(nome);
    setFormValor(valor);
    setFormCodigo(codigo);
  }, [nome, valor, codigo]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const data = {
        "id": id,
        "nome": formNome,
        "valor": formValor,
        "codigo": formCodigo
      };

      const response = await editarProduto(data);

      if (response) {
        alert("Produto alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setFormNome(""); // Limpa o campo do formulário
        setFormValor(0); // Limpa o campo do formulário
        setFormCodigo(0); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo Produto
      } else {
        alert("Erro ao alterar Produto.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Falha ao conectar com o servidor. ");
    }
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
              onChange={(e) => setFormValor(Number(e.target.value))}
              placeholder="Insira um valor"
              required
            />
          </div>

          <div>
            <Label htmlFor="codigo">Código</Label>
            <Input
              id="codigo"
              value={formCodigo}
              onChange={(e) => setFormCodigo(Number(e.target.value))}
              placeholder="Insira um codigo"
              required
            />
          </div>

          <Button type="submit" className="mt-4">
            Salvar
          </Button>
        </form>
      </DialogContent>
    </Dialog>
  );
}

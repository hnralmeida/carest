"use client";

import React, { useState } from "react";
import { PlusCircle } from "lucide-react";
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
import { toast, Toaster } from "sonner";
import { useProdutoHook } from "@/hooks/useProdutos";
import { Produto } from "@/models/produto";
import { formatarParaMoeda, moedaParaNumero } from "@/lib/utils";

export default function AddProduto() {
  const [open, setOpen] = useState(false);
  const [nome, setNome] = useState("");
  const [valor, setValor] = useState("");
  const [custo, setCusto] = useState("");
  const [codigo, setCodigo] = useState("");

  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const { criarProduto } = useProdutoHook();

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    setLoading(true); // Inicia o carregamento
    setError(null); // Reseta o erro

    const data: Produto = {
      id: "",
      nome,
      codigo,
      valor: Number(moedaParaNumero(valor).toFixed(2)),
      custo: Number(moedaParaNumero(custo).toFixed(2)),
    };

    if (!nome || !valor || !codigo) {
      setError("Não deixe campos vazios!");
      toast.error("Não deixe campos vazios!");
      setLoading(false);
    }

    try {
      const response = await criarProduto(data);

      window.location.reload(); // Recarrega a página para exibir o novo Funcionario

      toast.success("Produto " + response?.nome + " adicionada com sucesso!")
      setOpen(false); // Fecha o modal após sucesso
      setNome(""); // Limpa o campo do formulário
      setValor("0"); // Limpa o campo do formulário
      setCusto("0"); // Limpa o campo do formulário
      setCodigo(""); // Limpa o campo do formulário
      setLoading(false); // Inicia o carregamento

    } catch (error) {
      setOpen(false); // Fecha o modal após sucesso
      setLoading(false);
      toast.error("Erro na requisição:" + error);
    }
  };

  const handleChangeValor = (e: React.ChangeEvent<HTMLInputElement>) => {
    const valorFormatado = formatarParaMoeda(e.target.value);
    setValor(valorFormatado);
  };

  const handleChangeCusto = (e: React.ChangeEvent<HTMLInputElement>) => {
    const valorFormatado = formatarParaMoeda(e.target.value);
    setCusto(valorFormatado);
  };

  React.useEffect(() => {
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

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button
          className="button-class items-center text-2xl"
          onClick={() => setOpen(true)}
        >
          <PlusCircle size={48} />
          Adicionar
        </Button>
      </DialogTrigger>
      <DialogContent
        onInteractOutside={(e) => {
          e.preventDefault(); // Impede o fechamento do modal ao clicar fora
        }}
      >
        <DialogHeader>
          <DialogTitle>Adicionar Produto</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Produto</Label>
            <Input
              id="name"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              placeholder="Digite o nome"
              autoComplete="off"
              required
            />
          </div>

          <div>
            <Label htmlFor="valor">Valor</Label>
            <Input
              id="valor"
              value={valor}
              onChange={handleChangeValor}
              placeholder="R$ 0,00"
              required
            />
          </div>
          <div>
            <Label htmlFor="custo">Custo</Label>
            <Input
              id="custo"
              value={custo}
              onChange={handleChangeCusto}
              placeholder="R$ 0,00"
              required
            />
          </div>

          <div>
            <Label htmlFor="codigo">Codigo</Label>
            <Input
              id="codigo"
              value={codigo}
              onChange={(e) => {
                const apenasLetrasENumeros = e.target.value.replace(/[^a-zA-Z0-9]/g, '');
                setCodigo(apenasLetrasENumeros);
              }}
              onKeyDown={(e) => {
                if ((e.key).toLocaleLowerCase() == 'enter') {
                  e.preventDefault(); // impede que o enter dispare comportamento padrão
                  // você pode adicionar uma ação aqui se quiser, tipo submeter ou processar
                }
              }}
              placeholder="Insira um codigo"
              required
            />
          </div>

          <Button type="submit" className="mt-4" disabled={loading}>
            {loading ? "Carregando..." : "Adicionar Produto"}
          </Button>
        </form>
        <Toaster position="bottom-center" richColors />
      </DialogContent>
    </Dialog>
  );
}

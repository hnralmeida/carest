"use client";

import { useState } from "react";
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
import { useFuncionarioHook } from "@/hooks/useFuncionario";
import { Usuario } from "@/app/models/usuario";

export default function AddFuncionario() {
  const [open, setOpen] = useState(false);
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [senha2, setSenha2] = useState("");

  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const { criarFuncionario } = useFuncionarioHook();

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    setLoading(true); // Inicia o carregamento
    setError(null); // Reseta o erro

    const data: Usuario = {
      id: "",
      nome,
      email,
      senha,
      codigo: "",
      permissao: [],
    };

    if (senha !== senha2) {
      setError("As senhas não coincidem!");
      toast("As senhas não coincidem!", {
        action: {
          label: "Tentar Novamente",
          onClick: () => console.log("Undo"),
        },
      });
      setLoading(false); // Inicia o carregamento
      return;
    }
    try {
      const response = await criarFuncionario(data);

      window.location.reload(); // Recarrega a página para exibir o novo Funcionario

      toast.success("Funcionario " + response?.nome + " adicionado com sucesso!")
      setOpen(false); // Fecha o modal após sucesso
      setNome(""); // Limpa o campo do formulário
      setEmail(""); // Limpa o campo do formulário
      setLoading(false); // Inicia o carregamento

    } catch (error) {
      setLoading(false); // Inicia o carregamento
      setOpen(false); // Fecha o modal após sucesso
      setLoading(false); // Inicia o carregamento
      console.error("Erro na requisição:", error);

      toast("Erro na requisição:" + error, {
        action: {
          label: "Ok",
          onClick: () => console.log("OK"),
        },
      });
    }
  };

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
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Adicionar Funcionário</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Funcionário</Label>
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
            <Label htmlFor="email">Email</Label>
            <Input
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Digite um email"
              required
            />
          </div>

          <div>
            <Label htmlFor="senha">Senha</Label>
            <Input
              id="senha"
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              placeholder="Digite uma senha"
              required
              className={error ? "border-red-500" : ""}
            />
          </div>

          <div>
            <Label htmlFor="senha2">Repita a senha</Label>
            <Input
              id="senha2"
              type="password"
              value={senha2}
              onChange={(e) => setSenha2(e.target.value)}
              placeholder="Confirmar senha"
              required
              className={error ? "border-red-500" : ""}
            />
          </div>

          <Button type="submit" className="mt-4" disabled={loading}>
            {loading ? "Carregando..." : "Adicionar Funcionário"}
          </Button>
        </form>
        <Toaster position="bottom-center" richColors />
      </DialogContent>
    </Dialog>
  );
}

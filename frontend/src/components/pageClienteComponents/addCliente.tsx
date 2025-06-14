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
import { useClienteHook } from "@/hooks/useCliente";
import { Cliente } from "@/models/cliente";
import { formatTelefone } from "@/lib/utils";

export default function AddCliente() {
  const [open, setOpen] = useState(false);
  const [nome, setNome] = useState("");
  const [telefone, setTelefone] = useState("");
  const [email, setEmail] = useState("");
  const [codigo, setCodigo] = useState("");
  const [nascimento, setNascimento] = useState("");

  const { adicionarCliente } = useClienteHook();

  function ISODateToDate(d: Date): string {
    const dia = String(d.getDate()).padStart(2, '0');     // Dia do mês
    const mes = String(d.getMonth() + 1).padStart(2, '0'); // Mês (corrigido +1)
    const ano = d.getFullYear();

    return `${ano}/${mes}/${dia}`;
  }

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    const nascimentoDate = new Date(`${nascimento}T12:00:00`)

    const data = {
      id: "",
      nome: nome,
      nascimento: ISODateToDate(nascimentoDate),
      telefone: telefone,
      email: email,
      limite: 0,
      saldo: 0,
      em_uso: false,
      codigo: codigo,
      dividaData: ISODateToDate(new Date()),
    } as Cliente;

    try {
      const response = await adicionarCliente(data);

      toast.success("Cliente adicionado com sucesso!");
      setOpen(false); // Fecha o modal após sucesso
      setNome(""); // Limpa o campo do formulário
      // window.location.reload(); // Recarrega a página para exibir o novo cliente

    } catch (error: any) {
      console.error("Erro na requisição:", error);
      toast.error(typeof error === "string" ? error : "Erro desconhecido");
    }
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
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Adicionar cliente</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome Completo</Label>
            <Input
              id="name"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              placeholder="Digite o nome do cliente"
              required
            />
          </div>
          <div>
            <Label htmlFor="telefone">Telefone</Label>
            <Input
              id="telefone"
              value={telefone}
              onChange={(e) => setTelefone(formatTelefone(e.target.value))}
              placeholder="Digite o telefone do cliente"
              required
            />
          </div>
          <div>
            <Label htmlFor="email">Email</Label>
            <Input
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Digite o email do cliente"
              required
            />
          </div>
          <div>
            <Label htmlFor="codigo">Código</Label>
            <Input
              id="codigo"
              value={codigo}
              onChange={(e) => {
                const apenasLetrasENumeros = e.target.value.replace(/[^a-zA-Z0-9]/g, '');
                setCodigo(apenasLetrasENumeros)
                alert(e.target.value)
              }}
              onKeyDown={(e) => {
                if ((e.key).toLocaleLowerCase() == 'enter') {
                  e.preventDefault(); // impede que o enter dispare comportamento padrão
                  // você pode adicionar uma ação aqui se quiser, tipo submeter ou processar
                }
              }}
              placeholder="Digite o codigo do cliente"
              required
            />
          </div>
          <div>
            <Label htmlFor="nascimento">Data de Nascimento</Label>
            <Input
              id="nascimento"
              type="date"
              value={nascimento}
              onChange={(e) => setNascimento(e.target.value)}
              placeholder="Digite a Data de nascimento do cliente"
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

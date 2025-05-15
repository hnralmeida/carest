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
import { axiosClient } from "@/services/axiosClient";
import { dateToISO } from "@/lib/utils";
import { toast } from "sonner";
import { Cliente } from "@/models/cliente";
import { useClienteHook } from "@/hooks/useCliente";

interface EditClienteProps {
  id: string;
  item: Cliente;
}

export default function EditCliente({ id, item }: EditClienteProps) {
  const [open, setOpen] = useState(false);
  const [formCliente, setFormCliente] = useState<Cliente>({} as Cliente);

  const { editCliente } = useClienteHook();

  function ISODateToDate(d: Date): string {
    const dia = String(d.getDate()).padStart(2, '0');     // Dia do mês
    const mes = String(d.getMonth() + 1).padStart(2, '0'); // Mês (corrigido +1)
    const ano = d.getFullYear();

    return `${ano}/${mes}/${dia}`;
  }

  useEffect(() => {
    setFormCliente((prevData: any) => ({
      ...prevData,
      nome: item.nome,
    }));

    setFormCliente((prevData: any) => ({
      ...prevData,
      email: item.email,
    }));

    setFormCliente((prevData: any) => ({
      ...prevData,
      telefone: item.telefone,
    }));

    setFormCliente((prevData: any) => ({
      ...prevData,
      codigo: item.codigo,
    }));

    setFormCliente((prevData) => ({
      ...prevData,
      nascimento: dateToISO(new Date(item.nascimento)),
    }))
  }, [item]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    console.log("nascimento", formCliente.nascimento);

    try {
      const data = {
        id: id,
        nome: formCliente.nome,
        email: formCliente.email,
        telefone: formCliente.telefone,
        codigo: formCliente.codigo,
        limite: item.limite,
        saldo: item.saldo,
        nascimento: ISODateToDate(new Date(`${formCliente.nascimento.replace('/', '-')}T12:00:00`)),
      };
      console.log("data.nascimento", data.nascimento);

      const response = await editCliente(data, id);

      setOpen(false); // Fecha o modal após sucesso
      setFormCliente({} as Cliente); // Limpa o campo do formulário
      toast.success("Cliente alterado com sucesso!");

      // Aguarda 2 segundos antes de recarregar a página
      setTimeout(() => {
        window.location.reload();
      }, 2000); // Recarrega a página após 2 segundo
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error(typeof error === "string" ? error : "Erro desconhecido");
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
          <DialogTitle>Editar Cliente</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="name">Nome do Cliente</Label>
            <Input
              id="name"
              value={formCliente.nome}
              onChange={(e) =>
                setFormCliente((prevData) => ({
                  ...prevData,
                  nome: e.target.value,
                }))
              }
              placeholder="Digite o nome do cliente"
              required
            />
          </div>

          <div>
            <Label htmlFor="email">Email</Label>
            <Input
              id="email"
              value={formCliente.email}
              onChange={(e) =>
                setFormCliente((prevData) => ({
                  ...prevData,
                  email: e.target.value,
                }))
              }
              placeholder="Digite o email do cliente"
              required
            />
          </div>

          <div>
            <Label htmlFor="telefone">Telefone</Label>
            <Input
              id="telefone"
              value={formCliente.telefone}
              onChange={(e) =>
                setFormCliente((prevData) => ({
                  ...prevData,
                  telefone: e.target.value,
                }))
              }
              placeholder="Digite o telefone do cliente"
              required
            />
          </div>

          <div>
            <Label htmlFor="codigo">Código</Label>
            <Input
              id="codigo"
              value={formCliente.codigo}
              onChange={(e) =>
                setFormCliente((prevData) => ({
                  ...prevData,
                  codigo: e.target.value,
                }))
              }
              placeholder="Digite o código do cliente"
              required
            />
          </div>

          <div>
            <Label htmlFor="nascimento">Data de Nascimento</Label>
            <Input
              id="nascimento"
              type="date"
              value={
                formCliente.nascimento
                  ? dateToISO(new Date(formCliente.nascimento))
                  : ""
              }
              onChange={(e) =>
                setFormCliente((prevData) => ({
                  ...prevData,
                  nascimento: e.target.value,
                }))
              }
              placeholder="Digite a data de nascimento do cliente"
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

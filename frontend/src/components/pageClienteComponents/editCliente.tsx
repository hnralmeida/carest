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
import { Cliente } from "./columns";
import { dateToISO } from "@/lib/utils";
import { toast } from "sonner";

interface EditClienteProps {
  id: string;
  item: Cliente;
}

export default function EditCliente({ id, item }: EditClienteProps) {
  const [open, setOpen] = useState(false);
  const [formCliente, setFormCliente] = useState<Cliente>({} as Cliente);

  function ISODateToDate(d: Date): string {
    const dia = String(d.getDate()).padStart(2, '0');     // Dia do mês
    const mes = String(d.getMonth() + 1).padStart(2, '0'); // Mês (corrigido +1)
    const ano = d.getFullYear();
  
    return `${ano}/${mes}/${dia}`;
  }

  useEffect(() => {
    setFormCliente((prevData) => ({
      ...prevData,
      nome: item.nome,
    }));

    setFormCliente((prevData) => ({
      ...prevData,
      email: item.email,
    }));

    setFormCliente((prevData) => ({
      ...prevData,
      telefone: item.telefone,
    }));

    setFormCliente((prevData) => ({
      ...prevData,
      codigo: item.codigo,
    }));

    setFormCliente((prevData) => ({
      ...prevData,
      nascimento: item.nascimento,
    }));
  }, [item]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página

    try {
      const data = {
        id: id,
        nome: formCliente.nome,
        email: formCliente.email,
        telefone: formCliente.telefone,
        codigo: formCliente.codigo,
        nascimento: ISODateToDate(new Date(`${formCliente.nascimento}T12:00:00`)),
      };

      const response = await axiosClient.put("/cliente/" + id, data);

      if (response.status < 205) {
        toast.success("Cliente alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        setFormCliente({} as Cliente); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo cliente
      } else {
        toast.error("Erro ao alterar cliente. " + response.statusText.toString());
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error("Falha ao conectar com o servidor. ");
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
              value={dateToISO(new Date(formCliente.nascimento))}
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

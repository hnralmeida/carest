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
import { Saidas } from "@/models/saidas";
import { dateToISO, ISODateToDate, moedaParaNumero } from "@/lib/utils";

interface EditSaidaProps {
  id: string;
  item: Saidas;
}

export default function EditSaida({ id, item }: EditSaidaProps) {
  const [open, setOpen] = useState(false);
  const [formSaidas, setFormSaidas] = useState<Saidas>({} as Saidas);

  useEffect(() => {
    setFormSaidas((prevData: any) => ({
      ...prevData,
      descricao: item.descricao,
    }));

    setFormSaidas((prevData: any) => ({
      ...prevData,
      valor: item.valor,
    }));

    setFormSaidas((prevData) => ({
      ...prevData,
      dataPagamento: dateToISO(new Date(item.dataPagamento)),
    }))

    setFormSaidas((prevData) => ({
      ...prevData,
      dataVencimento: dateToISO(new Date(item.dataVencimento)),
    }))
  }, [item]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    const dataVencimentoData = new Date(`${item.dataVencimento}T12:00:00`);
    const dataPagamentoData = new Date(`${item.dataPagamento}T12:00:00`);

    try {
      const data = {
        "id": id,
        "descricao": item.descricao,
        "valor": Number(moedaParaNumero(item.valor.toFixed(2))),
        "dataVencimento": ISODateToDate(dataVencimentoData),
        "dataPagamento": ISODateToDate(dataPagamentoData)
      };

      const response = await axiosClient.put("/saidas/" + id, data);

      if (response.status < 205) {
        alert("Saida alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        window.location.reload(); // Recarrega a página para exibir o novo Saida
      } else {
        alert("Erro ao alterar Saida. " + response.statusText.toString());
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
          <DialogTitle>Editar Saida</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="descricao">Descrição</Label>
            <Input
              id="descricao"
              value={formSaidas.descricao}
              onChange={(e) =>
                setFormSaidas((prevData) => ({
                  ...prevData,
                  descricao: e.target.value,
                }))
              }
              placeholder="Digite a"
              required
            />
          </div>
          <div>
            <Label htmlFor="valor">Valor</Label>
            <Input
              id="valor"
              value={formSaidas.valor}
              onChange={(e) =>
                setFormSaidas((prevData) => ({
                  ...prevData,
                  valor: Number(e.target.value),
                }))
              }
              placeholder="Digite a"
              required
            />
          </div>
          <div>
            <Label htmlFor="dataPagamento">Data de Pagamento</Label>
            <Input
              id="dataPagamento"
              type="date"
              value={
                formSaidas.dataPagamento
                  ? dateToISO(new Date(formSaidas.dataPagamento))
                  : ""
              }
              onChange={(e) =>
                setFormSaidas((prevData) => ({
                  ...prevData,
                  dataPagamento: e.target.value,
                }))
              }
              placeholder="Digite a data de nascimento do cliente"
              required
            />
          </div>
          <div>
            <Label htmlFor="dataVencimento">Data de Vencimento</Label>
            <Input
              id="dataVencimento"
              type="date"
              value={
                formSaidas.dataVencimento
                  ? dateToISO(new Date(formSaidas.dataVencimento))
                  : ""
              }
              onChange={(e) =>
                setFormSaidas((prevData) => ({
                  ...prevData,
                  dataVencimento: e.target.value,
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

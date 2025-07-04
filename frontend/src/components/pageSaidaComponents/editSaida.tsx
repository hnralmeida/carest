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
import { dateToISO, dateToReadable, ISODateToDate, moedaParaNumero } from "@/lib/utils";
import { useSaidasHook } from "@/hooks/useSaidas";
import { toast } from "sonner";

interface EditSaidaProps {
  id: string;
  item: Saidas;
}

export default function EditSaida({ id, item }: EditSaidaProps) {
  const [open, setOpen] = useState(false);
  const [formSaidas, setFormSaidas] = useState<Saidas>({} as Saidas);
  const [fornecedor, setFornecedor] = useState("");

  const { fornecedores, editarSaidas, listarFornecedores } = useSaidasHook();

  useEffect(() => {
    setFormSaidas((prevData: any) => ({
      ...prevData,
      descricao: item.descricao,
      valor: item.valor,
      fornecedor: item.fornecedor,
      dataPagamento: dateToISO(new Date(item.dataPagamento)),
      dataVencimento: dateToISO(new Date(item.dataVencimento)),
    }));

    setFornecedor(item.fornecedor.id);

  }, [item]);

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    const dataVencimentoData = new Date(`${formSaidas.dataVencimento}T12:00:00`);
    const dataPagamentoData = new Date(`${formSaidas.dataPagamento}T12:00:00`);

    const fornecedorSelecionado = fornecedores?.find((f) => f.id === fornecedor);

    try {
      const data = {
        "id": id,
        "descricao": formSaidas.descricao,
        "valor": Number(moedaParaNumero(formSaidas.valor.toFixed(2))),
        "fornecedor": fornecedorSelecionado,
        "dataVencimento": dataVencimentoData,
        "dataPagamento": dataPagamentoData,
      };

      console.log("Dados enviados:", data);

      const response = await axiosClient.put("/saidas/" + id, data);

      if (response.status < 205) {
        toast.success("Saida alterado com sucesso!");
        setOpen(false); // Fecha o modal após sucesso
        window.location.reload(); // Recarrega a página para exibir o novo Saida
      } else {
        toast.error("Erro ao alterar Saida. " + response.statusText.toString());
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error("Falha ao conectar com o servidor. ");
    }
  };

  useEffect(() => {
    const fetch = async () => {
      await listarFornecedores();
    }

    fetch();
  }, []);

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
            <Label htmlFor="fornecedor">Fornecedor</Label>
            <select
              id="fornecedor"
              value={fornecedor}
              onChange={(e) => setFornecedor(e.target.value)}
              className="w-full rounded-md border px-3 py-2 text-sm shadow-sm"
              required
            >
              <option value="">Selecione um fornecedor</option>
              {fornecedores?.map((f) => (
                <option key={f.id} value={f.id}>
                  {f.nome}
                </option>
              ))}
            </select>
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

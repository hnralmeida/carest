"use client";

import { useEffect, useState } from "react";
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
import { axiosClient } from "@/services/axiosClient";
import { toast } from "sonner";
import { formatarParaMoeda, ISODateToDate, moedaParaNumero } from "@/lib/utils";
import { useSaidasHook } from "@/hooks/useSaidas";

export default function AddSaida() {
  const [open, setOpen] = useState(false);
  const [descricao, setDescricao] = useState("");
  const [valor, setValor] = useState("");
  const [dataVencimento, setDataVencimento] = useState("");
  const [dataPagamento, setDataPagamento] = useState("");
  const [fornecedor, setFornecedor] = useState("");

  const { fornecedores, saidas, criarSaidas, listarFornecedores } = useSaidasHook();

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // Evita que o formulário recarregue a página
    const dataVencimentoData = new Date(`${dataVencimento}T12:00:00`);
    const dataPagamentoData = new Date(`${dataPagamento}T12:00:00`);

    try {
      if (!fornecedores) return toast.error("Nenhum fornecedor encontrado.");

      const selectedFornecedor = fornecedores.find((f) => f.id === fornecedor);

      const body = {
        descricao,
        valor: Number(moedaParaNumero(valor).toFixed(2)),
        dataVencimento: ISODateToDate(dataVencimentoData),
        dataPagamento: ISODateToDate(dataPagamentoData),
        fornecedor: selectedFornecedor
      }
      console.log("Dados enviados:", body);

      const response = await axiosClient.post("/saidas", body);

      if (response.status === 201) {
        toast.success("Saida adicionada")
        setOpen(false); // Fecha o modal após sucesso
        setDescricao(""); // Limpa o campo do formulário
        window.location.reload(); // Recarrega a página para exibir o novo Saida
      } else {
        toast.error("Erro ao adicionar Saida.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      toast.error("Falha ao conectar com o servidor.");
    }
  };

  const handleChangeValor = (e: React.ChangeEvent<HTMLInputElement>) => {
    const valorFormatado = formatarParaMoeda(e.target.value);
    setValor(valorFormatado);
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
          className="button-class items-center text-2xl"
          onClick={() => setOpen(true)}
        >
          <PlusCircle size={48} />
          Adicionar
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Adicionar Saida</DialogTitle>
        </DialogHeader>
        <form onSubmit={onFormSubmit} className="flex flex-col gap-4">
          <div>
            <Label htmlFor="descricao">Descrição</Label>
            <Input
              id="descricao"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
              placeholder="Digite uma descrição"
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
            <Label htmlFor="dataPagamento">Data de Vencimento</Label>
            <Input
              id="dataVencimento"
              type="date"
              value={dataVencimento}
              onChange={(e) => setDataVencimento(e.target.value)}
              placeholder="Digite a data de vencimento da saída"
              required
            />
          </div>
          <div>
            <Label htmlFor="dataPagamento">Data de Pagamento</Label>
            <Input
              id="dataPagamento"
              type="date"
              value={dataPagamento}
              onChange={(e) => setDataPagamento(e.target.value)}
              placeholder="Digite a data em que foi feito pagamento"
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

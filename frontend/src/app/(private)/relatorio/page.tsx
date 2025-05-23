"use client";

import React, { useEffect, useState } from "react";
import { columns } from "../../../components/pageRelatorioProdutoComponents/columns";
import { useRelatorioProdutoHook } from "@/hooks/useRelatorioProduto";
import { DataTable } from "@/components/pageRelatorioProdutoComponents/DataTable";
import { Printer } from "lucide-react";
import { toast, Toaster } from "sonner";

function Page() {
  const { loading, relatorioProduto, listarRelatorioProduto, relatoriosRelatorioProduto } = useRelatorioProdutoHook();

  const [dataInicio, setDataInicio] = useState<string | null>(null);
  const [dataFim, setDataFim] = useState<string | null>(null);

  useEffect(() => {
    const dataAtual = new Date();
    const dataInicioFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth(), 1);
    const dataFimFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth() + 1, dataAtual.getDay());

    setDataInicio(dataInicioFormatada.toISOString().split('T')[0]);
    setDataFim(dataFimFormatada.toISOString().split('T')[0]);

    listarRelatorioProduto(dataInicioFormatada.toISOString().split('T')[0], dataFimFormatada.toISOString().split('T')[0]).catch((res: any) => {
      toast.error(res);
    });
  }, []);

  const handlePrint = async () => {
    if (!dataInicio || !dataFim) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }
    relatoriosRelatorioProduto(dataInicio, dataFim).then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };


  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Relatório de Produto</h1>
        <button
          className="button-print"
          onClick={() => handlePrint()}
          disabled={loading}
        >
          <Printer className="size-4 mr-2" />
          Exportar
        </button>
      </div>
      {relatorioProduto ? (
        <DataTable columns={columns} data={relatorioProduto} />
      ) : (
        <p>Sem Resultados.</p>
      )}
    </div>
  );
}

export default Page;

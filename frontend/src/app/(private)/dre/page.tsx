"use client";

import React, { useEffect, useState } from "react";
import { columns } from "../../../components/pageDREComponents/columns";
import { DataTable } from "@/components/pageDREComponents/DataTable";
import { useDreHook } from "@/hooks/useDre";
import { toast, Toaster } from "sonner";
import { Printer } from "lucide-react";

function Page() {
  const { dre, listarDre, relatoriosDRE, loading } = useDreHook();
  const [dataInicio, setDataInicio] = useState<string | null>(null);
  const [dataFim, setDataFim] = useState<string | null>(null);

  useEffect(() => {
    const dataAtual = new Date();
    const dataInicioFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth(), 1);
    const dataFimFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth() + 1, dataAtual.getDay());

    setDataInicio(dataInicioFormatada.toISOString().split('T')[0]);
    setDataFim(dataFimFormatada.toISOString().split('T')[0]);

    listarDre(dataInicioFormatada.toISOString().split('T')[0], dataFimFormatada.toISOString().split('T')[0]).catch((res: any) => {
      toast.error(res);
    });
  }, []);

  const handlePrint = async () => {
    if (!dataInicio || !dataFim) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }
    relatoriosDRE(dataInicio, dataFim).then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };


  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">DRE</h1>
        <button
          className="button-print"
          onClick={() => handlePrint()}
          disabled={loading}
        >
          <Printer className="size-4 mr-2" />
          Exportar
        </button>
      </div>
      {dre ? (
        <DataTable columns={columns} data={dre} />
      ) : (
        loading ? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

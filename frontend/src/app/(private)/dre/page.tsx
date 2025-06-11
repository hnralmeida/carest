"use client";

import React, { useEffect, useState } from "react";
import { columns } from "../../../components/pageDREComponents/columns";
import { DataTable } from "@/components/pageDREComponents/DataTable";
import { useDreHook } from "@/hooks/useDre";
import { toast, Toaster } from "sonner";
import { Printer, Search } from "lucide-react";
import { ButtonVariant } from "@/components/button-variant";
import { formatarParaMoeda } from "@/lib/utils";

function Page() {
  const { dre, listarDre, relatoriosDRE, loading } = useDreHook();
  const [dataInicio, setDataInicio] = useState<string>('');
  const [dataFim, setDataFim] = useState<string>('');

  useEffect(() => {
    const dataAtual = new Date();
    const dataInicioFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth(), 1);
    const dataFimFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth() + 1, dataAtual.getDay());

    // Formatar datas para YYYY-MM-DD
    const inicio = dataInicioFormatada.toISOString().split('T')[0];
    const fim = dataFimFormatada.toISOString().split('T')[0];

    setDataInicio(inicio);
    setDataFim(fim);

    listarDre(inicio, fim).catch((res: any) => {
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

  const handleDataInicio = (data: string) => {
    setDataInicio(data)
  };

  const handleDataFim = (data: string) => {
    setDataFim(data)
  }

  const pressSearch = async () => {
    if (!dataInicio || !dataFim) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }
    listarDre(dataInicio, dataFim).then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  }

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
      <div className="flex flex-start gap-8 items-end w-full mb-4">
        <div>
          <p>Início</p>
          <input
            type="date"
            className="input-search"
            onChange={(e) => handleDataInicio(e.target.value)}
            value={dataInicio}
          />
        </div>
        <div>
          <p>Fim</p>
          <input
            type="date"
            className="input-search"
            onChange={(e) => handleDataFim(e.target.value)}
            value={dataFim}
          />
        </div>
        <ButtonVariant
          handlePress={pressSearch}
          loading={loading}
          text="Buscar"
          Img={Search}
        />
        <div className="flex justify-end w-full">
          {dre && dre.length > 0 ? (
            <p className="text-right text-xl w-auto input-search pr-4 pl-4">
              Saldo Anterior: {formatarParaMoeda(`${dre[0].saldoAnterior}`, true)}
            </p>
          ) : null}
        </div>
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

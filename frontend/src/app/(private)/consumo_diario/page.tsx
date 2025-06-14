"use client";

import React, { useEffect, useState } from "react";
import { useConsumoDiarioHook } from "@/hooks/useConsumoDiario";
import View from "@/components/pageConsumoDiarioComponents/view";
import { FileDown, Printer, Search } from "lucide-react";
import { toast, Toaster } from "sonner";
import { ButtonVariant } from "@/components/button-variant";

function Page() {
  const { consumoDiario, listarConsumoDiario, relatoriosConsumoDiario, loading } = useConsumoDiarioHook();

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

    listarConsumoDiario(inicio, fim).catch((res: any) => {
      toast.error(res);
    });
  }, []);

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
    listarConsumoDiario(dataInicio, dataFim).catch((res: any) => {
      toast.error(res);
    });
  }

  const handlePrint = async () => {
    if (!dataInicio || !dataFim) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }
    relatoriosConsumoDiario(dataInicio, dataFim).then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };


  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Consumo Diário</h1>
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
      </div>
      {consumoDiario ?
        <div className="flex justify-center items-center w-full mb-4">
          <img src={consumoDiario} alt="Consumo Diário" className="max-w-full h-auto object-contain" />
        </div>
        :
        <p>Sem Resultados.</p>
      }
    </div>
  );
}

export default Page;

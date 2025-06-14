"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesDiarioComponents/columns";
import { useClientesDiarioHook } from "@/hooks/useClientesDiario";
import { DataTable } from "@/components/pageClientesDiarioComponents/DataTable";
import { FileDown, Printer, Search } from "lucide-react";
import { toast, Toaster } from "sonner";
import { ButtonVariant } from "@/components/button-variant";

function Page() {
  const { loading, clientesDiario, listarClientesDiario, relatoriosClientesDiario, listarClientesDiarioPorDia } = useClientesDiarioHook();
  const [dataSelecionada, setDataSelecionada] = React.useState<string>('');

  useEffect(() => {

    const dataAtual = new Date();
    const dataAtualFormatada = new Date(dataAtual.getFullYear(), dataAtual.getMonth(), 1);

    // Formatar datas para YYYY-MM-DD
    const atual = dataAtualFormatada.toISOString().split('T')[0];

    setDataSelecionada(atual);

    listarClientesDiario(atual).catch((res: any) => {
      toast.error(res);
    });
  }, []);

  const handlePrint = async () => {
    if (!dataSelecionada) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }

    relatoriosClientesDiario(dataSelecionada).then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };

  const pressSearch = async () => {
    if (!dataSelecionada) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }

    listarClientesDiarioPorDia(dataSelecionada).catch((res: any) => {
      toast.error(res);
    });
  }

  const handleData = (data: string) => {
    setDataSelecionada(data)
  }


  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Clientes Diário</h1>
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
          <p>Data</p>
          <input
            type="date"
            className="input-search"
            onChange={(e) => handleData(e.target.value)}
            value={dataSelecionada}
          />
        </div>
        <ButtonVariant
          handlePress={pressSearch}
          loading={loading}
          text="Buscar"
          Img={Search}
        />
      </div>
      {clientesDiario ? (
        <DataTable columns={columns} data={clientesDiario} />
      ) : (
        <p>Sem Resultados.</p>
      )}
    </div>
  );
}

export default Page;

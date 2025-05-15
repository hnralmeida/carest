"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesDiarioComponents/columns";
import { useClientesDiarioHook } from "@/hooks/useClientesDiario";
import { DataTable } from "@/components/pageClientesDiarioComponents/DataTable";
import { FileDown, Printer, Search } from "lucide-react";
import { toast, Toaster } from "sonner";
import { useTicketMedioHook } from "@/hooks/useTicketMedio";
import { ButtonVariant } from "@/components/button-variant";

function Page() {
  const { loading, ticketMedio, listarTicketMedio, relatoriosTicketMedio, setDataInicio, setDataFim, ticketPorData } = useTicketMedioHook();
  const [dataInicioForm, setDataInicioForm] = React.useState<string>('');
  const [dataFimForm, setDataFimForm] = React.useState<string>('');

  useEffect(() => {
    listarTicketMedio().catch((res: any) => {
      toast.error(res);
    });
    const hoje = new Date();
    const yyyyMmDd = hoje.toISOString().split("T")[0];
    setDataFimForm(yyyyMmDd);
  }, []);

  const handlePrint = async () => {
    relatoriosTicketMedio().then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };

  const handleDataInicio = (data: string) => {
    setDataInicioForm(data)
    const partes = data.split('/');
    if (partes.length === 3) {
      const [dia, mes, ano] = partes;
      setDataInicio(`${ano}-${mes}-${dia}`); // Se for enviar ao backend no padrão ISO
    }
    setDataInicio('');
  };

  const handleDataFim = (data: string) => {
    setDataFimForm(data)
    const partes = data.split('/');
    if (partes.length === 3) {
      const [dia, mes, ano] = partes;
      setDataFim(`${ano}-${mes}-${dia}`); // Se for enviar ao backend no padrão ISO
    }
    setDataFim('');
  };

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Ticket Médio de Clientes</h1>
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
            value={dataInicioForm}
          />
        </div>
        <div>
          <p>Fim</p>
          <input
            type="date"
            className="input-search"
            onChange={(e) => handleDataFim(e.target.value)}
            value={dataFimForm}
          />
        </div>
        <ButtonVariant
          handlePress={() => ticketPorData()}
          loading={loading}
          text="Buscar"
          Img={Search}
        />
      </div>
      {ticketMedio ? (
        <DataTable columns={columns} data={ticketMedio} />
      ) : (
        loading ? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

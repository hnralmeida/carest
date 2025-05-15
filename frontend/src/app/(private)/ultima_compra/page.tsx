"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesDiarioComponents/columns";
import { DataTable } from "@/components/pageClientesDiarioComponents/DataTable";
import { Printer, Search } from "lucide-react";
import { toast, Toaster } from "sonner";
import { ButtonVariant } from "@/components/button-variant";
import { useUltimaCompraHook } from "@/hooks/useUltimaCompra";

function Page() {
  const { loading, ultimaCompra, listarUltimaCompra, relatoriosUltimaCompra, setDataSelecionada, ultimaCompraPorData } = useUltimaCompraHook();
  const [dataForm, setDataForm] = React.useState<string>('');

  useEffect(() => {
    listarUltimaCompra().catch((res: any) => {
      toast.error(res);
    });
    const hoje = new Date();
    const yyyyMmDd = hoje.toISOString().split("T")[0];
    setDataForm(yyyyMmDd);
  }, []);

  const handlePrint = async () => {
    relatoriosUltimaCompra().then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };

  const handleData = (data: string) => {
    setDataForm(data)
    const partes = data.split('/');
    if (partes.length === 3) {
      const [dia, mes, ano] = partes;
      setDataSelecionada(`${ano}-${mes}-${dia}`); // Se for enviar ao backend no padrão ISO
    }
    setDataSelecionada('');
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
          <p>Selecione a data</p>
          <input
            type="date"
            className="input-search"
            onChange={(e) => handleData(e.target.value)}
            value={dataForm}
          />
        </div>
        <ButtonVariant
          handlePress={() => ultimaCompraPorData()}
          loading={loading}
          text="Buscar"
          Img={Search}
        />
      </div>
      {ultimaCompra ? (
        <DataTable columns={columns} data={ultimaCompra} />
      ) : (
        loading ? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

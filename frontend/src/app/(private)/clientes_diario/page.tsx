"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesDiarioComponents/columns";
import { useClientesDiarioHook } from "@/hooks/useClientesDiario";
import { DataTable } from "@/components/pageClientesDiarioComponents/DataTable";
import { FileDown, Printer } from "lucide-react";
import { toast, Toaster } from "sonner";

function Page() {
  const { loading, clientesDiario, listarClientesDiario, relatoriosClientesDiario } = useClientesDiarioHook();

  useEffect(() => {
    listarClientesDiario().catch((res: any) => {
          toast.error(res);
        });
  }, []);

  const handlePrint = async () => {
    relatoriosClientesDiario().then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };


  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center"/>
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
      {clientesDiario ? (
        <DataTable columns={columns} data={clientesDiario} />
      ) : (
        <p>Sem Resultados.</p>
      )}
    </div>
  );
}

export default Page;

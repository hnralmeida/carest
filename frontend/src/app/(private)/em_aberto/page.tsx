"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesEmAbertoComponents/columns";
import { DataTable } from "@/components/pageClientesEmAbertoComponents/DataTable";
import { Printer, Search } from "lucide-react";
import { toast, Toaster } from "sonner";
import { useClientesEmAbertoHook } from "@/hooks/useClientesEmAberto";
import { ButtonVariant } from "@/components/button-variant";

function Page() {
  const { loading, clientesEmAberto, listarClientesEmAberto, relatoriosClientesEmAberto } = useClientesEmAbertoHook();

  useEffect(() => {
    listarClientesEmAberto().catch((res: any) => {
      toast.error(res);
    });
  }, []);

  const handlePrint = async () => {
    relatoriosClientesEmAberto().then(() => {
      toast.success("Relatório gerado com sucesso!");
    }).catch((res: any) => {
      toast.error(res);
    });
  };

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Clientes em aberto</h1>
        <button
          className="button-print"
          onClick={() => handlePrint()}
          disabled={loading}
        >
          <Printer className="size-4 mr-2" />
          Exportar
        </button>
      </div>
      
      {clientesEmAberto ? (
        <DataTable columns={columns} data={clientesEmAberto} />
      ) : (
        loading ? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

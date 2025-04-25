"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageClientesDiarioComponents/columns";
import { useRelatoriosHook } from "@/hooks/useRelatorios";
import { DataTable } from "@/components/pageClientesDiarioComponents/DataTable";

// async function getData(): Promise<Tela[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Tela, b: Tela) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { clientesDiario, listarClientesDiario } = useRelatoriosHook();

  useEffect(() => {
    listarClientesDiario();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Clientes Diário</h1>
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

"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/telaComponents/columns";
import AddTela from "@/components/telaComponents/addTela";
import { DataTable } from "@/components/telaComponents/DataTable";
import { useTelaHook } from "@/hooks/useTela";

// async function getData(): Promise<Tela[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Tela, b: Tela) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { telas, listarTelas } = useTelaHook();

  useEffect(() => {
    listarTelas();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Telas</h1>
        <div className="justify-end">
          <AddTela />
        </div>
      </div>
      {telas ? (
        <DataTable columns={columns} data={telas} />
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
}

export default Page;

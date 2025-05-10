"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageFuncionarioComponents/columns";
import AddFuncionario from "@/components/pageFuncionarioComponents/addFuncionario";
import { DataTable } from "@/components/pageFuncionarioComponents/DataTable";
import { useFuncionarioHook } from "@/hooks/useFuncionario";

// async function getData(): Promise<Funcionario[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Funcionario, b: Funcionario) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { funcionarios, listarFuncionarios } = useFuncionarioHook();

  useEffect(() => {
    listarFuncionarios();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Funcionários</h1>
        <div className="justify-end">
          <AddFuncionario />
        </div>
      </div>
      {funcionarios ? (
        <DataTable columns={columns} data={funcionarios} />
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
}

export default Page;

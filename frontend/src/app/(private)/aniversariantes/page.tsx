"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageAniversariantesComponents/columns";
import { DataTable } from "@/components/pageAniversariantesComponents/DataTable";
import { useAniversariantesHook } from "@/hooks/useAniversariantes";

// async function getData(): Promise<Tela[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Tela, b: Tela) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { aniversariantes, listarAniversariantes } = useAniversariantesHook();

  useEffect(() => {
    listarAniversariantes();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Aniversariantes</h1>
      </div>
      {aniversariantes ? (
        <DataTable columns={columns} data={aniversariantes} />
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
}

export default Page;

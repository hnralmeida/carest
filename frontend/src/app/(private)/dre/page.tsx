"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageAniversariantesComponents/columns";
import { DataTable } from "@/components/pageAniversariantesComponents/DataTable";
import { useDreHook } from "@/hooks/useDre";

function Page() {
  const { dre, listarDre, loading } = useDreHook();

  useEffect(() => {
    listarDre();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Aniversariantes</h1>
      </div>
      {dre ? (
        <DataTable columns={columns} data={dre} />
      ) : (
        loading? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

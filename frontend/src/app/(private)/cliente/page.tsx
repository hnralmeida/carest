'use client';

import React, { useEffect } from "react";
import {
  columns,
} from "../../../components/pageClienteComponents/columns";
import AddCliente from "@/components/pageClienteComponents/addCliente";
import { DataTable } from "@/components/pageClienteComponents/DataTable";
import { useClienteHook } from "@/hooks/useCliente";

const page = () => {

  const { cliente, listarCliente, loading } = useClienteHook();

  useEffect(() => {
    listarCliente();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Clientes</h1>
        <div className="justify-end">
          <AddCliente />
        </div>
      </div>
      {cliente ? (
        <DataTable columns={columns} data={cliente} />
      ) : (
        loading ? <p>Carregando...</p> : <p>Sem resultados.</p>
      )}
    </div>
  );
};

export default page;

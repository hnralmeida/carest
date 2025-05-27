"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageSaidaComponents/columns";
import { DataTable } from "../../../components/pageSaidaComponents/DataTable";
import AddSaida from "@/components/pageSaidaComponents/addSaida";
import { axiosClient } from "@/services/axiosClient";
import { Saidas } from "@/models/saidas";
import { useSaidasHook } from "@/hooks/useSaidas";

const page = () => {

  const { saidas, listarSaidas } = useSaidasHook();

  useEffect(() => {
    listarSaidas();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Saídas</h1>
        <div className="justify-end">
          <AddSaida />
        </div>
      </div>
      {saidas ? (
        <DataTable columns={columns} data={saidas} />
      ) : (
        <p>Sem resultados</p>
      )}
    </div>
  );
};

export default page;

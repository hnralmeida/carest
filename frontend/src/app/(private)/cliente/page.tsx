import React from "react";
import {
  columns,
  Cliente,
} from "../../../components/pageClienteComponents/columns";
import { axiosClient } from "@/services/axiosClient";
import AddCliente from "@/components/pageClienteComponents/addCliente";
import { DataTable } from "@/components/pageClienteComponents/DataTable";

async function getData(): Promise<Cliente[]> {
  const response = await axiosClient.get("/cliente");
  return response.data.sort((a: Cliente, b: Cliente) =>
    a.nome.localeCompare(b.nome)
  );
}

const page = async () => {

  const data = await getData();
  
  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Clientes</h1>
        <div className="justify-end">
          <AddCliente />
        </div>
      </div>
      <DataTable columns={columns} data={data} />
    </div>
  );
};

export default page;

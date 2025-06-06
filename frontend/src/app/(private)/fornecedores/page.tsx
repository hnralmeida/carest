import React from "react";
import {
  columns,
  Fornecedor,
} from "../../../components/pageFornecedorComponents/columns";
import { DataTable } from "../../../components/pageFornecedorComponents/DataTable";
import AddFornecedor from "@/components/pageFornecedorComponents/addFornecedor";
import { axiosClient } from "@/services/axiosClient";
import { Toaster } from "sonner";

async function getData(): Promise<Fornecedor[]> {
  const response = await axiosClient.get("/fornecedor");
  return response.data.sort((a: Fornecedor, b: Fornecedor) =>
    a.nome.localeCompare(b.nome)
  );
}

const page = async () => {

  const data = await getData();
  
  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <Toaster richColors position="top-center" closeButton />
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Fornecedores</h1>
        <div className="justify-end">
          <AddFornecedor />
        </div>
      </div>
      <DataTable columns={columns} data={data} />
    </div>
  );
};

export default page;

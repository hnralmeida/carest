import React from "react";
import {
  columns,
  Fornecedor,
} from "../../../components/fornecedorComponents/columns";
import { DataTable } from "../../../components/fornecedorComponents/DataTable";
import AddFornecedor from "@/components/fornecedorComponents/addButton";

async function getData(): Promise<Fornecedor[]> {
  return new Array(50).fill(null).map(() => ({
    id: Math.random().toString(36).substring(7),
    descricao: "fornecedor-" + Math.random().toString(36).substring(7),
  }));
}

const page = async () => {
  const data = await getData();

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
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

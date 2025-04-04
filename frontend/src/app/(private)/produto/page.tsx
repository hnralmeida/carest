"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/produtoComponents/columns";
import AddProduto from "@/components/produtoComponents/addProduto";
import { DataTable } from "@/components/produtoComponents/DataTable";
import { useProdutoHook } from "@/hooks/useProdutos";

// async function getData(): Promise<Produto[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Produto, b: Produto) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { produtos, listarProdutos } = useProdutoHook();

  useEffect(() => {
    listarProdutos();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Produtos</h1>
        <div className="justify-end">
          <AddProduto />
        </div>
      </div>
      {produtos ? (
        <DataTable columns={columns} data={produtos} />
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
}

export default Page;

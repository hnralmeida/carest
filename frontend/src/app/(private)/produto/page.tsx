"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/produtoComponents/columns";
import AddProduto from "@/components/produtoComponents/addProduto";
import { DataTable } from "@/components/produtoComponents/DataTable";
import { useProdutoHook } from "@/hooks/useProdutos";
import ButtonEditBalanca from "@/components/editBalanca";

// async function getData(): Promise<Produto[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Produto, b: Produto) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { produtos, listarProdutos, balanca, obterBalanca } = useProdutoHook();

  useEffect(() => {
    listarProdutos();
    obterBalanca();
  }, []);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <h1 className="text-2xl font-bold">Self-Service</h1>
      <div className="flex w-full justify-center gap-x-4 items-center mb-[16px]">
        <p className="border border-[var(--black-color)] rounded-[16px] px-[32px] py-[16px] font-bold">
          {new Intl.NumberFormat('pt-BR', {
            style: 'currency',
            currency: 'BRL',
          }).format(balanca)}
        </p>

        {balanca&&<ButtonEditBalanca preco={balanca} />}
      </div>
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

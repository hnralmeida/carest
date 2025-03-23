import React from "react";
import {
  columns,
  Funcionario,
} from "../../../components/funcionarioComponents/columns";
import { axiosClient } from "@/services/axiosClient";
import AddFuncionario from "@/components/funcionarioComponents/addFuncionario";
import { DataTable } from "@/components/funcionarioComponents/DataTable";

async function getData(): Promise<Funcionario[]> {
  const response = await axiosClient.get("/usuario");
  return response.data.sort((a: Funcionario, b: Funcionario) =>
    a.nome.localeCompare(b.nome)
  );
}

const page = async () => {

  const data = await getData();
  
  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Funcionários</h1>
        <div className="justify-end">
          <AddFuncionario />
        </div>
      </div>
      <DataTable columns={columns} data={data} />
    </div>
  );
};

export default page;

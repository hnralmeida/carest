"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageTelaComponents/columns";
import AddTela from "@/components/pageTelaComponents/addTela";
import { DataTable } from "@/components/pageTelaComponents/DataTable";
import { useTelaHook } from "@/hooks/useTela";
import { useSession } from "next-auth/react";

// async function getData(): Promise<Tela[]> {
//   const response = await axiosClient.get("/usuario");
//   return response.data.sort((a: Tela, b: Tela) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

function Page() {
  const { telas, listarTelas } = useTelaHook();
  const { data: session } = useSession();
  const user = session?.user;

  useEffect(() => {
    if (user?.id) {
      listarTelas(user.id);
    }
  }, [user]);

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Telas</h1>
        <div className="justify-end">
          <AddTela />
        </div>
      </div>
      {telas ? (
        <DataTable columns={columns} data={telas} />
      ) : (
        <p>Sem resultados.</p>
      )}
    </div>
  );
}

export default Page;

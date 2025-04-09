import React from "react";
import VendasView from "@/components/pageVendasComponents/view";

// async function getData(): Promise<Cliente[]> {
//   const response = await axiosClient.get("/cliente");
//   return response.data.sort((a: Cliente, b: Cliente) =>
//     a.nome.localeCompare(b.nome)
//   );
// }

const page = async () => {

  // const data = await getData();

  return (
    <div className="container rounded-md border mx-auto py-[64px] px-[64px] content-bg h-full">
      <VendasView />
    </div>
  );
};

export default page;

"use client";

import React, { useEffect } from "react";
import { columns } from "../../../components/pageAniversariantesComponents/columns";
import { DataTable } from "@/components/pageAniversariantesComponents/DataTable";
import { useAniversariantesHook } from "@/hooks/useAniversariantes";
import { ButtonVariant } from "@/components/button-variant";
import { toast } from "sonner";
import { Search } from "lucide-react";

function Page() {
  const { aniversariantes, listarAniversariantes, loading } = useAniversariantesHook();
  const [dataSelecionada, setDataSelecionada] = React.useState<string>('');


  useEffect(() => {
    const hoje = new Date();
    const mesAtual = hoje.getMonth() + 1; // getMonth() retorna 0-11, então adicionamos 1
    setDataSelecionada(String(mesAtual)); // Formatar data para YYYY-MM-DD

    listarAniversariantes(mesAtual);
  }, []);

  const handleData = (data: string) => {
    setDataSelecionada(data)
  }

  const pressSearch = async () => {
    if (!dataSelecionada) {
      toast.error("Data de início e fim são obrigatórias");
      return;
    }

    listarAniversariantes(Number(dataSelecionada)).catch((res: any) => {
      toast.error(res);
    });
  }

  return (
    <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
      <div className="flex justify-between items-center w-full mb-4">
        <h1 className="text-2xl font-bold">Aniversariantes</h1>
      </div>
      <div className="flex flex-start gap-8 items-end w-full mb-4">
        <div>
          <p>Mês</p>
          <select
            className="input-search"
            onChange={(e) => handleData(e.target.value)}
            value={dataSelecionada}
          >
            <option value="">Selecione</option>
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
            <option value="4">Abril</option>
            <option value="5">Maio</option>
            <option value="6">Junho</option>
            <option value="7">Julho</option>
            <option value="8">Agosto</option>
            <option value="9">Setembro</option>
            <option value="10">Outubro</option>
            <option value="11">Novembro</option>
            <option value="12">Dezembro</option>
          </select>
        </div>
        <ButtonVariant
          handlePress={pressSearch}
          loading={loading}
          text="Buscar"
          Img={Search}
        />
      </div>
      {aniversariantes ? (
        <DataTable columns={columns} data={aniversariantes} />
      ) : (
        <p>Sem resultados</p>
      )}
    </div>
  );
}

export default Page;

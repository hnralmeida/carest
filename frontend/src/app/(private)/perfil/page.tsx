"use client";

import React, { useEffect, useState } from "react";
import { columns } from "../../../components/pageFuncionarioComponents/columns";
import AddFuncionario from "@/components/pageFuncionarioComponents/addFuncionario";
import { DataTable } from "@/components/pageFuncionarioComponents/DataTable";
import { useFuncionarioHook } from "@/hooks/useFuncionario";
import { useSession } from "next-auth/react";

function Page() {
    const [nome, setNome] = useState("");

    const { data: session } = useSession()

    useEffect(() => {
        setNome(session?.user?.nome || "");
    }, [session]);

    return (
        <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
            <div className="flex justify-between items-center w-full mb-4">
                <h1 className="text-2xl font-bold">{nome}</h1>
            </div>
            
        </div>
    );
}

export default Page;

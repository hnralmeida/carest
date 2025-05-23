import { useConsumoDiarioHook } from "@/hooks/useConsumoDiario";
import { useEffect, useState } from "react";

const View = () => {
    const { consumoDiario } = useConsumoDiarioHook();

    return (
        <div className="container rounded-md border mx-auto my-16 py-4 px-4 content-bg">
            <h1 className="text-2xl font-bold">Consumo Diário</h1>
            {consumoDiario ?
                <img src={consumoDiario} alt="Consumo Diário" className="w-full h-auto" />
                :
                <p>Sem Resultados.</p>
            }
        </div>
    );
}

export default View;
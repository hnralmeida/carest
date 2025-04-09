"use client";

import React from "react";
import { Radio } from "lucide-react";
import { ItemVenda } from "./item";
import { useVendasHook } from "@/hooks/useVendas";
import { formatarParaMoeda } from "@/lib/utils";

const VendasView = () => {

    const { buscarCliente, buscarProduto, resetarVenda, cliente, produtos } = useVendasHook();

    const [codigoLido, setCodigoLido] = React.useState("");

    const timeoutRef = React.useRef<NodeJS.Timeout | null>(null);

    React.useEffect(() => {
        const fetch = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));
            console.log(codigoLido.trim())
            if (codigoLido.trim() == "123456") {
                resetarVenda();
                setCodigoLido("");
                return;
            }

            if (codigoLido.length > 0) {
                if (cliente?.id)
                    buscarProduto(codigoLido)
                else
                    buscarCliente(codigoLido)
            }
            setCodigoLido("");
        }

        const handleKeyPress = (e: KeyboardEvent) => {

            if (e.key === "Enter") fetch();

            // Adiciona o caractere à string
            setCodigoLido((prev) => prev + e.key);

            // Limpa o código caso o usuário pare de digitar por mais de 1s
            if (timeoutRef.current) {
                clearTimeout(timeoutRef.current);
            }
            timeoutRef.current = setTimeout(() => {
                setCodigoLido("");
            }, 1000); // tempo de "expiração" do código lido
        };

        window.addEventListener("keydown", handleKeyPress);

        return () => {
            window.removeEventListener("keydown", handleKeyPress);
            if (timeoutRef.current) {
                clearTimeout(timeoutRef.current);
            }
        };
    }, [codigoLido]);

    function ClienteView() {
        return (
            <div className="flex flex-col justify-center items-start w-full h-[128px] gap-[8px] mb-4 px-[64px]">
                <p className="font-semibold">{String(cliente.nome)}</p>
                <p className="font-semibold">Saldo: {formatarParaMoeda(cliente.saldo)}</p>
                <p className="font-semibold">Limite: {formatarParaMoeda(cliente.limite)}</p>
            </div>
        )
    }

    function PulseView() {
        return (
            <div className="flex justify-center animate-pulse items-center w-full h-[128px] mb-4">
                <Radio size={48} />
            </div>
        )
    }

    return (
        <>
            <div className="rounded-md overflow-x-auto">
                <h1 className="text-2xl font-bold">Cliente</h1>
                {cliente?.id ? <ClienteView /> : <PulseView />}
            </div>

            <div className="rounded-md overflow-x-auto">
                <h1 className="text-2xl font-bold">Produtos</h1>
                <div className="flex flex-col mt-2 gap-[16px] mx-[16px]">
                    {produtos.length > 0 ? produtos.map((item, index) => (
                        <ItemVenda
                            key={index}
                            id={item.id}
                            nome={item.nome}
                            preco={item.valor}
                            quantidade={2}
                        />
                    )) : (
                        <div className="flex flex-row gap-[16px]">
                            <p className="flex flex-col justify-center items-center w-[164px] h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]" />
                            <p className="flex flex-col justify-center items-center w-full h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]" />
                            <p className="flex flex-col justify-center items-center w-full h-[48px] mb-4 border rounded-md p-4 border-[var(--color-black)] bg-[var(--color-white)]" />
                        </div>
                    )}
                </div>
            </div>
        </>
    )
}

export default VendasView;
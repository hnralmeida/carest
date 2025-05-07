"use client";

import React from "react";
import { Radio } from "lucide-react";
import { useVendasHook } from "@/hooks/useVendas";
import { formatarParaMoeda } from "@/lib/utils";
import { toast, Toaster } from "sonner";
import OptionButtonLimite from "./optionLimite";
import OptionButtonSaldo from "./optionSaldo";
import OptionButtonBloquear from "./optionBloquear";
import { useCreditoHook } from "@/hooks/useCredito";

const CreditoView = () => {

    const { buscarCliente, cliente } = useCreditoHook();

    const [codigoLido, setCodigoLido] = React.useState("");

    const timeoutRef = React.useRef<NodeJS.Timeout | null>(null);

    React.useEffect(() => {
        const fetch = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));

            if (codigoLido.length > 0) {
                try {
                    console.log(codigoLido)
                    //retira tudo que não for numeros de codigoLido
                    const codigoLidoLimpo = codigoLido.replace(/\D/g, "");
                    await buscarCliente(codigoLidoLimpo)
                } catch (e: any) {
                    if (e.response.status == 404) {
                        toast.error("Cliente não encontrado")
                    }
                    else if (e.response.status == 500) {
                        toast.error("Erro interno do servidor")
                    }
                    else if (e.response.status == 400) {
                        toast.error("Código inválido")
                    }
                    else if (e.response.status == 401) {
                        toast.error("Código já utilizado")
                    }
                }
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
                <p className="font-semibold">Saldo: {formatarParaMoeda(String(cliente.saldo), true)}</p>
                <p className="font-semibold">Limite: {formatarParaMoeda(String(cliente.limite), true)}</p>
                <p className="font-semibold">Codigo: {String(cliente.codigo)}</p>
            </div>
        )
    }

    function PulseView() {
        return (
            <div className="flex justify-center animate-pulse items-center w-full h-[164px] mb-4">
                <Radio size={48} />
            </div>
        )
    }

    return (
        <>
            <div className="rounded-md overflow-x-auto h-[256px]">
                {!cliente?.id && <h1 className="text-2xl">Aproxime o cartão da leitora</h1>}
                {cliente?.id && <h1 className="text-2xl">Cliente</h1>}
                {cliente?.id ? <ClienteView /> : <PulseView />}
            </div>

            <div className="rounded-md overflow-x-auto">
                <h1 className="text-2xl">Gerenciar o Cartão</h1>
                <div className="flex flex-row justify-around items-center w-full h-[128px] gap-[8px] mb-4 px-[64px]">
                    <OptionButtonLimite />
                    <OptionButtonSaldo />
                    <OptionButtonBloquear />
                </div>
            </div>
            <Toaster richColors position="top-center" />
        </>
    )
}

export default CreditoView;
"use client";

import React from "react";
import { Radio } from "lucide-react";
import { formatarParaMoeda } from "@/lib/utils";
import { toast, Toaster } from "sonner";
import { useAcessoHook } from "@/hooks/useAcesso";

const AcessoView = () => {

    const { buscarAcesso, cliente, saidaCliente } = useAcessoHook();

    const [codigoLido, setCodigoLido] = React.useState("");
    const [saida, setSaida] = React.useState("");
    const [perform, setPerform] = React.useState("ENTRADA");
    const [validarSaldo, setValidarSaldo] = React.useState(false);

    const timeoutRef = React.useRef<NodeJS.Timeout | null>(null);

    React.useEffect(() => {
        const fetch = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));

            if (codigoLido.length > 0) {
                try {
                    console.log(codigoLido)
                    //retira tudo que não for numeros de codigoLido
                    const codigoLidoLimpo = codigoLido.replace(/\D/g, "");
                    setSaida(codigoLidoLimpo);
                    await buscarAcesso(codigoLidoLimpo)

                    if (cliente?.saldo < 0) {
                        let valor = Number(cliente.limite) + Number(cliente.saldo);
                        if (Number(valor) < 0) {
                            setValidarSaldo(false);
                            setPerform("ACESSO NEGADO");
                            toast.error("Saldo insuficiente para acesso");
                        }
                        setValidarSaldo(true);
                    } else {
                        setValidarSaldo(true);
                        setPerform("ENTRADA")
                    }

                } catch (e: any) {
                    console.log(e)
                    if (e.response.status == 404) {
                        toast.error("Cliente não encontrado")
                    }
                    else if (e.response.status == 500) {
                        toast.error("Erro interno do servidor")
                    }
                    else if (e.response.status == 401) {
                        toast.error("Código já utilizado")
                    }
                    else if (e.response.status == 400) {
                        const codigoLidoLimpo = codigoLido.replace(/\D/g, "");
                        try {
                            await saidaCliente(codigoLidoLimpo)
                            setPerform("SAÍDA")
                        } catch (e: any) {
                            console.log(e)
                            if (e.response.status == 404) {
                                toast.error("Cliente não encontrado")
                            }
                            else if (e.response.status == 500) {
                                toast.error("Erro interno do servidor")
                            }
                            else if (e.response.status == 401) {
                                toast.error("Código já utilizado")
                            } else {
                                toast.error(e.response.data)
                            }
                        }
                    }
                    else {
                        toast.error(e.response.data)
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

    function AcessoNegadoView() {
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
                {cliente?.id && <h1 className={`text-2xl ${perform==='ACESSO NEGADO' ? 'text-red-500' : ''}`}>{perform}</h1>}
                {cliente?.id ? (validarSaldo ? <ClienteView /> : <AcessoNegadoView />) : <PulseView />}
            </div>

            <Toaster richColors position="top-center" />
        </>
    )
}

export default AcessoView;
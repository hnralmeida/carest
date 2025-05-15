"use client";

import React from "react";
import { Radio } from "lucide-react";
import { ItemVenda } from "./item";
import { useVendasHook } from "@/hooks/useVendas";
import { formatarParaMoeda } from "@/lib/utils";
import { toast, Toaster } from "sonner";

const VendasView = () => {

    const { buscarCliente, buscarProduto, resetarVenda, registrarVenda, cliente, produtos } = useVendasHook();

    const [codigoLido, setCodigoLido] = React.useState("");

    const timeoutRef = React.useRef<NodeJS.Timeout | null>(null);

    React.useEffect(() => {
        const fetch = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));
            if (codigoLido.trim() == "123456") {
                resetarVenda();
                setCodigoLido("");
                return;
            }

            if (codigoLido.trim() == "9000") {
                registrarVenda().then(() => {
                    toast.success("Venda registrada com sucesso")
                })
                setCodigoLido("");
                return;
            }

            if (codigoLido.length > 0) {
                try {
                    if (cliente?.id)
                        await buscarProduto(codigoLido)
                    else
                        await buscarCliente(codigoLido)
                } catch (e: any) {
                    if (e.response.status == 404 && cliente?.id) {
                        toast.error("Produto não encontrado")
                    } else if (e.response.status == 404) {
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

            // Impede Ctrl+J de abrir a aba de downloads
            if (e.ctrlKey && e.key.toLowerCase() === "j") {
                e.preventDefault();
                return; // se quiser ignorar completamente essa combinação
            }

            // Só adiciona à leitura se não for uma tecla ignorada
            const ignoredKeys = ["Control", "j"];
            if (!ignoredKeys.includes(e.key)) {
                setCodigoLido((prev) => prev + e.key);
            }
            
            if (e.key === "Enter") {
                fetch();
            }

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
                            id={item.produto.id}
                            nome={item.produto.nome}
                            preco={item.valor}
                            quantidade={item.quantidade}
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
            <Toaster richColors position="top-center" />
        </>
    )
}

export default VendasView;
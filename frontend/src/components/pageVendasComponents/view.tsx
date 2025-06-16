"use client";

import React from "react";
import { Radio } from "lucide-react";
import { ItemVenda } from "./item";
import { useVendasHook } from "@/hooks/useVendas";
import { formatarParaMoeda } from "@/lib/utils";
import { toast, Toaster } from "sonner";
import InserirPeso from "./pesoInserir";

const VendasView = () => {

    const { buscarCliente, buscarProduto, resetarVenda, registrarVenda, cliente, produtos, inserirBalanca } = useVendasHook();

    const [codigoLido, setCodigoLido] = React.useState("");

    const [open, setOpen] = React.useState(false);

    const timeoutRef = React.useRef<NodeJS.Timeout | null>(null);

    React.useEffect(() => {
        const fetchState = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));
            if (codigoLido.trim() == "123456") {
                resetarVenda();
                setCodigoLido("");
                return;
            }

            console.log("Código lido:", codigoLido.trim());

            // Código da balanca vai ser 10004800
            if (codigoLido.trim() == "10004800") {
                // const res = await fetch('/api/peso');
                // const data = await res.json();
                // console.log(data);
                setOpen(true);

                setCodigoLido("");

                return
            }

            if (codigoLido.trim() == "9000") {
                registrarVenda().then(() => {
                    toast.success("Venda registrada com sucesso")
                }).catch((e: any) => {
                    if (e.response.status == 404) {
                        toast.error("Cliente não encontrado")
                    } else if (e.response.status == 409) {
                        toast.error("Cliente não possui saldo suficiente")
                        produtos.pop();
                    } else if (e.response.status == 500) {
                        toast.error("Erro interno do servidor")
                    } else {
                        toast.error(e.response.status)
                    }
                });

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
                    console.log("Error\n", e);
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
                fetchState();
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

    function confirmarBalanca(peso: string) {
        if (!peso || peso.trim() === "") {
            toast.error("Peso não pode ser vazio");
            return;
        }
        console.log("Inserindo balança com peso: ", peso);
        inserirBalanca(peso).catch((e: any) => {
            if (e.response.status == 404) {
                toast.error("Balança não encontrada");
            }
            else if (e.response.status == 500) {
                toast.error("Erro interno do servidor");
            }
            else if (e.response.status == 400) {
                toast.error("Código inválido");
            }
            else if (e.response.status == 401) {
                toast.error("Código já utilizado");
            }
        }
        );
    }

    function ClienteView() {
        const saldo = Number(cliente.saldo);
        const limite = Number(cliente.limite);
        const totalDisponivel = saldo + limite;
        const isNegativo = totalDisponivel < 0;

        return (
            <div className="flex flex-col justify-center items-start w-full h-[128px] gap-[8px] mb-4 px-[64px]">
                <p className="font-semibold">{String(cliente.nome)}</p>
                <p className={`font-semibold ${isNegativo ? 'text-red-500' : ''}`}>
                    Saldo: {formatarParaMoeda(String(saldo), true)}
                </p>
                <p className={`font-semibold ${isNegativo ? 'text-red-500' : ''}`}>
                    Limite: {formatarParaMoeda(String(limite), true)}
                </p>
            </div>
        );
    }


    function PulseView() {
        return (
            <div className="flex justify-center animate-pulse items-center w-full h-[128px] mb-4">
                <Radio size={48} />
            </div>
        )
    }

    function calcular() {
        let total = 0;
        produtos.forEach((item) => {
            total += item.valor * item.quantidade;
        });
        return formatarParaMoeda(String(total), true);
    }

    return (
        <>
            <div className="rounded-md overflow-x-auto">
                <h1 className="text-2xl font-bold">Cliente</h1>
                {cliente?.id ? <ClienteView /> : <PulseView />}
            </div>
            <InserirPeso
                open={open}
                feedback={(peso: string) => {
                    confirmarBalanca(peso)
                }}
                onClose={() => {
                    setCodigoLido("");
                    setOpen(false);
                }}
            />;

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
                    <div className="flex flex-row gap-[16px] justify-end">
                        <p>TOTAL: {calcular()}</p>
                    </div>
                </div>
            </div>
            <Toaster richColors position="top-center" />
        </>
    )
}

export default VendasView;
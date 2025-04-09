"use client";

import * as React from "react";
import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarRail,
} from "@/components/ui/sidebar";
import { GalleryVerticalEnd, LogOut, User } from "lucide-react";
import { Button } from "./ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@radix-ui/react-dropdown-menu";
import { signOut } from "next-auth/react";
import { useSession } from "next-auth/react"

export function AppSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {
  
  const { data: session } = useSession()

  React.useEffect(() => {
    const fetchData = async () => {
      console.log(session)
    }
    fetchData();
  }, []);
  
  // Estado para controlar os itens ativos
  const [active, setActive] = React.useState<Record<string, boolean>>({
    dashboard: false,
    acesso: false,
    venda: false,
    produto: false,
    cliente: false,
    credito: false,
    funcionarios: false,
    em_aberto: false,
    consumo_diario: false,
    clientes_diario: false,
    aniversariantes: false,
    ticket: false,
    ultima_compra: false,
    relatorio: false,
    fornecedores: false,
    controle_saida: false,
    dre: false,
    tela: false,
  });

  // Função para resetar o estado
  const resetActive = () => {
    setActive((prev) =>
      Object.keys(prev).reduce((acc, key) => ({ ...acc, [key]: false }), {})
    );
  };

  const data = {
    versions: ["Carest"],
    navMain: [
      {
        title: "Principal",
        url: "#",
        items: [
          {
            title: "Acesso",
            url: "/acesso",
            onClick: () => {
              resetActive();
              active.acesso = true;
            },
            isActive: active.acesso,
          },
          {
            title: "Vendas",
            url: "/vendas ",
            onClick: () => {
              resetActive();
              active.venda = true;
            },
            isActive: active.venda,
          },
          {
            title: "Produtos",
            url: "/produto",
            onClick: () => {
              resetActive();
              active.produto = true;
            },
            isActive: active.produto,
          },
          {
            title: "Clientes",
            url: "/cliente",
            onClick: () => {
              resetActive();
              active.cliente = true;
            },
            isActive: active.cliente,
          },
          {
            title: "Crédito",
            url: "/credito",
            onClick: () => {
              resetActive();
              active.credito = true;
            },
            isActive: active.credito,
          },
        ],
      },
      {
        title: "Controle",
        url: "#",
        items: [
          {
            title: "Funcionários",
            url: "/funcionario",
            onClick: () => {
              resetActive();
              active.funcionario = true;
            },
            isActive: active.funcionario,
          },
          {
            title: "Clientes em aberto",
            url: "/em_aberto",
            onClick: () => {
              resetActive();
              active.em_aberto = true;
            },
            isActive: active.em_aberto,
          },
          {
            title: "Consumo Diário",
            url: "/consumo_diario",
            onClick: () => {
              resetActive();
              active.consumo_diario = true;
            },
            isActive: active.consumo_diario,
          },
          {
            title: "Clientes Diário",
            url: "/clientes_diario",
            onClick: () => {
              resetActive();
              active.clientes_diario = true;
            },
            isActive: active.clientes_diario,
          },
          {
            title: "Aniversariantes",
            url: "/aniversariantes",
            onClick: () => {
              resetActive();
              active.aniversariantes = true;
            },
            isActive: active.aniversariantes,
          },
          {
            title: "Ticket Médio",
            url: "/ticket",
            onClick: () => {
              resetActive();
              active.ticket = true;
            },
            isActive: active.ticket,
          },
          {
            title: "Última Compra",
            url: "/ultima_compra",
            onClick: () => {
              resetActive();
              active.ultima_compra = true;
            },
            isActive: active.ultima_compra,
          },
          {
            title: "Relatório de produtos",
            url: "/relatorio_produtos",
            onClick: () => {
              resetActive();
              active.relatorio_produtos = true;
            },
            isActive: active.relatorio_produtos,
          },
          {
            title: "Fornecedores",
            url: "/fornecedor",
            onClick: () => {
              resetActive();
              active.fornecedor = true;
            },
            isActive: active.fornecedor,
          },
          {
            title: "Controle de Saída",
            url: "/controle_saida",
            onClick: () => {
              resetActive();
              active.controle_saida = true;
            },
            isActive: active.controle_saida,
          },
          {
            title: "DRE Diário",
            url: "/dre",
            onClick: () => {
              resetActive();
              active.dre = true;
            },
            isActive: active.dre,
          },
          {
            title: "Tela",
            url: "/tela",
            onClick: () => {
              resetActive();
              active.tela = true;
            },
            isActive: active.tela,
          },
        ],
      },
    ],
  };

  const handleLogout = () =>{
    signOut();
  }

  return (
    <Sidebar {...props}>
      <SidebarHeader>
        <SidebarMenuButton
          size="lg"
          className="data-[state=open]:bg-sidebar-accent data-[state=open]:text-sidebar-accent-foreground "
        >
          <div className="bg-sidebar-primary text-sidebar-primary-foreground flex aspect-square size-8 items-center justify-center rounded-lg">
            <GalleryVerticalEnd className="size-4" />
          </div>
          <div className="flex flex-col gap-0.5 leading-none">
            <span className="font-medium">Carest</span>
          </div>
        </SidebarMenuButton>
      </SidebarHeader>
      <SidebarContent>
        {/* We create a SidebarGroup for each parent. */}
        {data.navMain.map((item) => (
          <SidebarGroup key={item.title}>
            <SidebarGroupLabel>{item.title}</SidebarGroupLabel>
            <SidebarGroupContent>
              <SidebarMenu>
                {item.items.map((item) => (
                  <SidebarMenuItem
                    key={item.title}
                    className={
                      item.isActive
                        ? "bg-[var(--primary-key)] text-white"
                        : "hover:bg-gray-200"
                    }
                  >
                    <SidebarMenuButton
                      asChild
                      isActive={item.isActive}
                      onClick={item.onClick}
                    >
                      <a href={item.url}>{item.title}</a>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                ))}
              </SidebarMenu>
            </SidebarGroupContent>
          </SidebarGroup>
        ))}
      </SidebarContent>
      
      <SidebarContent className="p-4 border-t mt-auto max-h-32">
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="w-full flex items-center gap-8 cursor-pointer">
              <User className="size-5 mr-2" />
              <span>{session?.user?.name || 'Usuário'}</span>
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem onClick={handleLogout} className="text-red-500 flex items-center btn-hover-scale cursor-pointer">
              <LogOut className="size-4 mr-2" /> Sair
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarContent>

      <SidebarRail />
    </Sidebar>
  );
}

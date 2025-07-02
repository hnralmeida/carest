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
import Image from "next/image";
import group_login from "../../public/images/logo.png";
import { LogOut, User } from "lucide-react";
import { Button } from "./ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@radix-ui/react-dropdown-menu";
import { signOut } from "next-auth/react";
import { useSession } from "next-auth/react"
import { useRouter } from "next/navigation";

interface SidebarType {
  versions: string[];
  navMain: {
    title: string;
    url: string;
  }[];
}


const fullData = {
  versions: ["Carest"],
  navMain: [
  ],
};

export function AppSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {

  const [data, setData] = React.useState<SidebarType>(fullData as SidebarType);

  const { data: session } = useSession();

  const router = useRouter();

  React.useEffect(() => {

    if (session) {

      const filteredItems = session?.user.permissoes
        ?.filter((item: any) => item.read === true)
        .map((item: any) => ({
          title: item.tela.nome,
          url: item.tela.rota,
        }))
        .sort((a, b) => a.title.localeCompare(b.title)) || [];

      const updatedData = {
        ...fullData,
        navMain: filteredItems,
      };

      setData(updatedData);
    }
  }, [session]);

  const handleLogout = () => {
    signOut();
    router.push("/login");
  }

  const handlePerfil = () => {
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
            <Image
              src={group_login}
              alt="Create Next App"
              className="mx-auto hover:scale-105 hover:drop-shadow-[0_0_10px_rgba(62,62,255,0.1)] transition-transform duration-200 ease-in-out"
              width={256}
              height={256}
              placeholder="empty"
            />
          </div>
          <div className="flex flex-col gap-0.5 leading-none">
            <span className="font-medium">Carest</span>
          </div>
        </SidebarMenuButton>
      </SidebarHeader>
      <SidebarContent>
        {/* We create a SidebarGroup for each parent. */}
        {data && data.navMain.map((item) => (
          <SidebarMenu
            key={item.title}
          >
            <SidebarMenuItem
              className={
                "hover:bg-gray-200"
              }
            >
              <SidebarMenuButton
                asChild
              >
                <a href={item.url}>{item.title}</a>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        ))}
      </SidebarContent>

      <SidebarContent className="p-4 border-t mt-auto max-h-[96px]">
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="w-full flex items-center gap-8 cursor-pointer">
              <User className="size-5 mr-2" />
              <span>{session?.user.nome || 'Usuário'}</span>
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent side="top" align="end" className="flex flex-col bg-white shadow-md w-56 pt-4 pb-4 border rounded-lg">
            <DropdownMenuItem onClick={handlePerfil} className="text-[var(--black-color)] flex items-center btn-hover-scale cursor-pointer w-fit ml-4 mr-4">
              <User className="size-4 mr-2" /> Perfil
            </DropdownMenuItem>
            <div className="border-t my-2 mx-4" />
            <DropdownMenuItem onClick={handleLogout} className="text-[var(--error-color)] flex items-center btn-hover-scale cursor-pointer w-fit ml-4 mr-4 mt-2">
              <LogOut className="size-4 mr-2" /> Sair
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarContent>

      <SidebarRail />
    </Sidebar>
  );
}

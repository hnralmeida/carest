import Image from "next/image";
import group_login from "../../public/images/group_login.svg";
import Link from "next/link";
import { Arrow } from "@radix-ui/react-tooltip";
import { ArrowLeft } from "lucide-react";

export default function ErrorPage() {

  return (
    <div className="flex flex-col items-center justify-center h-screen"> {/* Pai: centro total */}
      <div className="mx-auto py-4 px-4 rounded-md border content-bg max-w-md text-center">
        <Link
          href="/"
          className="flex w-full flex-row items-center gap-2 mb-4 text-gray-500 hover:text-gray-700 transition-colors duration-200"
        >
          <ArrowLeft className="text-gray-500 hover:text-gray-700 transition-colors duration-200" />
          Voltar para a página inicial
        </Link>
        <h1 className="text-2xl font-bold mb-2">Página não encontrada</h1>
        <p className="text-lg px-16">Desculpe, a página que você está procurando não existe.</p>
      </div>
      <Image
        src={group_login}
        alt="Create Next App"
        className="mx-auto hover:scale-105 hover:drop-shadow-[0_0_10px_rgba(62,62,255,0.1)] transition-transform duration-200 ease-in-out"
        width={256}
        height={256}
        placeholder="empty"
      />
    </div>
  );

}
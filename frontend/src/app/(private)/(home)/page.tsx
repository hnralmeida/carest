'use client';

import LoggoutButton from "@/components/loggoutButton";
import { useUsuarioHook } from "@/hooks/useUsuario";
import { useSession } from "next-auth/react";
import React, { use } from "react";

export default function Home() {

  const { data: session, status } = useSession()

  const email = session?.user?.email || "";

  const { getUsuario } = useUsuarioHook();

  React.useEffect(() => {
    console.log("useEffect Home for ", session);
    getUsuario(email).then((usuario) => {
      console.log(usuario);
    }).catch((error) => {
      console.error(error);
    });
  }, [session]);

  if (status === "authenticated") {
    return <p>Signed in as {session.user.email}</p>
  }
  
  return (
    <main>
      <LoggoutButton />
    </main>
  );
}

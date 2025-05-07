'use client'

import { getServerSession } from "next-auth";

export default async function Home() {

    const session = await getServerSession();

    return (
      <main>
        <h1>Acesso {session?.user?.email || ''}</h1>
      </main>
    );
  }
  
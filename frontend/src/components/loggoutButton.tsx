"use client";

import { signOut } from "next-auth/react";

export default function LoggoutButton() {
  return (
    <button className="btn btn-outlined" onClick={() => signOut()}>
      Loggout
    </button>
  );
}

"use client";

import { signIn } from "next-auth/react";
import { useSearchParams } from "next/navigation";

export default function LoginForm() {
  const searchParams = useSearchParams();

  const error = searchParams.get("error");

  async function login(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const data = {
      email: formData.get("email") as string,
      password: formData.get("password") as string,
    };

    await signIn("credentials", {
      ...data,
      callbackUrl: "/",
    });
  }
  return (
    <form
      onSubmit={login}
      className="flex flex-col items-center justify-center mt-4 w-full max-w-ls px-16"
    >
      <div className="mb-4 w-full max-w-ls">
        <label className="block text-sm font-bold mb-2" htmlFor="email">
          Email
        </label>
        <input
          id="email"
          type="email"
          name="email"
          placeholder="Email"
          className="shadow appearance-none border rounded w-full py-2 px-3 text-[var(--neutral-color)] leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
        />
      </div>
      <div className="mb-4 w-full max-w-ls">
        <label className="block text-sm font-bold mb-2" htmlFor="password">
          Password
        </label>
        <input
          id="password"
          type="password"
          name="password"
          placeholder="******************"
          className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
        />
      </div>
      <div className="flex items-center justify-between">
        <button className="button-class" type="submit">
          Sign In
        </button>
      </div>
      {error=="CredentialsSignin" && <p className="text-red-400">Email ou senha inválida</p>}
    </form>
  );
}

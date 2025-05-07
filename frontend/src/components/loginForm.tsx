"use client";

import { signIn } from "next-auth/react";
import { useSearchParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { toast, Toaster } from "sonner";

interface IUser {
  email: string;
  senha: string;
}

export default function LoginForm() {
  const [data, setData] = useState<IUser>({
    email: "",
    senha: "",
  });

  const [isLoading, setIsLoading] = useState<boolean>(false);

  const searchParams = useSearchParams();
  const error = searchParams.get("error");
  const router = useRouter();

  async function login(e: React.SyntheticEvent) {
    setIsLoading(true);
    e.preventDefault();

    const res = await signIn("credentials", {
      ...data,
      redirect: false,
      // callbackUrl: "/",
    });

    if (res?.error) {
      toast.error(res.error)
      console.log(res.error);
    } else {
      console.log("Login successful", res);
      router.push("/");
    }
    setIsLoading(false);
  }

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setData((prev) => {
      return { ...prev, [e.target.name]: e.target.value };
    });
  };

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
          autoCapitalize="none"
          autoCorrect="off"
          onChange={handleChange}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-[var(--neutral-color)] leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
        />
      </div>
      <div className="mb-4 w-full max-w-ls">
        <label className="block text-sm font-bold mb-2" htmlFor="senha">
          Password
        </label>
        <input
          id="senha"
          type="password"
          name="senha"
          placeholder="******************"
          autoCapitalize="none"
          autoCorrect="off"
          onChange={handleChange}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
        />
      </div>
      <div className="flex items-center justify-between">
        <button className="button-class" type="submit" disabled={isLoading}>
          Entrar
        </button>
      </div>
      <Toaster richColors position="top-center"/>
      {error == "CredentialsSignin" && (
        <p className="text-red-400">Email ou senha inválida</p>
      )}
    </form>
  );
}

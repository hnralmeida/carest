import { axiosClient } from "@/services/axiosClient";
import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

const handler = NextAuth({
  pages: {
    signIn: "/login",
  },
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        email: { label: "email", type: "text", placeholder: "jsmith" },
        password: { label: "password", type: "password" },
      },
      async authorize(credentials) {
        if (!credentials||credentials?.email==="") return null;
        const response = await axiosClient.post("/auth/login", credentials);
        if (response.status === 401) {
          throw new Error("Credenciais inválidas");
        }
        if (response.status === 404) {
          throw new Error("Usuário não encontrado");
        }
        if (response.status === 500) {
          throw new Error("Erro interno no servidor");
        }
        if (response.status <= 200) {
          return response.data;
        }
        return null;},
    }),
  ],
});

export { handler as GET, handler as POST };

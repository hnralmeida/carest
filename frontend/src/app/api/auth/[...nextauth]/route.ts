// import { axiosClient } from "@/services/axiosClient";
import { axiosClient } from "@/services/axiosClient";
import { TIMEOUT } from "dns";
import NextAuth, { AuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

const nextAuthOption: AuthOptions = {
  pages: {
    signIn: "/login",
    signOut: "/",
  },
  providers: [
    CredentialsProvider({
      name: "credentials",
      credentials: {
        email: { label: "email", type: "text", placeholder: "email@email.com" },
        senha: { label: "senha", type: "password" },
      },
      async authorize(credentials) {
        if (!credentials?.email || !credentials?.senha) {
          throw new Error("Dados de Login necessários");
        }
        try {
          const response = await axiosClient.post("/auth/login", {
            email: credentials.email,
            senha: credentials.senha,
          });

          const user = response.data;

          console.log('login   ', user)
          function timeout(ms: number): Promise<void> {
            return new Promise(resolve => setTimeout(resolve, ms));
          }
          
          // Uso:
          await timeout(2000);

          if (!user) {
            throw new Error("Usuário não encontrado");
          }
          return user;
        } catch (error: any) {
          const msg = error?.response?.data || "Erro desconhecido";
          throw new Error(msg);
        }
      },
    }),
  ]
};

const handler = NextAuth(nextAuthOption);

export { handler as GET, handler as POST };

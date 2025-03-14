// import { axiosClient } from "@/services/axiosClient";
import { axiosClient } from "@/services/axiosClient";
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
        console.log("CREDENCIAIS", credentials);
        if (!credentials?.email || !credentials?.senha) {
          throw new Error("Dados de Login necessários");
        }
        console.log("CREDENCIAIS", credentials);
        try {
          const response = await axiosClient.post("/auth/login", {
            email: credentials.email,
            senha: credentials.senha,
          });
          const user = response.data;
          if (!user) {
            throw new Error("Usuário não encontrado");
          }
          console.log("USUÁRIO AAAAAAAAAAAAAAAA", user);
          return user;
        } catch (error: unknown) {
          console.log(error);
          throw new Error("Usuario ou senha Incorreto");
        }
      },
    }),
  ],
};

const handler = NextAuth(nextAuthOption);

export { handler as GET, handler as POST };

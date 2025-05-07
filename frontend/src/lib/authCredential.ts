// import { axiosClient } from "@/services/axiosClient";
import { axiosClient } from "@/services/axiosClient";
import { AuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

export const nextAuthOption: AuthOptions = {
    pages: {
      signIn: "/",
      signOut: "/login",
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
  
            const userData = response.data;
  
            if (!userData) {
              throw new Error("Usuário não encontrado");
            }
  
            return {
              id: userData.id,
              email: userData.email,
              nome: userData.nome,
              permissoes: userData.permissoes,
            };
          } catch (error: any) {
            const msg = error?.response?.data || "Erro desconhecido";
            throw new Error(msg);
          }
        },
      }),
    ],
    session: {
      strategy: "jwt",
    },
    callbacks: {
      jwt: async ({ token, user }) => {
        if (user) {
          const {
            id,
            email,
            nome,
            permissoes,
          } = user
          return {
            ...token,
            id,
            email,
            nome,
            permissoes,
          };
        }
        return token;
      },
      session: async ({ session, token }) => {
  
        session.user = {
          id: token.id as string,
          nome: token.nome as string,
          email: token.email as string,
          permissoes: token.permissoes as string[],
        };
        return session;
      },
    },
  };
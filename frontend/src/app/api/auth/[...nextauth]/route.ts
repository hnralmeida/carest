// import { axiosClient } from "@/services/axiosClient";
import { useUsuarioHook } from "@/hooks/useUsuario";
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
        if (!credentials?.email || !credentials?.senha) {
          throw new Error("Dados de Login necessários");
        }
        try {
          const response = await axiosClient.post("/auth/login", {
            email: credentials.email,
            senha: credentials.senha,
          });

          const user = response.data;
          
          if (!user) {
            throw new Error("Usuário não encontrado");
          }
          return {
            id: user.id,
            nome: user.nome,
            email: user.email,
            permissoes: user.permissoes
          };
        } catch (error: any) {
          const msg = error?.response?.data || "Erro desconhecido";
          throw new Error(msg);
        }
      },
    }),
  ],
  callbacks: {
    async session({ session, token, user }) {
      // Adiciona o usuário da JWT à sessão
      session.user = token.user as any;
      return session;
    },
    async jwt({ token, user }) {
      if (user) {
        token.user = user;
      }
      return token;
    },
  },
};

const handler = NextAuth(nextAuthOption);

export { handler as GET, handler as POST };

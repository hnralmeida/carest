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
        return { id: "1", name: "John Doe", email: credentials.email };
      },
    }),
  ],
});

export { handler as GET, handler as POST };

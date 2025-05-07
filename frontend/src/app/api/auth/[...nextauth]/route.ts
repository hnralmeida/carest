import { nextAuthOption } from "@/lib/authCredential";
import NextAuth from "next-auth"

const handler = NextAuth(nextAuthOption);

export { handler as GET, handler as POST}
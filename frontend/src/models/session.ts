// types/next-auth.d.ts
import NextAuth from "next-auth";

declare module "next-auth" {
    interface Session {
        user: {
            id: string;
            email: string;
            nome: string;
            permissoes: any[];
            // outros campos personalizados...
        };
    }

    interface User {
        id: string;
        email: string;
        nome: string;
        permissoes: any[];
        // outros campos se quiser
    }
}

declare module "next-auth/jwt" {
    interface JWT {
        user: {
            id: string;
            email: string;
            nome: string;
            permissoes: any[];
        };
    }
}

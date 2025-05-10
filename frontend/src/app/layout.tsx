import type { Metadata } from "next";
import "./globals.css";
import AuthProvider from "../providers/AuthProvider";
import { PageProvider } from "@/providers/PageProvider";


export const metadata: Metadata = {
  title: "Carest",
  description: "Sistema de Gerenciamento de Restaurantes",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="body-bg min-h-screen" cz-shortcut-listen="true">
        <AuthProvider>
          <PageProvider>
            <main>
              <div className="flex w-full m-0">
                <div className="w-full">{children}</div>
              </div>
            </main>
          </PageProvider>
        </AuthProvider>
      </body>
    </html>
  );
}

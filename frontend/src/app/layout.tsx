import type { Metadata } from "next";
import "./globals.css";

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
        <main>{children}</main>
      </body>
    </html>
  );
}

import { AppSidebar } from "@/components/app-sidebar";
import {
  SidebarInset,
  SidebarProvider,
  SidebarTrigger,
} from "@/components/ui/sidebar";
import AuthProvider from "@/providers/AuthProvider";

export default function PrivateLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <AuthProvider>
        <SidebarProvider>
          <AppSidebar />
          <SidebarInset className="p-4">
            <header className="flex shrink-0 items-center gap-2 px-4">
              <SidebarTrigger />
            </header>
            {children}
          </SidebarInset>
        </SidebarProvider>
    </AuthProvider>
  )
}
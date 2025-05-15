"use client";

import { useSession } from 'next-auth/react';
import { useRouter } from "next/navigation";
import React, { createContext, useContext, useState, ReactNode, use } from 'react';

interface PageContextProps {
    currentPage: string;
    setCurrentPage: (page: string) => void;
}

const PageContext = createContext<PageContextProps | undefined>(undefined);

export const PageProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [currentPage, setCurrentPage] = useState<string>('home');
    const router = useRouter();
    const session = useSession();
    const user = session.data?.user;

    React.useEffect(() => {
        if (session.status === 'loading') return; // Aguarda a sessão carregar

        setCurrentPage(window.location.pathname);

        const token = localStorage.getItem('next-auth.session-token') ||localStorage.getItem('next-auth.session-token.0');

        if (session.status === 'authenticated'||token) {
            if (currentPage === '/login' || currentPage === '/register') {
                router.push('/');
            }
        }else{
            if (currentPage != '/login' && currentPage != '/register') {
                router.push('/login');
            }
        }

        if (session.status === 'unauthenticated') {
            if (currentPage !== '/login' && currentPage !== '/register') {
                router.push('/login');
            }
        }
    }, [session.status]);

    return (
        <PageContext.Provider value={{ currentPage, setCurrentPage }}>
            {children}
        </PageContext.Provider>
    );
};

export const usePage = (): PageContextProps => {
    const context = useContext(PageContext);
    if (!context) {
        throw new Error('usePage must be used within a PageProvider');
    }
    return context;
};
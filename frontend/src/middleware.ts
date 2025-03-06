import { MiddlewareConfig, NextRequest, NextResponse } from "next/server";

interface RouteType { path: string; whenAuthenticated: string };

const publicRoutes: RouteType [] = [
  { path: "/login", whenAuthenticated: "redirect" },
] as const;

const REDIRECT_WHEN_NOT_AUTHENTICATED = "/login";

export function middleware(request: NextRequest) {
  const path = request.nextUrl.pathname;
  const publicRouteURL = publicRoutes.find((route) => route.path === path);
  const authToken  = request.cookies.get('token');

  if (!authToken && publicRouteURL) {
    return NextResponse.next();
  }
  
  if (!authToken && !publicRouteURL) {
    const redirectURL = request.nextUrl.clone();
    redirectURL.pathname = REDIRECT_WHEN_NOT_AUTHENTICATED;
    return NextResponse.redirect(redirectURL);
  }

  if (authToken && publicRouteURL && publicRouteURL.whenAuthenticated === 'redirect') {
    const redirectURL = request.nextUrl.clone();
    redirectURL.pathname = "/";
    return NextResponse.redirect(redirectURL);
  }

  if (authToken && !publicRouteURL) {
    // Checkar se token expirou
    return NextResponse.next();
  }

  return NextResponse.next();
}

export const config: MiddlewareConfig = {
  matcher: [
    /*
     * Match all request paths except for the ones starting with:
     * - api (API routes)
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico, sitemap.xml, robots.txt (metadata files)
     */
    "/((?!api|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)",
  ],
};

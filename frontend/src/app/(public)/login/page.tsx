import Image from "next/image";
import logo from "../../../../public/images/logo.png";
import group_login from "../../../../public/images/group_login.svg";
import LoginForm from "@/components/loginForm";

export default function Login() {
  return (
    <div className="login-bg min-h-screen flex items-center justify-center">
      <div className="white-box flex flex-row w-full max-w-ls box-border p-8 rounded-lg shadow-2xl mx-64">
        <div className="flex flex-col items-center justify-center w-full hover:scale-105 hover:drop-shadow-[0_0_10px_rgba(62,62,255,0.1)] transition-transform duration-200 ease-in-out mb-8">
          <h1 className="text-2xl font-bold text-center mb-4 hover:scale-105 hover:drop-shadow-[0_0_10px_rgba(62,62,255,0.1)] transition-transform duration-200 ease-in-out">
            Bem-vindo de volta, colaborador!
          </h1>
          <Image
            src={group_login}
            alt="Create Next App"
            className="mx-auto hover:scale-105 hover:drop-shadow-[0_0_10px_rgba(62,62,255,0.1)] transition-transform duration-200 ease-in-out"
            width={256}
            height={256}
            placeholder="empty"
          />
        </div>
        <div className="flex flex-col items-center justify-center max-w-ls w-full">
          <Image
            src={logo}
            alt="Create Next App"
            className="mx-auto hover:scale-105 transition-transform duration-200 ease-in-out"
            width={128}
            height={128}
            placeholder="empty"
          />
          <LoginForm />
          {/* <a
            className="inline-block align-baseline font-bold text-sm hover:text-[var(--primary-color)] hover:scale-105 transition-transform duration-200 ease-in-out mt-4"
            href="#"
          >
            Forgot Password?
          </a>
          <p className="text-center text-xs mt-4">
            &copy;2025 Carest. All rights reserved.
          </p> */}
        </div>
      </div>
    </div>
  );
}

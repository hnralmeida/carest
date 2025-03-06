import Image from "next/image";
import logo from "../../../../public/images/logo.png";
import group_login from "../../../../public/images/group_login.svg";

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
          <form className="flex flex-col items-center justify-center mt-4 w-full max-w-ls px-16">
            <div className="mb-4 w-full max-w-ls">
              <label className="block text-sm font-bold mb-2" htmlFor="email">
                Email
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-[var(--neutral-color)] leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
                id="email"
                type="email"
                placeholder="Email"
              />
            </div>
            <div className="mb-4 w-full max-w-ls">
              <label
                className="block text-sm font-bold mb-2"
                htmlFor="password"
              >
                Password
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline hover:scale-105 transition-transform duration-200 ease-in-out"
                id="password"
                type="password"
                placeholder="******************"
              />
            </div>
            <div className="flex items-center justify-between">
              <button className="button-class" type="button">
                Sign In
              </button>
            </div>
          </form>
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

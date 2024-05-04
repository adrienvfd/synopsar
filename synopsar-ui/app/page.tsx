'use client';

import Image from "next/image";
import Link from "next/link";


export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-content-start p-12">

      <div className="relative h-48 w-48 flex items-center justify-center mt-16">
        <Image
          src="/synopsar-logo.png"
          alt="Synopsar Logo"
          className="rounded-full"
          width={256}
          height={256}
          priority
        />
      </div>

      <p className="mt-16 text-center text-2xl font-semibold">
        Synopsar: Summarize any YouTube video
      </p>

      <div className="mt-16 h-auto flex w-full items-center justify-center">
          <Link href="http://localhost:8081/oauth2/authorization/google">
            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-4 px-8 rounded">Login with Google to get started</button>
          </Link>
      </div>
      
    </main>
  );
}
"use client";
import Link from "next/link";

export default function NavBar() {
  return (
    <nav
      style={{
        backgroundColor: "#e1f5fe",
        padding: "1em",
        display: "flex",
        justifyContent: "space-between",
      }}
    >
      <div style={{ fontSize: "1.25em", margin: 0 }}>
        <Link href="/">
          <img
            src="/synopsar-logo.png"
            alt="Synopsar Logo"
            className="rounded-full"
            width={64}
            height={64}
          />
        </Link>
      </div>

      <div style={{ fontSize: "0.8em", margin: "0.5em auto", textDecoration: "none" }}>
        <Link href="/profile" style={{ display: "block", textAlign: "center" }}>
                Generate Synopses
        </Link>
      </div>

      <div style={{ fontSize: "0.8em", margin: "0.5em auto", paddingRight: "1em" }}>
        View Synopses
      </div>

    </nav>
  );
}

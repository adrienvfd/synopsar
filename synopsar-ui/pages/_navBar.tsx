"use client";
import Link from "next/link";
import Image from "next/image";

export default function NavBar() {

    return (
        <nav
            style={{
                backgroundColor: "#e1f5fe",
                padding: "1em",
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                flexDirection: "row",
                columnGap: "1em",
                height: "100%",
            }}
        >
            <div style={{ fontSize: "0.8em", margin: 0, textDecoration: "none", display: "flex" }}>
                <Link href="/">
                    <Image
                        src="/synopsar-logo.png"
                        alt="Synopsar Logo"
                        className="rounded-full"
                        width={64}
                        height={64}
                    />
                </Link>
            </div>

            <div style={{ fontSize: "0.8em", margin: 0, textDecoration: "none" }}>
                <Link
                    href="/generate"
                    style={{
                        display: "flex",
                        alignItems: "center",
                        fontFamily: "Arial, sans-serif",
                        color: "darkblue",
                        textDecoration: "none",
                    }}
                >
                    <div>
                        <Image src="/magic-wand.png" alt="Generate Synopses" width={32} height={32} style={{ marginRight: "0.5em" }}/>
                        <div style={{ marginLeft: "0.5em" }}>Generate Synopses</div>
                    </div>
                </Link>
            </div>

            <div style={{ fontSize: "0.8em", margin: 0, paddingRight: "1em" }}>
                <Link
                    href="/history"
                    style={{
                        display: "flex",
                        alignItems: "center",
                        fontFamily: "Arial, sans-serif",
                        color: "darkblue",
                        textDecoration: "none",
                    }}
                >
                    <div>
                        <Image src="/history.png" alt="View Synopses" width={32} height={32} />
                        <div style={{ marginLeft: "0.5em" }}>View Synopses</div>
                    </div>
                </Link>
            </div>
        </nav>
    );
}
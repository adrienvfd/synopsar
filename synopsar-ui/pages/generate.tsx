"use client";
import React, {useState} from "react";
import ReactMarkdown from "react-markdown";
import "./generate.css";
import remarkGfm from "remark-gfm";
import NavBar from "./_navBar";
import Button from "@mui/material/Button";
import {SummaryDtoLite} from "@/dto/summaryDtoLite";

function Generate() {
    const [loading, setLoading] = useState(false);
    const [summary, setSummary] = useState("");
    const [youtubeUrl, setYoutubeUrl] = useState("");
    const [loadingMessage, setLoadingMessage] = useState("");
    const postSummary = async (requestBody: SummaryDtoLite) => {
        const resp = await fetch(`http://localhost:8081/summary`, {
            method: "POST",
            credentials: "include",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody),
        });
    };

    const generateSummary = async (event) => {
        event.preventDefault();
        setLoading(true);
        setLoadingMessage("Fetching YouTube Summary...");
        try {
            const response = await fetch(
                `http://localhost:8081/summary?url=${youtubeUrl}`,
                {
                    method: "GET",
                    credentials: "include",
                    mode: "cors",
                }
            );
            const responseText = await response.text();
            if (responseText != null) {
                console.log(responseText);
                setSummary(responseText);

                const requestBody: SummaryDtoLite = {
                    id: null,
                    url: youtubeUrl,
                    text: responseText,
                };
                console.log(requestBody);

                try {
                    await postSummary(requestBody);
                    setLoading(false);
                    setLoadingMessage("Summary Saved!");
                    return;
                } catch (error) {
                    setLoading(false);
                    setLoadingMessage("Failed to save Summary");
                    return;
                }
            }
        } catch (error) {
            setLoading(false);
            setLoadingMessage("Failed to fetch YouTube Summary");
            return;
        }
    };

    return (
        <div className="container h-screen">
            <NavBar/>
            <main className="flex min-h-screen flex-col justify-content-start p-12-page">
                <div className="items-center">
                    <h1>Generate Summary</h1>

                    <div className="mt-16 h-auto flex w-full items-center justify-center">
                        <form onSubmit={generateSummary} className="w-full">
                            <label htmlFor="youtube-url" className="block mb-2">
                                Enter a YouTube video URL:
                            </label>

                            <input
                                type="text"
                                id="youtube-url"
                                name="youtube-url"
                                value={youtubeUrl}
                                onChange={(event) => setYoutubeUrl(event.target.value)}
                                className="w-full px-4 py-2 border border-gray-400 rounded"
                            />

                            {loading ? (
                                <div className="text-center mt-4" role="alert">
                                    <div className="lds-ring"></div>
                                    <p className="text-sm mt-2">{loadingMessage}</p>
                                </div>
                            ) : (
                                <Button variant="contained" type="submit">
                                    Generate Summary
                                </Button>
                            )}
                            {loadingMessage === "Summary Saved!" && (
                                <p className="text-sm mt-2">{loadingMessage}</p>
                            )}
                        </form>
                    </div>
                </div>
                <div className="items-left px-12 pt-12 text-left">
                    <ReactMarkdown
                        children={summary}
                        remarkPlugins={[remarkGfm]}
                        textAlign="left"
                    />
                </div>
            </main>
        </div>
    );
}

export default Generate;

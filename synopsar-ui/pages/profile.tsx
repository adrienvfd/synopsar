"use client";
import { useState } from "react";
import './profile.css';

function Profile() {
  const [loading, setLoading] = useState<boolean>(false);
  const [summary, setSummary] = useState<string>("");
  const [youtubeUrl, setYoutubeUrl] = useState<string>("");

  const generateSummary = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setLoading(true);

    const response = await fetch(
      `http://localhost:8081/summary/?url=${youtubeUrl}`,
      {
        method: "GET",
        credentials: "include",
        mode: "cors",
      }
    );
    const responseText = await response.text();
    setSummary(responseText);
    setLoading(false);
  };

  return (
    <div className="container">
      <main className="flex min-h-screen flex-col items-center justify-content-start p-12-page">
        <h1>Profile</h1>

        <h1>Generate Summary</h1>

        <div className="mt-16 h-auto flex w-full items-center justify-center">
          <form onSubmit={generateSummary} className="w-full ">
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
                <div className="lds-ring">Generating summary</div>
                <p className="text-sm mt-2">Generating summary...</p>
              </div>
            ) : (
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-4 px-8 rounded"
                type="submit"
              >
                Generate Summary
              </button>
            )}
          </form>
        </div>

        <h1>View Summary</h1>
        <a>{summary}</a>
      </main>
    </div>
  );
}

export default Profile;

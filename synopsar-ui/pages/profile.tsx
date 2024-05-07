'use client';
import React from 'react';
import { useState } from 'react';
import ReactMarkdown from 'react-markdown';
import './profile.css';
import remarkGfm from 'remark-gfm'
import NavBar from './_navBar';

function Profile() {
  const [loading, setLoading] = useState(false);
  const [summary, setSummary] = useState('');
  const [youtubeUrl, setYoutubeUrl] = useState('');

  const generateSummary = async (event) => {
    event.preventDefault();
    setLoading(true);

    const response = await fetch(`http://localhost:8081/summary/?url=${youtubeUrl}`, {
      method: 'GET',
      credentials: 'include',
      mode: 'cors',
    });
    const responseText = await response.text();
    setSummary(responseText);
    setLoading(false);
  };

  return (
    <div className="container">
      <NavBar />
      <main className="flex min-h-screen flex-col items-center justify-content-start p-12-page">
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
                <p className="text-sm mt-2">Generating summary...</p>
              </div> 
              ) : (
              <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-4 px-8 rounded" type="submit">
                Generate Summary
              </button>
            )}
          </form>
        </div>
        <ReactMarkdown children={summary} remarkPlugins={[remarkGfm]} />
      </main>
    </div>
  );
}

export default Profile;
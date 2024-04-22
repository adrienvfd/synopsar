
# Synopsar: summarize any youtube video

Synopsar is a web application that allows you to 
create a synopsis of any youtube video in a few seconds.
The app uses the [youtube-transcript-api](https://github.com/jdepoix/youtube-transcript-api) library to get the transcript of the video, to which we added an access through rest api.

The user just needs to provide the url of the video and the app will summarize it for you.

The summarization is done through Groq API queries and is powered by Mistral AI model.

The app contains the following components:
- Transcriptor: Python app that allows you to get the transcript of a youtube video
- Summarizer: Python API using Groq API
- Synopsar-UI: NextJs application
- Synopsar-API: Java rest api with Postgresql database

## Installation

## Install and Run Synopsar-API
Prerequisites: Install Java JDK 22.
Following commands to build and run:
mvn clean install
mvn spring-boot:run

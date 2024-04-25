FROM python:3.12.2

WORKDIR /app

COPY requirements.txt .

RUN pip install -r requirements.txt
RUN pip install flask
RUN pip install flask_restful
RUN pip install youtube_transcript_api

COPY . .

EXPOSE 5000

CMD ["python", "_restAPI.py", "--host=0.0.0.0"]
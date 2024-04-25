FROM python:3.12.2

WORKDIR /app

COPY requirements.txt .

RUN pip install -r requirements.txt
RUN pip install addict
RUN pip install flask
RUN pip install load_dotenv
RUN pip install groq
RUN pip install regex

COPY . .

ENV GROQ_API_KEY ${GROQ_API_KEY}

EXPOSE 5001

CMD ["python", "app.py"]
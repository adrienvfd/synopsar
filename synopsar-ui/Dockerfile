FROM node:18.19.0

WORKDIR /app

COPY package*.json ./

RUN npm install -g pnpm
RUN pnpm install
RUN pnpm install react-oauth2
RUN pnpm install react-markdown

COPY . .

RUN pnpm build

EXPOSE 3000

CMD ["pnpm", "start"]
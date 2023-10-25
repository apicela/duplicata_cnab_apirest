FROM node:20.8.1

WORKDIR /app

COPY . /app

RUN npm install

CMD ["npm", "run", "dev", "--", "--port", "3000","--host", "0.0.0.0"]

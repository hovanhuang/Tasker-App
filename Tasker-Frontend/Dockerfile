FROM node:12-alpine
WORKDIR /tasker-app-frontend
COPY package.json .

RUN npm install
COPY . .
CMD ["npm", "start"]
# Etapa de build
FROM node:14 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Etapa de produção
FROM nginx:alpine
COPY --from=build /app/dist/desafio /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
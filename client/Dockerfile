FROM node:22 AS builder

WORKDIR /app

COPY app/package*.json ./

RUN npm ci
COPY app .
RUN npm run build

RUN apt update -y && apt install openssl -y && \
    mkdir -p /etc/nginx/ssl && \
    openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout /etc/nginx/ssl/rag.key \
    -out /etc/nginx/ssl/rag.crt \
    -subj "/C=MO/ST=FZ/O=FSDM/OU=USMBA/CN=$RAG_HOST"

FROM nginx:alpine

COPY --chmod=644 config/nginx.conf /etc/nginx/nginx.conf

COPY --from=builder /etc/nginx/ssl/rag.key /etc/nginx/ssl/rag.key
COPY --from=builder /etc/nginx/ssl/rag.crt /etc/nginx/ssl/rag.crt
COPY --from=builder /app/dist/client/browser /var/www/html

ENTRYPOINT ["nginx", "-g", "daemon off;"]

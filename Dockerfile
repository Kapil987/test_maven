FROM alpine:latest

RUN apk add --no-cache git openssh-client

CMD ["/bin/sh"]

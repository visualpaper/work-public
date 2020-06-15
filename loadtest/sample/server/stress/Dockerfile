FROM alpine

RUN apk update \
    && apk add openssl

ENTRYPOINT ["/bin/sh", "-c", "while :; do openssl speed -multi 26; done"]



FROM python:3.7-alpine3.9

ARG OPERATION_ID

RUN    apk update \
    && apk --no-cache add --virtual=.build-dep build-base libffi-dev git \
    && apk --no-cache add zeromq-dev \
    && python3 -m pip install locustio  \
    && apk del .build-dep \
    && mkdir /locust

ADD ./scenario/${OPERATION_ID}/scenario.py /locust/scenario.py
ADD ./datas /locust/datas

EXPOSE 8089 5557 5558
ENTRYPOINT ["/usr/local/bin/locust", "-f", "/locust/scenario.py"]

FROM python:3.6-alpine

RUN    apk update \
    && apk --no-cache add --virtual=.build-dep build-base libffi-dev git \
    && apk --no-cache add zeromq-dev \
    && python3 -m pip install locustio==0.14.2  \
    && apk del .build-dep \
    && mkdir /locust

ADD ./locust.py /locust/locust.py
ADD ./apis /locust/apis
ADD ./actions /locust/actions
ADD ./datas /locust/datas

EXPOSE 8089 5557 5558
ENTRYPOINT ["/usr/local/bin/locust", "-f", "/locust/locust.py"]

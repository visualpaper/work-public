FROM python:3.7-alpine3.9

RUN    apk update \
    && apk --no-cache add --virtual=.build-dep build-base libffi-dev git \
    && apk --no-cache add zeromq-dev \
    && python3 -m pip install locustio  \
    && apk del .build-dep \
    && mkdir /locust

ADD ./senario/senario.py /locust/senario.py
ADD ./uploadFiles /locust/uploadFiles

EXPOSE 8089 5557 5558
ENTRYPOINT ["/usr/local/bin/locust", "-f", "/locust/senario.py"]

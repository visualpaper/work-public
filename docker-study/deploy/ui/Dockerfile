FROM httpd:latest

RUN mkdir -p /usr/local/apache2/conf.d

COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf
COPY ./application.conf /usr/local/apache2/conf.d/

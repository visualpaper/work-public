FROM httpd:latest

RUN mkdir -p /usr/local/apache2/conf.d
RUN mkdir -p /study/ui/

COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf
ADD ./build /study/ui/
COPY ./application.conf /usr/local/apache2/conf.d/

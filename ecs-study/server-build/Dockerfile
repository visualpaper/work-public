
FROM ubuntu:latest

# base update
RUN apt-get update && apt-get upgrade -y

# add-apt-repository コマンドの追加
RUN apt-get install -y apt-file
RUN apt-file update
RUN apt-file search add-apt-repository
RUN apt-get install -y software-properties-common

# gradle & java 追加
RUN add-apt-repository ppa:cwchien/gradle
RUN add-apt-repository ppa:webupd8team/java

# インストール
RUN apt-get update && apt-get upgrade -y gradle git maven ant

# Javaライセンス承諾とインストール
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections
RUN apt-get install -y oracle-java8-installer

FROM tomcat:8.0.20-jre8

# ADD tomcat/catalina.sh $CATALINA_HOME/bin/
WORKDIR /usr/local/tomcat/bin
COPY run.sh run.sh
RUN chmod +x run.sh

#Copy war file
ADD ./server.war /usr/local/tomcat/webapps/

# Expose ports
EXPOSE 7199 8126

WORKDIR /usr/local/tomcat/bin
COPY dd-java-agent.jar .
CMD ["run.sh"]


#!/bin/sh

export JAVA_OPTS="$JAVA_OPTS -javaagent:/usr/local/tomcat/bin/dd-java-agent.jar -Ddd.agent.host=dd-agent -Ddd.agent.port=8126 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=7199 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
exec ${CATALINA_HOME}/bin/catalina.sh run


FROM openjdk:8 AS builder

ARG APP_HOME=/tmp/app

COPY src $APP_HOME/src

COPY --from=intersystemsdc/jgw:latest /jgw/*.jar $APP_HOME/jgw/

COPY lib $APP_HOME/jgw

WORKDIR $APP_HOME/jar/

WORKDIR $APP_HOME/src

RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* dc/irishiveadapter/HiveOperation.java && \
    jar -cvf $APP_HOME/jar/HiveOperation.jar dc/irishiveadapter/HiveOperation.class

FROM intersystemsdc/jgw:latest

COPY --from=builder /tmp/app/jar/*.jar $GWDIR/
COPY lib/*.jar $GWDIR/ 

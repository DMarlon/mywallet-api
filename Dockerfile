FROM gradle:7.4.2-jdk11-alpine AS build
RUN apk add git

ENV DB_SERVER=localhost
ENV DB_PORT=8801
ENV DB_NAME=test
ENV DB_USER=dev
ENV DB_PASSWORD=dev
ENV FRONT_SERVER=http://localhost:8081

WORKDIR /api

RUN git clone https://github.com/DMarlon/mywallet-api .

RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

ARG db_server
ARG db_port
ARG db_name
ARG db_user
ARG db_password
ARG front_server

ENV DB_SERVER=${db_server}
ENV DB_PORT=${db_port}
ENV DB_NAME=${db_name}
ENV DB_USER=${db_user}
ENV DB_PASSWORD=${db_password}
ENV FRONT_SERVER=${front_server}

EXPOSE 8080
WORKDIR /api

COPY --from=build /api/build/libs/mywallet-0.0.1-SNAPSHOT.jar ./mywallet-0.0.1-SNAPSHOT.jar

CMD [\
  "java",\
  "-jar",\
  "mywallet-0.0.1-SNAPSHOT.jar"\
]

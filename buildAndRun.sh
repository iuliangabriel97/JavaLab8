#!/bin/sh
mvn clean package && docker build -t com.mycompany/JavaLab8 .
docker rm -f JavaLab8 || true && docker run -d -p 9080:9080 -p 9443:9443 --name JavaLab8 com.mycompany/JavaLab8
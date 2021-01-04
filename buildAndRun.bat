@echo off
call mvn clean package
call docker build -t com.mycompany/JavaLab8 .
call docker rm -f JavaLab8
call docker run -d -p 9080:9080 -p 9443:9443 --name JavaLab8 com.mycompany/JavaLab8
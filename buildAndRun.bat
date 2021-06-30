@echo off
call mvn clean package
call docker build -t com.j3cs.test/yes .
call docker rm -f yes
call docker run -d -p 9080:9080 -p 9443:9443 --name yes com.j3cs.test/yes
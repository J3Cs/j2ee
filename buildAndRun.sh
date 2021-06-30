#!/bin/sh
mvn clean package && docker build -t com.j3cs.test/yes .
docker rm -f yes || true && docker run -d -p 9080:9080 -p 9443:9443 --name yes com.j3cs.test/yes
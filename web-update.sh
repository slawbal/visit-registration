#!/bin/bash

mvn -v
mvn clean install -f registration-web/
docker-compose build web
docker-compose up --no-deps -d web

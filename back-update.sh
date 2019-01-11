#!/bin/bash

docker-compose build backend
docker-compose up --no-deps -d backend

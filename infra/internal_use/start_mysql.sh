#!/bin/sh
docker run --name mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=coinBase$ -d mysql
docker logs mysql

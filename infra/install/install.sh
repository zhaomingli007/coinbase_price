#!/bin/sh
####Pull docker images
docker pull zhaomingli/mysql8:coinbase
docker pull zhaomingli/pyscala:coinbase


#### Run docker containers
#Run Mysql container
docker run --name mysql -e MYSQL_ROOT_PASSWORD=coinBase$ -d zhaomingli/mysql8:coinbase
#Run data pipeline container
docker run --name pipeline --link mysql:coinbase-mysql -t -d pyscala:coinbase
docker exec -d pipeline bash tools/run_pipeline.sh
#Run scala+play container
docker run --name play --link mysql:coinbase-mysql -t pyscala:coinbase
docker exec -d play bash  tools/run_play.sh 8080
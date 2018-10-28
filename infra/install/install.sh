#!/bin/sh
####Pull docker images
docker pull zhaomingli/mysql8:coinbase
docker pull zhaomingli/pyscala:coinbase


#### Run docker containers
#Run Mysql container
docker run --name mysql -v /Users/zhao/codebase/coinbase_price/infra/install/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD='coinBase$' -d zhaomingli/mysql8:coinbase
#Create a database
docker exec -d mysql mysql -u root -p'coinBase$' -h localhost -e 'create database coinbase'

#Run data pipeline container
docker run --name pipeline --link mysql:coinbase-mysql -t -d zhaomingli/pyscala:coinbase
docker exec -it pipeline bash tools/run_pipeline.sh
#Run scala+play container
docker run --name play -p 80:80 --link mysql:coinbase-mysql -t -d zhaomingli/pyscala:coinbase
docker exec -it play bash  tools/run_play.sh 80
#!/bin/sh

#Pull docker image
docker pull mysql
docker run --name mysql -p 3307:3306 -v /Users/zhao/codebase/coinbase_price/infra/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD='coinBase$' -d mysql



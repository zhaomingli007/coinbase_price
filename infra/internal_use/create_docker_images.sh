#!/bin/sh

#Create Mysql image from container
docker commit ec3c94b20a70  zhaomingli/mysql8:coinbase

#Create Python, Scala, Git, Spark and Java image

#Publish images
docker push zhaomingli/mysql8
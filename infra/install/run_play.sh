#!/bin/sh
playport=$1
cd code
git clone https://github.com/zhaomingli007/coinbase_price.git

#Start play
mkdir play_runtime
cd coinbase_price
sbt dist
cp  backend/playproject/coinbase/target/universal/coinbase-1.0.zip ../play_runtime/
cd ../play_runtime
unzip coinbase-1.0.zip -d coinbase
chmod +x coinbase/coinbase-1.0/bin/coinbase
coinbase/coinbase-1.0/bin/coinbase -Dhttp.port=$playport -Dplay.http.secret.key='927c05f0-da7c-11e8-9f8b-f2801f1b9fd1' -Dconfig.file=/workspace/code/coinbase_price/backend/playproject/coinbase/conf/application-prod.conf

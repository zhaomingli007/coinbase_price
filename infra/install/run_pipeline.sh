#!/bin/sh
cd code
git clone https://github.com/zhaomingli007/coinbase_price.git

#Extract and Talend data pipeline scripts
mkdir talend_runtime
unzip coinbase_price/data_ingestion/batch/Talend_run/main_pipeline_0.1.zip -d ./talend_runtime
chmod -R +rx ./talend_runtime
talend_runtime/main_pipeline/main_pipeline_run.sh -> main_pipeline_run.log

#schedule
0 23 * * * bash talend_runtime/main_pipeline/main_pipeline_run.sh -> main_pipeline_run.log
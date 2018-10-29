- [Project structures](#project-structures)
    - [Code structure](#code-structure)
    - [Datalake structure](#datalake-structure)
    - [Workspace structure](#workspace-structure)
    - [Database catalog and schema](#database-catalog-and-schema)
- [Architecture and techiques](#architecture-and-techiques)
    - [Business requirement and system design principles](#business-requirement-and-system-design-principles)
        - [Business requirement](#business-requirement)
        - [Design principles](#design-principles)
    - [Architecture overview](#architecture-overview)
    - [Data storage](#data-storage)
    - [Data pipeline](#data-pipeline)
    - [Web API](#web-api)
    - [Bitcorn price forecasting model](#bitcorn-price-forecasting-model)
- [Web API](#web-api)
- [Data modeling and forecast](#data-modeling-and-forecast)
- [Automated data pipeline](#automated-data-pipeline)
    - [Data pipeline design](#data-pipeline-design)
        - [Data ingestion](#data-ingestion)
        - [Data prepare](#data-prepare)
    - [Data pipeline deploy and run](#data-pipeline-deploy-and-run)
- [Installation requirement and steps](#installation-requirement-and-steps)
- [Future work](#future-work)


### Project structures
#### Code structure
#### Datalake structure
![](documents/datalake_struc.png)
#### Database catalog and schema

### Architecture and techiques
Languages, frameworks and libraries used in this project:
- Scala + Play, for web API
- Python, for data modeling and forecasting
- Talend and Spark for data pipeline
- Mysql for API's backend storage
- HDFS/S3 (local FS) as data lake

#### Business requirement and system design principles
##### Business requirement
- Allowing users to see the bitcoin price movement for last week, last month or any custom date.
- Allowing users to see the x days rolling / moving average bitcoin prices between two custom dates.
- Allowing users to get the forecasted bitcoin price for next 15 days.
##### Design principles
- Datalake: raw, single version of truth
- Data prepare/analytics: SQL friendly
- Batch/ daily refresh
- Automate data pipeline
- API access - Low latency
- API application, low project dependency to data systems.

  
#### Architecture overview

#### Data storage
#### Data pipeline

#### Web API
Demo IP: 54.202.166.199
```sh
    #Get last week/month prices movement
    GET /prices/lastweek
    curl -X GET http://54.202.166.199/prices/lastweek
    GET /prices/lastmonth
    curl -X GET http://54.202.166.199/prices/lastmonth
    #Get prices between two customized dates
    POST /prices/:start/:end
    example: curl -X POST http://54.202.166.199/prices/3/2018-03-01/2018-04-01
    #Get X days moving average prices between two customized dates
    POST /prices/:xday/:start/:end  
    example: curl -X POST http://54.202.166.199/prices/3/2017-12-01/2017-12-26
    #Get 15 forecast prices
    GET /prices/forecast
    curl -X GET http://54.202.166.199/prices/forecast
```
#### Bitcorn price forecasting model
### Web API

### Data modeling and forecast

### Automated data pipeline
#### Data pipeline design
##### Data ingestion

##### Data prepare


[![Data pipeline video](documents/datapipeline.png)](https://www.screencast.com/t/1r5O5XnH)

#### Data pipeline deploy and run

### Installation requirement and steps
- Requirment: Docker
- Install:
    Download this script [install.sh](infra/install/install.sh) and follow and adapt the commands in `install.sh`

### Future work
- Web API
    -  kubernete cluster deployment
    -  Security control (API permissions)
    -  Performance benchmark
    - Setting up a front end HTTP server
    - Configuring HTTPS
- Data lake
    - HDFS/S3 in production
- Data pipeline
    - Notification
    - Scheduler dashboard
- CI/CD
    - Jenkins/AWS CodeBuild+Docker
- Forecasting model
    -  improve precision
  
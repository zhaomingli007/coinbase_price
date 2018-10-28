FROM python:3

#Create workspace
RUN mkdir workspace && \
 mkdir workspace/tools && \
 mkdir workspace/code && \
 mkdir workspace/data

WORKDIR workspace/

#Install python packages
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

#Install java, sbt and git
# Install Java.
# Env variables
ENV SCALA_VERSION 2.12.7
ENV SBT_VERSION 1.2.6
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  apt-get update && \  
  DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends apt-utils && \
  apt-get install -y software-properties-common && \
  apt-get install apt-utils && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install --allow-unauthenticated -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer && \
  apt-get install -y git && \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get install sbt && \
  apt-get update && \  
  apt-get install -y unzip && \
  sbt sbtVersion  

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle  


#Copy spark library to image
COPY docker_build/spark-2.2.1-bin-hadoop2.7 /workspace/tools/spark-2.2.1-bin-hadoop2.7

#Copy historical data
COPY docker_build/datalake /workspace/data/datalake

#Copy start scripts
COPY run_pipeline.sh /workspace/tools/
COPY run_play.sh /workspace/tools/

# Define default command.
CMD ["bash"]
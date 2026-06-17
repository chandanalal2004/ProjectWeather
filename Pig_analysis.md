# Apache Pig Analysis on Weather Dataset

## Overview

Apache Pig was used to perform exploratory data analysis and aggregation on the cleaned weather dataset stored in HDFS. Pig Latin scripts were executed in MapReduce mode to analyze city-wise records, city-state relationships, and unique states present in the dataset.

---

## Environment Setup

```bash
cd ~/vit/hadoop-3.5.0/sbin
./start-all.sh

export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

export HADOOP_HOME=~/vit/hadoop-3.5.0
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop

export PIG_HOME=~/pig-0.17.0
export PATH=$PATH:$PIG_HOME/bin

pig -x mapreduce
```

---

## Load Dataset from HDFS

```pig
weather = LOAD '/clean_output/part-r-00000'
USING PigStorage(',')
AS (
c0:chararray,
c1:chararray,
c2:chararray,
c3:chararray,
c4:chararray,
c5:chararray,
c6:chararray,
c7:chararray,
c8:chararray,
c9:chararray,
c10:chararray,
c11:chararray,
c12:chararray,
c13:chararray
);
```

---

## Analysis 1: City-wise Record Count

```pig
city1 = FOREACH weather GENERATE c5 AS city;

city2 = GROUP city1 BY city;

city3 = FOREACH city2 GENERATE group AS city, COUNT(city1) AS total_records;

DUMP city3;
```

### Purpose

Counts the total number of weather records available for each city.

---

## Analysis 2: City-State Mapping

```pig
map1 = FOREACH weather GENERATE c5 AS city, c6 AS state;

map2 = DISTINCT map1;

DUMP map2;
```

### Purpose

Generates unique city-state mappings from the weather dataset.

---

## Analysis 3: Unique States in Dataset

```pig
state1 = FOREACH weather GENERATE c6 AS state;

state2 = DISTINCT state1;

DUMP state2;
```

### Purpose

Identifies all unique states present in the weather dataset.

---

## Outcomes

* Computed city-wise weather record counts.
* Generated unique city-state mappings.
* Identified distinct states available in the dataset.
* Demonstrated large-scale data exploration and aggregation using Apache Pig and Pig Latin on Hadoop.

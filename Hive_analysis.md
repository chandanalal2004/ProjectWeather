# Apache Hive Analysis on Weather Dataset

## 1. Verify Hive Installation

```sql
java -version
echo $JAVA_HOME
beeline -u "jdbc:hive2://"
```

---

## 2. Create Database

```sql
CREATE DATABASE weatherdb;

SHOW DATABASES;

USE weatherdb;
```

---

## 3. Create Weather Table

```sql
CREATE TABLE weather_data (
    precipitation     STRING,
    date_full         STRING,
    date_month        STRING,
    date_week         STRING,
    date_year         STRING,
    station_city      STRING,
    station_code      STRING,
    station_location  STRING,
    station_state     STRING,
    avg_temp          STRING,
    max_temp          STRING,
    min_temp          STRING,
    wind_direction    STRING,
    wind_speed        STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE;
```

---

## 4. Load Dataset into Hive

```sql
LOAD DATA LOCAL INPATH '/home/hd/ProjectWeather/weather.csv'
INTO TABLE weather_data;
```

---

## 5. Verify Data Load

```sql
SHOW TABLES;

SELECT * FROM weather_data LIMIT 5;
```

---

# Weather Data Analysis using HiveQL

## Maximum Temperature

```sql
SELECT MAX(
       CAST(regexp_replace(max_temp,'"','') AS DOUBLE)
)
FROM weather_data
WHERE max_temp != '"Data.Temperature.Max Temp"';
```

### Purpose

Finds the highest recorded temperature in the dataset.

---

## Minimum Temperature

```sql
SELECT MIN(
       CAST(regexp_replace(min_temp,'"','') AS DOUBLE)
)
FROM weather_data
WHERE min_temp != '"Data.Temperature.Min Temp"';
```

### Purpose

Finds the lowest recorded temperature in the dataset.

---

## Average Temperature

```sql
SELECT AVG(
       CAST(regexp_replace(avg_temp,'"','') AS DOUBLE)
)
FROM weather_data
WHERE avg_temp != '"Data.Temperature.Avg Temp"';
```

### Purpose

Calculates the average temperature across all weather records.

---

## Average Precipitation

```sql
SELECT AVG(
       CAST(regexp_replace(precipitation,'"','') AS DOUBLE)
)
FROM weather_data
WHERE precipitation != '"Data.Precipitation"';
```

### Purpose

Calculates the average precipitation recorded in the dataset.

---

## Summary

Apache Hive was used to perform SQL-like analytical queries on large-scale weather data stored in HDFS. HiveQL enabled efficient aggregation and statistical analysis, including:

* Maximum Temperature Analysis
* Minimum Temperature Analysis
* Average Temperature Analysis
* Average Precipitation Analysis

This demonstrates the use of Hive as a data warehousing and querying solution within the Hadoop ecosystem.

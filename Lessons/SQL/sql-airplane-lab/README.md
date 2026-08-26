---
title: Flying With SQL
type: lab
duration: "1:00"
creator: Isha Arora
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Flying With SQL Lab

## Set Up

1. Create a new database called `flights`, connect to it 
```
\c flights
```

and run the following code:

  ```sql
  CREATE TABLE airlines (
    id int,
    name varchar(255) DEFAULT NULL,
    alias varchar(255) DEFAULT NULL,
    iata varchar(255) DEFAULT NULL,
    icao varchar(255) DEFAULT NULL,
    callsign varchar(255) DEFAULT NULL,
    country varchar(255) DEFAULT NULL,
    active varchar(255) DEFAULT NULL
  );

  CREATE TABLE airports (
    id int,
    name varchar(255) DEFAULT NULL,
    city varchar(255) DEFAULT NULL,
    country varchar(255) DEFAULT NULL,
    iata_faa varchar(255) DEFAULT NULL,
    icao varchar(255) DEFAULT NULL,
    latitude varchar(255) DEFAULT NULL,
    longitude varchar(255) DEFAULT NULL,
    altitude varchar(255) DEFAULT NULL,
    utc_offset varchar(255) DEFAULT NULL,
    dst varchar(255) DEFAULT NULL,
    tz varchar(255) DEFAULT NULL
  );

  CREATE TABLE routes (
    airline_code varchar(255) DEFAULT NULL,
    airline_id int DEFAULT NULL,
    origin_code varchar(255) DEFAULT NULL,
    origin_id int DEFAULT NULL,
    dest_code varchar(255) DEFAULT NULL,
    dest_id int DEFAULT NULL,
    codeshare varchar(255) DEFAULT NULL,
    stops int DEFAULT NULL,
    equipment varchar(255) DEFAULT NULL
  );
  ```

You can check if the tables above are created by running the `\dt` command in your Psql shell. 

Clone this repo and copy [`airlines.csv`](airlines.csv), [`airports.csv`](airports.csv), and [`routes.csv`](routes.csv) to your `Desktop` directory on your computer.

Run the following code, substituting your computer's username for mine. If you're unsure of the full path, `cd` to your `Desktop` and `pwd`:

  ```sql
  \copy routes FROM '/Users/<your-username>/Desktop/routes.csv' DELIMITER ',' CSV;
  \copy airports FROM '/Users/<your-username>/Desktop/airports.csv' DELIMITER ',' CSV;
  \copy airlines FROM '/Users/<your-username>/Desktop/airlines.csv' DELIMITER ',' CSV;
  ```

> If you get "Permission denied" error, copy csv files to `C:\Windows\Temp` folder and then run the above command again with updated path `\copy routes FROM 'C:\Windows\Temp\routes.csv' DELIMITER ',' CSV;`

You should have now seeded three tables with flight data. Run `SELECT * FROM <table-name> LIMIT 10;` to confirm.

## Goal

1. Select airport code for airports LIKE `'John F Kennedy'` or `'De Gaulle'`. 
2. Select all of the flights originating from `'JFK'` AND going to `'CDG'`.
3. Find out the NUMBER of airports in Canada.
4. Select airport names IN Algeria, Ghana, and Ethiopia. Order the results by country.  
5. Select all of the airports from which `'Germania'` Airlines flies.

## Bonus

If you want more practice writing SQL queries, create an account on hackerrank.com and follow [this link](https://www.hackerrank.com/domains/sql?badge_type=sql&filters%5Bdifficulty%5D%5B%5D=easy&filters%5Bsubdomains%5D%5B%5D=select).



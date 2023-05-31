drop database if exists ncaa;
create database ncaa;

use ncaa;

drop table if exists ncaa_stats;

create table ncaa_stats( year integer,
                         team varchar(50),
                         conf varchar(5),
                         games integer,
                         wins integer,
                         postseason varchar(12),
                         seed integer,
                         constraint pkTeamYear primary key (year, team)
);
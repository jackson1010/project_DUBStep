DROP DATABASE IF EXISTS dubs_users;
CREATE DATABASE IF NOT EXISTS dubs_users;

--5 digit user_id starting from 10000;
USE dubs_users;
DROP TABLE IF EXISTS user_credentials;
CREATE TABLE IF NOT EXISTS user_credentials (
    user_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL,
    enabled BOOLEAN NOT NULL,
    authority ENUM("USER","SUPERUSER","ADMIN") NOT NULL,
    PRIMARY KEY (user_id)
);
ALTER TABLE user_credentials AUTO_INCREMENT=10000;
DESC user_credentials;
--+-------------------------+----------------------------------+------+-----+---------+----------------+
--| Field                   | Type                             | Null | Key | Default | Extra          |
--+-------------------------+----------------------------------+------+-----+---------+----------------+
--| user_id                 | smallint unsigned                | NO   | PRI | NULL    | auto_increment |
--| username                | varchar(30)                      | NO   |     | NULL    |                |
--| password                | varchar(255)                     | NO   |     | NULL    |                |
--| account_non_expired     | tinyint(1)                       | NO   |     | NULL    |                |
--| account_non_locked      | tinyint(1)                       | NO   |     | NULL    |                |
--| credentials_non_expired | tinyint(1)                       | NO   |     | NULL    |                |
--| enabled                 | tinyint(1)                       | NO   |     | NULL    |                |
--| authority               | enum('USER','SUPERUSER','ADMIN') | NO   |     | NULL    |                |
--+-------------------------+----------------------------------+------+-----+---------+----------------+

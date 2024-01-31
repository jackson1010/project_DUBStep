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

DROP TABLE IF EXISTS user_profiles;
CREATE TABLE IF NOT EXISTS user_profiles (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id SMALLINT UNSIGNED NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender ENUM("MALE","FEMALE","OTHER") NOT NULL,
    height DOUBLE(5,2) NOT NULL,
    weight DOUBLE(5,2) NOT NULL,
    blood_type ENUM("A_POSITIVE", "A_NEGATIVE","B_POSITIVE","B_NEGATIVE", "O_POSITIVE","O_NEGATIVE","AB_POSITIVE","AB_NEGATIVE"),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_credentials(user_id)
);

--+---------------+-----------------------------------------------------------------------------------------------------------------+------+-----+---------+----------------+
--| Field         | Type                                                                                                            | Null | Key | Default | Extra          |
--+---------------+-----------------------------------------------------------------------------------------------------------------+------+-----+---------+----------------+
--| id            | smallint unsigned                                                                                               | NO   | PRI | NULL    | auto_increment |
--| user_id       | smallint unsigned                                                                                               | NO   | MUL | NULL    |                |
--| first_name    | varchar(30)                                                                                                     | NO   |     | NULL    |                |
--| last_name     | varchar(30)                                                                                                     | NO   |     | NULL    |                |
--| date_of_birth | date                                                                                                            | NO   |     | NULL    |                |
--| gender        | enum('MALE','FEMALE','OTHER')                                                                                   | NO   |     | NULL    |                |
--| height        | double(5,2)                                                                                                     | NO   |     | NULL    |                |
--| weight        | double(5,2)                                                                                                     | NO   |     | NULL    |                |
--| blood_type    | enum('A_POSITIVE','A_NEGATIVE','B_POSITIVE','B_NEGATIVE','O_POSITIVE','O_NEGATIVE','AB_POSITIVE','AB_NEGATIVE') | YES  |     | NULL    |                |
--+---------------+-----------------------------------------------------------------------------------------------------------------+------+-----+---------+----------------+

DROP TABLE IF EXISTS user_contacts;
CREATE TABLE IF NOT EXISTS user_contacts (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id SMALLINT UNSIGNED NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    mobileNumber VARCHAR(30) NOT NULL,
    homeNumber VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_credentials(user_id)
);

--+--------------+-------------------+------+-----+---------+----------------+
--| Field        | Type              | Null | Key | Default | Extra          |
--+--------------+-------------------+------+-----+---------+----------------+
--| id           | smallint unsigned | NO   | PRI | NULL    | auto_increment |
--| user_id      | smallint unsigned | NO   | MUL | NULL    |                |
--| address      | varchar(255)      | NO   |     | NULL    |                |
--| email        | varchar(50)       | NO   |     | NULL    |                |
--| mobileNumber | varchar(30)       | NO   |     | NULL    |                |
--| homeNumber   | varchar(30)       | NO   |     | NULL    |                |
--+--------------+-------------------+------+-----+---------+----------------+

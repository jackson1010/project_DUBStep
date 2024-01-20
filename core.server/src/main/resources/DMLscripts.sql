INSERT INTO user_credentials (username,password,account_non_expired,account_non_locked, credentials_non_expired,enabled)
VALUES ('init','initpass', TRUE,TRUE,TRUE,TRUE);
SELECT * FROM user_credentials;
--+---------+----------+----------+---------------------+--------------------+-------------------------+---------+
--| user_id | username | password | account_non_expired | account_non_locked | credentials_non_expired | enabled |
--+---------+----------+----------+---------------------+--------------------+-------------------------+---------+
--|   10000 | init     | initpass |                   1 |                  1 |                       1 |       1 |
--+---------+----------+----------+---------------------+--------------------+-------------------------+---------+

 --CASE SENSITIVE TO ENUMS
INSERT INTO authorities(authority) VALUES ('SUPERUSER');
INSERT INTO authorities(authority) VALUES ('ADMIN');
SELECT * FROM authorities;
--+--------------+-----------+
--| authority_id | authority |
--+--------------+-----------+
--|            2 | ADMIN     |
--|            1 | SUPERUSER |
--+--------------+-----------+

INSERT INTO user_authorities(user_id,authority_id)
VALUES (10000,1),(10000,2);
SELECT * FROM user_authorities;
--+---------+--------------+
--| user_id | authority_id |
--+---------+--------------+
--|   10000 |            1 |
--|   10000 |            2 |
--+---------+--------------+
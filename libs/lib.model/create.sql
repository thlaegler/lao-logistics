CREATE USER 'lao'@'%' IDENTIFIED VIA mysql_native_password USING 'lao';GRANT USAGE ON *.* TO 'lao'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;CREATE DATABASE IF NOT EXISTS `lao`;GRANT ALL PRIVILEGES ON `lao`.* TO 'lao'@'%';GRANT ALL PRIVILEGES ON `lao\_%`.* TO 'lao'@'%';
REVOKE ALL PRIVILEGES ON *.* FROM 'lao'@'%'; GRANT ALL PRIVILEGES ON *.* TO 'lao'@'%' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
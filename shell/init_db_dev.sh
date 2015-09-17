#!/usr/bin/env bash


HOST_NAME="localhost"
ROOT_PASS=""

DB_USER="sjyuan"
DB_PASS="admin"
DB_NAME="spring_boot_mybitas"

CREATE_DB="DROP DATABASE IF EXISTS $DB_NAME;CREATE DATABASE $DB_NAME CHARACTER SET UTF8;"

CREATE_USER="
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ANSI';
USE $DB_NAME;
DROP PROCEDURE IF EXISTS $DB_NAME.drop_user_if_exists ;
DELIMITER $$
CREATE PROCEDURE $DB_NAME.drop_user_if_exists()
BEGIN
  DECLARE foo BIGINT DEFAULT 0 ;
  SELECT COUNT(*)
  INTO foo
    FROM mysql.user
      WHERE User = '$DB_USER' and  Host = '$HOST_NAME';
   IF foo > 0 THEN
         DROP USER '$DB_USER'@'$HOST_NAME' ;
  END IF;
END ;$$
DELIMITER ;
CALL $DB_NAME.drop_user_if_exists() ;
DROP PROCEDURE IF EXISTS $DB_NAME.drop_users_if_exists ;
SET SQL_MODE=@OLD_SQL_MODE ;

CREATE USER '$DB_USER'@'$HOST_NAME' IDENTIFIED BY '$DB_PASS';
GRANT ALL PRIVILEGES ON $DB_NAME.* TO '$DB_USER'@'$HOST_NAME' IDENTIFIED BY '$DB_PASS';
FLUSH PRIVILEGES;"


if ["" = "$ROOT_PASS"]; then
    MYSQL -u root -e "$CREATE_DB$CREATE_USER"
else
    MYSQL -u root -p$ROOT_PASS -e "$CREATE_DB$CREATE_USER"
fi


if [ $? != "0" ]; then
 echo "=========================================="
 echo "[Error]: Database create failed!"
 echo "=========================================="
 exit 1
else
 echo "------------------------------------------"
 echo " Database [$DB_NAME] create successful~ "
 echo "------------------------------------------"
 echo " DB Information: "
 echo ""
 echo " DB User: $DB_USER"
 echo " DB Name: $DB_NAME"
 echo ""
 echo "------------------------------------------"
fi

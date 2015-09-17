#!/usr/bin/env bash


DB_USER_ROOT="root"
DB_ROOT_PASS=""
DB_NAME="spring_boot_mybatis"

DROP_DB="DROP DATABASE IF EXISTS $DB_NAME;"
CREATE_DB="CREATE DATABASE $DB_NAME CHARACTER SET UTF8;"
GRANT="GRANT ALL PRIVILEGES ON $DB_NAME.* TO $DB_USER_ROOT@$HOST_NAME IDENTIFIED BY '$DB_ROOT_PASS';FLUSH PRIVILEGES;"


if ["" = "$DB_ROOT_PASS"]; then
    MYSQL -u $DB_USER_ROOT -e "$DROP_DB$CREATE_DB$GRANT"
else
    MYSQL -u $DB_USER_ROOT -p$DB_ROOT_PASS -e "$DROP_DB$CREATE_DB$GRANT"
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
 echo " DB User: $DB_USER_ROOT"
 echo " DB Name: $DB_NAME"
 echo ""
 echo "------------------------------------------"
fi
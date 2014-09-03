#!/bin/sh

echo 'creating dump file'
mysqldump -uroot -ppassword dms > /tmp/dms_mysql_dump.sql

exit
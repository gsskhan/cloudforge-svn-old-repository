#!/bin/sh

echo 'creating dump file'
mysqldump -uroot -ppassword dms > /tmp/dms_dump.sql

exit
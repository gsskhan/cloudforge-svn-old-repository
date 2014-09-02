#!/bin/sh

echo 'creating dump files'
mongodump --host localhost --port 27017 -db dms --out /tmp/

echo 'archiving and compressing dump files'
cd /tmp
tar -zcvf dms.tar.gz dms

echo 'cleaned up disk space'
rm -r /tmp/dms

exit
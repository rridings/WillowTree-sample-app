#!/bin/bash

if [  $# -le 0 ] 
then 
	echo -e "\nUsage:\n$0 [number of concurrent requests] \n"
	exit 1
fi 

/ScholarOne/httpd-2.2.19_64bit_web/bin/ab -n 1 -c 1 -p post -v 4 "http://localhost:4567/asset"

/ScholarOne/httpd-2.2.19_64bit_web/bin/ab -n $1*100 -c $1 -v 1 "http://localhost:4567/asset/myorg://users/test"

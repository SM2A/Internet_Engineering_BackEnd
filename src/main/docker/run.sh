#!/bin/sh

echo "***********************************************************"
echo "****************** Starting Baloot service ****************"
echo "***********************************************************"

if [[ $SPRING_PROFILE != "dev" ]]; then
    echo "***********************************************************"
    echo "**************** Waiting for MySQL to start ***************"
    echo "***********************************************************"
    while ! `nc -z mysql_database 3306`; do sleep 3; done
    echo "**************** MySQL Database has started ***************"
    else
      echo "********** No Waiting for MySQL as dev profile **********"
fi
echo "*************** Running Baloot service... ****************"
exec java -jar /usr/local/service/@project.build.finalName@.jar

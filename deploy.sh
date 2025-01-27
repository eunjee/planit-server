#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/planit-server
cd $REPOSITORY

APP_NAME=planit-server
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

./gradlew clean build

echo "> $JAR_PATH 배포"
nohup java -jar -Dspring.profiles.active=prod $JAR_PATH > /dev/null 2> /dev/null < /dev/null &

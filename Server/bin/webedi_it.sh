#!/bin/bash

AppName=WebEDI-1.0.0-SNAPSHOT.jar

#JVM
JVM_OPTS="-Dname=$AppName"
SPRING_ARGS="--spring.profiles.active=it"
APP_HOME=`pwd`
LOG_PATH=$APP_HOME/logs/$AppName.log

if [[ ! -d "$APP_HOME/logs/" ]] ;
then
    echo "Log Folder doesn't exist. Create now!"
    mkdir -p "$APP_HOME/logs/"
fi

if [ "$1" = "" ];
then
    echo -e "\033[0;31m Please input the operator \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$AppName" = "" ];
then
    echo -e "\033[0;31m Please input the Apllication's name \033[0m"
    exit 1
fi

function start()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`

	if [ x"$PID" != x"" ]; then
	    echo "$AppName is running..."
	else
		# nohup java -jar  $JVM_OPTS $APP_HOME/$AppName > /dev/null 2>&1 &
		nohup java -jar  $JVM_OPTS $APP_HOME/$AppName $SPRING_ARGS > $LOG_PATH 2>&1 &
		echo "Start $AppName success..."
	fi
}

function stop()
{
    echo "Stop $AppName"

	PID=""
	query(){
		PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`
	}

	query
	if [ x"$PID" != x"" ]; then
		kill -TERM $PID
		echo "$AppName (pid:$PID) exiting..."
		while [ x"$PID" != x"" ]
		do
			sleep 1
			query
		done
		echo "$AppName exited."
	else
		echo "$AppName already stopped."
	fi
}

function restart()
{
    stop
    sleep 2
    start
}

function status()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|wc -l`
    if [ $PID != 0 ];then
        echo "$AppName is running..."
    else
        echo "$AppName is not running..."
    fi
}

case $1 in
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    *)

esac

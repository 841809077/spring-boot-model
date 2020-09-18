#!/bin/sh
if [ "x$JavaOpts" = "x" ]
then
	JavaOpts="-Xmx1024m -Xms512m"
fi
if [ "x$UserName" = "x" ]
then
	echo "User name no set!"
	exit 0
fi
id $UserName >& /dev/null
if [ $? -ne 0 ]
then
	echo "$UserName not exits!"
fi
if [ "x$AppName" = "x" ]
then
    echo "App name not set!"
    exit 0
fi
if [ "x$Package" = "x" ]
then
    echo "App package not set!"
    exit 0
fi
checkingbooted()
{
    sleep 2
    result=$(jps | grep -w "$AppName")
    ret=$?
    if [ $ret -eq 0 ]
    then
        pid=$(echo $result | awk '{print $1}')
        echo "The program[${pid}] started!!!"
    else
        echo "There is something wrong"
    fi
}
# Set app home
SCRIPT=$0
APP_HOME=$(dirname "$SCRIPT")/..
APP_HOME=$(cd "$APP_HOME"; pwd)
BASE=$1
shift
# Set classpath
APP_CLASSPATH=$APP_CLASSPATH:$APP_HOME/lib/*:$APP_HOME/conf
check_running() {
    result=$(jps | grep -w "$AppName")
    ret=$?
    if [ $ret -eq 0 ]
    then
        pid=$(echo $result | awk '{print $1}')
        echo "The program[${pid}] is running."
        exit
    else
        echo "The program was not running."
    fi
}
usage() {
    echo "Usage: $BASE [-dh] [--help] [stop|restart|status|debug]"
    echo "Start $AppName."
    echo "    stop          stop program"
    echo "    restart       restart program in background"
    echo "    status        check program status"
    echo "    debug         debug program"
    echo "    -d            run program in background"
    echo "    -h|--help     print command line options"
}
launch_service() {
    cd $APP_HOME
    daemonized=$1
	  command="java $JavaOpts -cp $APP_CLASSPATH $Package.$AppName"
    #echo $command
    if [ "x$daemonized" = "x" ]; then
        exec $command
	  else
        exec $command > /dev/null 2>&1 &
        return $?
    fi
}
stop_service() {
    result=$(jps | grep -w "$AppName")
    ret=$?
    if [ $ret -eq 0 ]
    then
        pid=$(echo $result | awk '{print $1}')
        echo "The program[${pid}] is running!!! Now stop it!!!"
        kill -9 $pid
        kill -0 $pid > /dev/null 2>&1
        ret=$?
        while [ $ret -eq 0 ]
        do
            sleep 1
            kill -0 $pid > /dev/null 2>&1
            ret=$?
        done
        echo "The program[${pid}] stopped!!!"
    else
        echo "No program need to stop!!!"
    fi
}
debug_service() {
    port=5005
    if [ "x$1" != "x" ]
    then
      port=$!
    fi
    command="java $JavaOpts -cp $APP_CLASSPATH -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$port $Package.$AppName"
    exec $command
}

user=$(whoami)
if [ $user != "$UserName" ]
then
    echo "Start error. Can not use[${user}], please use[${UserName}] to start!"
    exit 1
fi

ARGS=`getopt -o dh --long help -n $BASE -- "$@"`
#将规范化后的命令行参数分配至位置参数（$1,$2,...)
eval set -- "${ARGS}"

# if $1 has value, enter case statement
while [ -n "$1" ]; do
    case $1 in
        stop)
            stop_service
            exit 0
        ;;
        restart)
            stop_service
            launch_service "yes"
            checkingbooted
            exit 0
        ;;
        status)
            check_running
            exit 0
        ;;
        debug)
            case "$2" in
                "")
                    debug_service
                    exit 0
                ;;
                *)
                    debug_service $2
                    exit 0
                ;;
            esac
        ;;
        -d)
            daemonized="yes"
            launch_service "$daemonized"
            checkingbooted
            exit 0
        ;;
        -h|--help)
            usage
            exit 0
        ;;
        --)
            shift
        ;;
        *)
            echo "$BASE missing optstring argument -- [$*]"
            echo "Try '$BASE --help' for more information."
            exit 1
        ;;
    esac
done
launch_service
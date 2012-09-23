#!/bin/sh

rm *.class

#Main classes
SERVER=Server
CLIENT=ChatClient

#Stubs
#STUB=ChatImpl

#Compile
javac $SERVER.java;
javac $CLIENT.java;

#RMI
rmiregistry &
RMIPID=$!

#Run
java $SERVER &
SPID=$!
echo "$SERVER started in background $SPID"

sleep 1
echo "Starting client"
java $CLIENT localhost

echo "Shutting down Server $SPID"
kill -15 $SPID
echo "Shutting down RMI $RMIPID"
kill -15 $RMIPID


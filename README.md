# gRPC-Echo-Service
This is a sample maven echo service using gRPC and Google Protocol Buffers. The echo service will take a message and echo it back along with the date.

## Compiling
Compile the project by navigating to the project folder and running 
``` 
mvn install 
```

## Running the program
In one console, navigate to the target folder and run the EchoServer 
``` 
java -jar EchoServer.jar 
```

In another console, navigate to the target folder and run the EchoClient
``` 
java -jar EchoClient.jar 
```

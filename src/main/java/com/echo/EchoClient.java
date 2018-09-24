package com.echo;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector.Level;

public class EchoClient {

	private final ManagedChannel channel;
	private final EchoGrpc.EchoBlockingStub blockingStub;

	public EchoClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port)
		        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
		        // needing certificates.
		        .usePlaintext()
		        .build());
	}


	public EchoClient(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = EchoGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}


	public void sendMessage(String str) {
		MessageRequest request = MessageRequest.newBuilder().setMessage(str).build();

		MessageResponse response;

		try {
		      response = blockingStub.processMessage(request);
		      System.out.println(response.getMessage() + " " + response.getDate());
		} catch (StatusRuntimeException e) {
			System.out.println("Level.WARNING" + "RPC failed: {0} " + e.getStatus());
		     // logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
		      return;
		}

	}


	public static void main(String[] args) throws InterruptedException {

		 EchoClient client = new EchoClient("localhost", 50051);


		 try {
		      /* Access a service running on the local machine on port 50051 */
		      String user = "Hello World";


		      client.sendMessage(user);

		  } finally {
		      client.shutdown();
		}

	}

}

package com.echo;

import io.grpc.stub.StreamObserver;
import java.util.Date;

public class EchoImpl extends EchoGrpc.EchoImplBase{

	@Override
	public void processMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
		// TODO Auto-generated method stub

		MessageResponse res = processMessageHelper(request);
		responseObserver.onNext(processMessageHelper(request));
		responseObserver.onCompleted();



		//We construct and populate a Feature response object to return to the client, as specified in our service definition. In this example, we do this in a separate private checkFeature() method.
		//		We use the response observer’s onNext() method to return the Feature.

		// We use the response observer’s onCompleted() method to specify that we’ve finished dealing with the RPC.

		//super.processMessage(request, responseObserver);
	}


	private MessageResponse processMessageHelper(MessageRequest request) {
		String message = request.getMessage();

		// builder pattern

		Date date = new Date();

		String dateString = date.toString();

		MessageResponse response = MessageResponse.newBuilder().setMessage(message).setDate(dateString).build();

		return response;
	}
}

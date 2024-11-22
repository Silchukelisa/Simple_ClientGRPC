package org.tyt;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.tyt.grpc.GreetingServiceGrpc;
import org.tyt.grpc.GreetingServiceOuterClass;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest
                        .newBuilder()
                        .setName("Liza")
                        .build();

        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request);

        //для потока данных
        /*Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);
        while (response.hasNext()) {
            System.out.println(response.next());
        }*/

        System.out.println(response);
        channel.shutdownNow();
    }

}

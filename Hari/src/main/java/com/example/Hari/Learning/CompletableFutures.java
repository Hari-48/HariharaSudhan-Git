package com.example.Hari.Learning;

import java.util.concurrent.CompletableFuture;

public class CompletableFutures {



    public void supplyAsync() throws  Exception{

        /*This creates a CompletableFuture that will execute
         the lambda function passed to supplyAsync in a separate thread.
        And after the execution, the result lambda function is returned by CompletableFuture Object
         */

        CompletableFuture<String> greetings = CompletableFuture.supplyAsync(()->{
            return "Hi ,Welcome ";
        });
        System.out.println(greetings.get());

    }

    public void  example(){

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, world!";
        });
        future.thenAccept(result -> System.out.println(result));
    }





}

package org.sfeir.maxime.mqapp.application.port.out;

import java.util.concurrent.CompletableFuture;

public interface MqGateway {
    CompletableFuture<Void> sendMessage(String destination, String message);
    void receiveMessage(String message);
}
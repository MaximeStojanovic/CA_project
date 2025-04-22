package org.sfeir.maxime.mqapp.application.service;

public interface JmsService {
    void sendMessage(String destination, String message);
} 
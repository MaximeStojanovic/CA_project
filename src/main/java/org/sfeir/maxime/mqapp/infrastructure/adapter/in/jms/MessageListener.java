package org.sfeir.maxime.mqapp.infrastructure.adapter.in.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.sfeir.maxime.mqapp.application.service.MessageServiceImpl;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {

    private final MessageServiceImpl messageService;

    @JmsListener(destination = "${ibm.mq.queue}")
    public void receiveMessage(String message) {
        log.info("Received message from queue: {}", message);
        try {
            messageService.processMessage(message);
            log.info("Message processed successfully");
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
        }
    }
} 
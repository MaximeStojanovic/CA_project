package org.sfeir.maxime.mqapp.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsServiceImpl implements JmsService {
    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }
} 
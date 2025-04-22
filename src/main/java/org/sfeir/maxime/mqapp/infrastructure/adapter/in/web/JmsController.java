package org.sfeir.maxime.mqapp.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.sfeir.maxime.mqapp.application.service.JmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/jms")
@RequiredArgsConstructor
public class JmsController {
    private static final Logger logger = LogManager.getLogger(JmsController.class);
    private final JmsService jmsService;

    @PostMapping("/send/{destination}")
    public ResponseEntity<Void> sendMessage(
            @PathVariable String destination,
            @RequestBody String message) {
        logger.info("Sending message to destination: {}", destination);
        jmsService.sendMessage(destination, message);
        logger.debug("Message sent successfully to destination: {}", destination);
        return ResponseEntity.ok().build();
    }
} 
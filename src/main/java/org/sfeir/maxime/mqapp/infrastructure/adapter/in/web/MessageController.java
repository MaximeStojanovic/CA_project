package org.sfeir.maxime.mqapp.infrastructure.adapter.in.web;

import lombok.AllArgsConstructor;
import org.sfeir.maxime.mqapp.application.service.MessageServiceImpl;
import org.sfeir.maxime.mqapp.domain.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private static final Logger logger = LogManager.getLogger(MessageController.class);
    private final MessageServiceImpl messageService;

    @GetMapping()
    public ResponseEntity<List<Message>> getAllMessages() {
        logger.info("Getting all messages");
        List<Message> messages = messageService.getAllMessages();
        logger.debug("Retrieved {} messages", messages.size());
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable String id) {
        logger.info("Getting message by id: {}", id);
        Optional<Message> message = messageService.getMessageById(id);
        logger.debug("Message found: {}", message.isPresent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/process")
    public ResponseEntity<Message> processMessage(@RequestParam String messageContent) {
        logger.info("Processing message: {}", messageContent);
        Message message = messageService.processMessage(messageContent);
        logger.debug("Message processed successfully with id: {}", message.getId());
        return ResponseEntity.ok(message);
    }
}

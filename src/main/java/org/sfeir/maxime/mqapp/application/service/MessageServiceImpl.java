package org.sfeir.maxime.mqapp.application.service;

import lombok.extern.slf4j.Slf4j;
import org.sfeir.maxime.mqapp.application.port.in.MessageUseCase;
import org.sfeir.maxime.mqapp.application.port.out.MessageRepository;
import org.sfeir.maxime.mqapp.domain.model.Message;
import org.sfeir.maxime.mqapp.domain.model.MessageStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageUseCase {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        log.debug("Fetching all messages");
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> getMessageById(String id) {
        log.debug("Fetching message by id: {}", id);
        return messageRepository.findById(id);
    }

    @Override
    @Transactional
    public Message processMessage(String messageContent) {
        if (messageContent == null || messageContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }

        log.debug("Processing new message with content: {}", messageContent);
        Message message = new Message(
                UUID.randomUUID().toString(),
                messageContent,
                "SYSTEM",
                "SYSTEM",
                LocalDateTime.now(),
                MessageStatus.RECEIVED
        );
        Message savedMessage = messageRepository.save(message);
        log.info("Message processed successfully with id: {}", savedMessage.getId());
        return savedMessage;
    }
}

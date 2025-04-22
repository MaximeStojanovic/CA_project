package org.sfeir.maxime.mqapp.application.port.in;

import org.sfeir.maxime.mqapp.domain.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageUseCase {
    List<Message> getAllMessages();
    Optional<Message> getMessageById(String id);
    Message processMessage(String messageContent);
}
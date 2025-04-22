package org.sfeir.maxime.mqapp.application.port.out;

import org.sfeir.maxime.mqapp.domain.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    List<Message> findAll();
    Optional<Message> findById(String id);
    Message save(Message message);
}

package org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.repositories;

import org.sfeir.maxime.mqapp.application.port.out.MessageRepository;
import org.sfeir.maxime.mqapp.domain.model.Message;
import org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.entities.MessageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageRepositoryAdapter implements MessageRepository {
    private final MessageJpaRepository repository;

    public MessageRepositoryAdapter(MessageJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<Message> findById(String id) {
        return repository.findById(id)
                .map(this::mapToDomainEntity);
    }

    @Override
    public Message save(Message message) {
        MessageEntity entity = mapToJpaEntity(message);
        MessageEntity savedEntity = repository.save(entity);
        return mapToDomainEntity(savedEntity);
    }

    private Message mapToDomainEntity(MessageEntity entity) {
        return new Message(
                entity.getId(),
                entity.getContent(),
                entity.getSender(),
                entity.getRecipient(),
                entity.getTimestamp(),
                entity.getStatus()
        );
    }

    private MessageEntity mapToJpaEntity(Message message) {
        MessageEntity entity = new MessageEntity();
        entity.setId(message.getId());
        entity.setContent(message.getContent());
        entity.setSender(message.getSender());
        entity.setRecipient(message.getRecipient());
        entity.setTimestamp(message.getTimestamp());
        entity.setStatus(message.getStatus()); 
        return entity;
    }
}
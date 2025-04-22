package org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.repositories;

import org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, String> {
}
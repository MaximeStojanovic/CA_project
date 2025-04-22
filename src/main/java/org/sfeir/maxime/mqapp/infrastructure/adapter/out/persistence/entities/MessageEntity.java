package org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import org.sfeir.maxime.mqapp.domain.model.MessageStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "messages")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MessageEntity {
    @Id
    private String id;

    @Column(length = 4000)
    private String content;

    private String sender;
    private String recipient;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    // Getters, setters, constructors

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ? 
            hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ? 
            hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        MessageEntity that = (MessageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        if (this instanceof HibernateProxy hibernateProxy)
            return hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode();
        return getClass().hashCode();
    }
}


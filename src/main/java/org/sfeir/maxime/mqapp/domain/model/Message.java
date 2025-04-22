package org.sfeir.maxime.mqapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Message content cannot be blank")
    @Column(nullable = false)
    private String content;

    @NotBlank(message = "Sender cannot be blank")
    @Column(nullable = false)
    private String sender;

    @NotBlank(message = "Recipient cannot be blank")
    @Column(nullable = false)
    private String recipient;

    @NotNull(message = "Timestamp cannot be null")
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status;
}


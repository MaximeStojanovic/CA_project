package org.sfeir.maxime.mqapp.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sfeir.maxime.mqapp.application.port.out.MessageRepository;
import org.sfeir.maxime.mqapp.domain.model.Message;
import org.sfeir.maxime.mqapp.domain.model.MessageStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Message Service Tests")
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Nested
    @DisplayName("When retrieving messages")
    class RetrieveMessagesTests {

        @Test
        @DisplayName("should return all messages when repository has messages")
        void shouldReturnAllMessages() {
            // Arrange
            List<Message> expectedMessages = Arrays.asList(
                createTestMessage("1", "Test message 1"),
                createTestMessage("2", "Test message 2")
            );
            when(messageRepository.findAll()).thenReturn(expectedMessages);

            // Act
            List<Message> result = messageService.getAllMessages();

            // Assert
            assertThat(result).isNotNull()
                .hasSize(2)
                .containsExactlyElementsOf(expectedMessages);
            verify(messageRepository).findAll();
        }

        @Test
        @DisplayName("should return empty list when repository has no messages")
        void shouldReturnEmptyList() {
            // Arrange
            when(messageRepository.findAll()).thenReturn(List.of());

            // Act
            List<Message> result = messageService.getAllMessages();

            // Assert
            assertThat(result).isNotNull().isEmpty();
            verify(messageRepository).findAll();
        }
    }

    @Nested
    @DisplayName("When retrieving message by ID")
    class RetrieveMessageByIdTests {

        @Test
        @DisplayName("should return message when ID exists")
        void shouldReturnMessageWhenIdExists() {
            // Arrange
            String messageId = "test-id";
            Message expectedMessage = createTestMessage(messageId, "Test message");
            when(messageRepository.findById(messageId)).thenReturn(Optional.of(expectedMessage));

            // Act
            Optional<Message> result = messageService.getMessageById(messageId);

            // Assert
            assertThat(result).isPresent()
                .contains(expectedMessage);
            verify(messageRepository).findById(messageId);
        }

        @Test
        @DisplayName("should return empty optional when ID does not exist")
        void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
            // Arrange
            String nonExistentId = "non-existent-id";
            when(messageRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            // Act
            Optional<Message> result = messageService.getMessageById(nonExistentId);

            // Assert
            assertThat(result).isEmpty();
            verify(messageRepository).findById(nonExistentId);
        }
    }

    @Nested
    @DisplayName("When processing messages")
    class ProcessMessageTests {

        @Test
        @DisplayName("should save message when valid content is provided")
        void shouldSaveMessageWhenValidContent() {
            // Arrange
            String content = "Test message";
            Message savedMessage = createTestMessage(UUID.randomUUID().toString(), content);
            when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

            // Act
            Message result = messageService.processMessage(content);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getContent()).isEqualTo(content);
            assertThat(result.getStatus()).isEqualTo(MessageStatus.RECEIVED);
            verify(messageRepository).save(any(Message.class));
        }

        @Test
        @DisplayName("should throw exception when content is null")
        void shouldThrowExceptionWhenContentIsNull() {
            // Arrange
            String content = null;

            // Act & Assert
            assertThrows(IllegalArgumentException.class,
                () -> messageService.processMessage(content));
            verifyNoInteractions(messageRepository);
        }

        @Test
        @DisplayName("should throw exception when content is empty")
        void shouldThrowExceptionWhenContentIsEmpty() {
            // Arrange
            String content = "";

            // Act & Assert
            assertThrows(IllegalArgumentException.class,
                () -> messageService.processMessage(content));
            verifyNoInteractions(messageRepository);
        }
    }

    private Message createTestMessage(String id, String content) {
        return new Message(
            id,
            content,
            "SYSTEM",
            "SYSTEM",
            LocalDateTime.now(),
            MessageStatus.RECEIVED
        );
    }
} 
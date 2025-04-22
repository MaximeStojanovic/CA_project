package org.sfeir.maxime.mqapp.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sfeir.maxime.mqapp.application.port.out.PartnerRepository;
import org.sfeir.maxime.mqapp.domain.model.Direction;
import org.sfeir.maxime.mqapp.domain.model.Partner;
import org.sfeir.maxime.mqapp.domain.model.ProcessedFlowType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Partner Service Tests")
class PartnerServiceImplTest {

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private PartnerServiceImpl partnerService;

    @Nested
    @DisplayName("When retrieving partners")
    class RetrievePartnersTests {

        @Test
        @DisplayName("should return all partners when repository has partners")
        void shouldReturnAllPartners() {
            // Arrange
            List<Partner> expectedPartners = Arrays.asList(
                createTestPartner("1", "Partner1"),
                createTestPartner("2", "Partner2")
            );
            when(partnerRepository.findAll()).thenReturn(CompletableFuture.completedFuture(expectedPartners));

            // Act
            CompletableFuture<List<Partner>> resultFuture = partnerService.getAllPartners();
            List<Partner> result = resultFuture.join();

            // Assert
            assertThat(result).isNotNull()
                .hasSize(2)
                .containsExactlyElementsOf(expectedPartners);
            verify(partnerRepository).findAll();
        }

        @Test
        @DisplayName("should return empty list when repository has no partners")
        void shouldReturnEmptyList() {
            // Arrange
            when(partnerRepository.findAll()).thenReturn(CompletableFuture.completedFuture(List.of()));

            // Act
            CompletableFuture<List<Partner>> resultFuture = partnerService.getAllPartners();
            List<Partner> result = resultFuture.join();

            // Assert
            assertThat(result).isNotNull().isEmpty();
            verify(partnerRepository).findAll();
        }
    }

    @Nested
    @DisplayName("When retrieving partner by ID")
    class RetrievePartnerByIdTests {

        @Test
        @DisplayName("should return partner when ID exists")
        void shouldReturnPartnerWhenIdExists() {
            // Arrange
            String partnerId = "test-id";
            Partner expectedPartner = createTestPartner(partnerId, "Test Partner");
            when(partnerRepository.findById(partnerId))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(expectedPartner)));

            // Act
            CompletableFuture<Optional<Partner>> resultFuture = partnerService.getPartnerById(partnerId);
            Optional<Partner> result = resultFuture.join();

            // Assert
            assertThat(result).isPresent()
                .contains(expectedPartner);
            verify(partnerRepository).findById(partnerId);
        }

        @Test
        @DisplayName("should return empty optional when ID does not exist")
        void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
            // Arrange
            String nonExistentId = "non-existent-id";
            when(partnerRepository.findById(nonExistentId))
                .thenReturn(CompletableFuture.completedFuture(Optional.empty()));

            // Act
            CompletableFuture<Optional<Partner>> resultFuture = partnerService.getPartnerById(nonExistentId);
            Optional<Partner> result = resultFuture.join();

            // Assert
            assertThat(result).isEmpty();
            verify(partnerRepository).findById(nonExistentId);
        }
    }

    @Nested
    @DisplayName("When creating partners")
    class CreatePartnerTests {

        @Test
        @DisplayName("should save partner when valid data is provided")
        void shouldSavePartnerWhenValidData() {
            // Arrange
            Partner partnerToSave = createTestPartner(UUID.randomUUID().toString(), "New Partner");
            when(partnerRepository.save(any(Partner.class)))
                .thenReturn(CompletableFuture.completedFuture(partnerToSave));

            // Act
            CompletableFuture<Partner> resultFuture = partnerService.createPartner(partnerToSave);
            Partner result = resultFuture.join();

            // Assert
            assertThat(result).isNotNull()
                .isEqualTo(partnerToSave);
            verify(partnerRepository).save(partnerToSave);
        }

        @Test
        @DisplayName("should throw exception when partner is null")
        void shouldThrowExceptionWhenPartnerIsNull() {
            // Arrange
            Partner nullPartner = null;

            // Act & Assert
            assertThrows(IllegalArgumentException.class,
                () -> partnerService.createPartner(nullPartner));
            verifyNoInteractions(partnerRepository);
        }
    }

    private Partner createTestPartner(String id, String alias) {
        return new Partner(
            id,
            alias,
            "TYPE",
            Direction.INBOUND,
            "APP",
            ProcessedFlowType.MESSAGE,
            "Test Description"
        );
    }
} 
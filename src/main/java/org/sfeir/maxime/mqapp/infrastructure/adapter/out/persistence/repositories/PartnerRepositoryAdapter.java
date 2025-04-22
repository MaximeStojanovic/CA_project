package org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.repositories;

import lombok.AllArgsConstructor;
import org.sfeir.maxime.mqapp.application.port.out.PartnerRepository;
import org.sfeir.maxime.mqapp.domain.model.Partner;
import org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.entities.PartnerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class PartnerRepositoryAdapter implements PartnerRepository {
    private final PartnerJpaRepository repository;


    @Override
    public CompletableFuture<List<Partner>> findAll() {
        return CompletableFuture.supplyAsync(() ->
                                                     repository.findAll().stream()
                                                               .map(this::mapToDomainEntity)
                                                               .toList()
                                            );
    }

    @Override
    public CompletableFuture<Optional<Partner>> findById(String id) {
        return CompletableFuture.supplyAsync(() ->
                                                     repository.findById(id)
                                                               .map(this::mapToDomainEntity)
                                            );
    }

    @Override
    public CompletableFuture<Partner> save(Partner partner) {
        return CompletableFuture.supplyAsync(() -> {
            PartnerEntity entity = mapToJpaEntity(partner);
            PartnerEntity savedEntity = repository.save(entity);
            return mapToDomainEntity(savedEntity);
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteById(String id) {
        return null;
    }

    private Partner mapToDomainEntity(PartnerEntity entity) {
        return new Partner(
                entity.getId(),
                entity.getAlias(),
                entity.getType(),
                entity.getDirection(),
                entity.getApplication(),
                entity.getProcessedFlowType(),
                entity.getDescription());

    }

    private PartnerEntity mapToJpaEntity(Partner partner) {
        PartnerEntity entity = new PartnerEntity();
        entity.setId(partner.getId());
        entity.setAlias(partner.getAlias());
        entity.setType(partner.getType());
        entity.setApplication(partner.getApplication());
        entity.setDescription(partner.getDescription());
        entity.setDirection(partner.getDirection());
        entity.setProcessedFlowType(partner.getProcessedFlowType());
        return entity;
    }

}


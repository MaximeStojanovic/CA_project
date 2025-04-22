package org.sfeir.maxime.mqapp.application.service;

import lombok.extern.slf4j.Slf4j;
import org.sfeir.maxime.mqapp.application.port.in.PartnerUseCase;
import org.sfeir.maxime.mqapp.application.port.out.PartnerRepository;
import org.sfeir.maxime.mqapp.domain.model.Partner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PartnerServiceImpl implements PartnerUseCase {
    private final PartnerRepository partnerRepository;

    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public CompletableFuture<List<Partner>> getAllPartners() {
        log.debug("Fetching all partners");
        return partnerRepository.findAll();
    }

    @Override
    public CompletableFuture<Optional<Partner>> getPartnerById(String id) {
        log.debug("Fetching partner by id: {}", id);
        return partnerRepository.findById(id);
    }

    @Override
    @Transactional
    public CompletableFuture<Partner> createPartner(Partner partner) {
        if (partner == null) {
            throw new IllegalArgumentException("Partner cannot be null");
        }

        log.debug("Creating new partner: {}", partner);
        return partnerRepository.save(partner)
            .thenApply(savedPartner -> {
                log.info("Partner created successfully with id: {}", savedPartner.getId());
                return savedPartner;
            });
    }

    @Override
    public CompletableFuture<Boolean> deletePartner(String id) {
        return partnerRepository.deleteById(id);
    }
}
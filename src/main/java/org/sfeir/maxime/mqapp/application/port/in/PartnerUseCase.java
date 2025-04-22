package org.sfeir.maxime.mqapp.application.port.in;

import org.sfeir.maxime.mqapp.domain.model.Partner;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PartnerUseCase {
    CompletableFuture<List<Partner>> getAllPartners();
    CompletableFuture<Optional<Partner>> getPartnerById(String id);
    CompletableFuture<Partner> createPartner(Partner partner);
    CompletableFuture<Boolean> deletePartner(String id);
}
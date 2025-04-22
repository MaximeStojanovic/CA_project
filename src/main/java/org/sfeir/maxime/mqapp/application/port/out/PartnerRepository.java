package org.sfeir.maxime.mqapp.application.port.out;

import org.sfeir.maxime.mqapp.domain.model.Partner;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PartnerRepository {
    CompletableFuture<List<Partner>> findAll();
    CompletableFuture<Optional<Partner>> findById(String id);
    CompletableFuture<Partner> save(Partner partner);
    CompletableFuture<Boolean> deleteById(String id);
}
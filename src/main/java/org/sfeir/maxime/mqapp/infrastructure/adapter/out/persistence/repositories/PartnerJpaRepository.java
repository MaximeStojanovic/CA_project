package org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.repositories;

import org.sfeir.maxime.mqapp.infrastructure.adapter.out.persistence.entities.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerJpaRepository extends JpaRepository<PartnerEntity, String> {
}

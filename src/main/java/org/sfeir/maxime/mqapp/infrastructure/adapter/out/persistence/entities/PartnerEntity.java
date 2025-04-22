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
import org.sfeir.maxime.mqapp.domain.model.Direction;
import org.sfeir.maxime.mqapp.domain.model.ProcessedFlowType;

import java.util.Objects;

@Entity
@Table(name = "partner")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PartnerEntity {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @Column
    private String application;

    @Enumerated(EnumType.STRING)
    @Column(name = "processed_flow_type", nullable = false)
    private ProcessedFlowType processedFlowType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    // Getters, setters, constructors

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        PartnerEntity that = (PartnerEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
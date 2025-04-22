package org.sfeir.maxime.mqapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Alias cannot be blank")
    @Column(nullable = false, unique = true)
    private String alias;

    @NotBlank(message = "Type cannot be blank")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "Direction cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @NotBlank(message = "Application cannot be blank")
    @Column(nullable = false)
    private String application;

    @NotNull(message = "Processed flow type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProcessedFlowType processedFlowType;

    @Column(length = 1000)
    private String description;
}


package com.exxeta.demo.middlewaretextvorsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name ="dataTable")
    public class Data {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private UUID id;

        @Column(nullable=false)
        private String payload;

        private Instant createdAt = Instant.now();

    }


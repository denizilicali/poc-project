package com.exxeta.demo.middlewaretextvorsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name ="data_table")
    public class Data {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable=false)
        private String content;

        private Instant createdAt = Instant.now();

    }


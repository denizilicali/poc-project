package com.exxeta.demo.middlewaretextvorsystem.repository;

import com.exxeta.demo.middlewaretextvorsystem.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Data, Long> {

}

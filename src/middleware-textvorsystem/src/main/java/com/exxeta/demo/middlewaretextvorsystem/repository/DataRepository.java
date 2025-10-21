package com.exxeta.demo.middlewaretextvorsystem.repository;

import com.exxeta.demo.middlewaretextvorsystem.entity.MiddlewareData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<MiddlewareData, Long> {

}

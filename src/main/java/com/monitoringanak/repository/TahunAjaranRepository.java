package com.monitoringanak.repository;

import com.monitoringanak.model.TahunAjaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TahunAjaranRepository extends JpaRepository<TahunAjaran, Integer> {
    Optional<TahunAjaran> findByIsActiveTrue();
}

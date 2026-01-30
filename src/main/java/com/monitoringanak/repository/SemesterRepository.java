package com.monitoringanak.repository;

import com.monitoringanak.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    Optional<Semester> findByIsActiveTrue();

    List<Semester> findByTahunAjaran_IdTahun(Integer idTahun);
}

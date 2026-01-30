package com.monitoringanak.repository;

import com.monitoringanak.model.PendaftaranKelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PendaftaranKelasRepository extends JpaRepository<PendaftaranKelas, Integer> {
    List<PendaftaranKelas> findBySemester_IdSemester(Integer idSemester);

    List<PendaftaranKelas> findByAnak_IdAnak(Integer idAnak);

    Optional<PendaftaranKelas> findByAnak_IdAnakAndSemester_IdSemester(Integer idAnak, Integer idSemester);

    List<PendaftaranKelas> findByGuru_IdUserAndSemester_IdSemester(Integer idGuru, Integer idSemester);
}

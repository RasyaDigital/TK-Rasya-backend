package com.monitoringanak.service;

import com.monitoringanak.model.PendaftaranKelas;
import com.monitoringanak.repository.PendaftaranKelasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PendaftaranKelasService {
    private final PendaftaranKelasRepository pendaftaranKelasRepository;

    public PendaftaranKelas daftar(PendaftaranKelas pendaftaran) {
        return pendaftaranKelasRepository.save(pendaftaran);
    }

    public List<PendaftaranKelas> getBySemester(Integer idSemester) {
        return pendaftaranKelasRepository.findBySemester_IdSemester(idSemester);
    }

    public List<PendaftaranKelas> getByGuru(Integer idGuru, Integer idSemester) {
        return pendaftaranKelasRepository.findByGuru_IdUserAndSemester_IdSemester(idGuru, idSemester);
    }
}

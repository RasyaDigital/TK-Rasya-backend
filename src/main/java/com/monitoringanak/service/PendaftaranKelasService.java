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

    public PendaftaranKelas update(Integer id, PendaftaranKelas details) {
        PendaftaranKelas existing = pendaftaranKelasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pendaftaran not found"));

        if (details.getKelas() != null) {
            existing.setKelas(details.getKelas());
        }

        if (details.getGuru() != null) {
            existing.setGuru(details.getGuru());
        }

        if (details.getSemester() != null) {
            existing.setSemester(details.getSemester());
        }

        if (details.getAnak() != null) {
            existing.setAnak(details.getAnak());
        }

        return pendaftaranKelasRepository.save(existing);
    }

    public void delete(Integer id) {
        pendaftaranKelasRepository.deleteById(id);
    }
}

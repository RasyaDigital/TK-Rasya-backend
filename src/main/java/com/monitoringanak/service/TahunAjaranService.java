package com.monitoringanak.service;

import com.monitoringanak.model.TahunAjaran;
import com.monitoringanak.repository.TahunAjaranRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TahunAjaranService {
    private final TahunAjaranRepository tahunAjaranRepository;

    public List<TahunAjaran> getAll() {
        return tahunAjaranRepository.findAll();
    }

    public TahunAjaran create(TahunAjaran tahun) {
        if (tahun.getIsActive() != null && tahun.getIsActive()) {
            deactivateAll();
        }
        return tahunAjaranRepository.save(tahun);
    }

    public void deactivateAll() {
        tahunAjaranRepository.findAll().forEach(t -> {
            t.setIsActive(false);
            tahunAjaranRepository.save(t);
        });
    }

    public TahunAjaran getActive() {
        return tahunAjaranRepository.findByIsActiveTrue().orElse(null);
    }

    public TahunAjaran update(Integer id, TahunAjaran details) {
        TahunAjaran existing = tahunAjaranRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tahun Ajaran not found"));

        if (details.getTahunAjaran() != null) {
            existing.setTahunAjaran(details.getTahunAjaran());
        }

        if (details.getIsActive() != null) {
            if (details.getIsActive()) {
                deactivateAll();
            }
            existing.setIsActive(details.getIsActive());
        }

        return tahunAjaranRepository.save(existing);
    }

    public void delete(Integer id) {
        tahunAjaranRepository.deleteById(id);
    }
}

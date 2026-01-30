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
}

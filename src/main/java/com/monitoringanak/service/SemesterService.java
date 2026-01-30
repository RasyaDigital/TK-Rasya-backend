package com.monitoringanak.service;

import com.monitoringanak.model.Semester;
import com.monitoringanak.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    public Semester create(Semester semester) {
        if (semester.getIsActive() != null && semester.getIsActive()) {
            deactivateAll();
        }
        return semesterRepository.save(semester);
    }

    public void deactivateAll() {
        semesterRepository.findAll().forEach(s -> {
            s.setIsActive(false);
            semesterRepository.save(s);
        });
    }

    public Semester getActive() {
        return semesterRepository.findByIsActiveTrue().orElse(null);
    }

    public Semester update(Integer id, Semester details) {
        Semester existing = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        if (details.getSemesterKe() != null) {
            existing.setSemesterKe(details.getSemesterKe());
        }

        if (details.getIsActive() != null) {
            if (details.getIsActive()) {
                deactivateAll();
            }
            existing.setIsActive(details.getIsActive());
        }

        if (details.getTahunAjaran() != null) {
            existing.setTahunAjaran(details.getTahunAjaran());
        }

        return semesterRepository.save(existing);
    }

    public void delete(Integer id) {
        semesterRepository.deleteById(id);
    }
}

package com.monitoringanak.controller;

import com.monitoringanak.model.Semester;
import com.monitoringanak.service.SemesterService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/akademik/semester")
@AllArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester retrieved")
                .data(semesterService.getAll())
                .code(200).build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Semester semester) {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester created")
                .data(semesterService.create(semester))
                .code(200).build());
    }

    @GetMapping("/aktif")
    public ResponseEntity<?> getActive() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Active Semester retrieved")
                .data(semesterService.getActive())
                .code(200).build());
    }
}

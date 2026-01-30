package com.monitoringanak.controller;

import com.monitoringanak.model.TahunAjaran;
import com.monitoringanak.service.TahunAjaranService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/akademik/tahun-ajaran")
@AllArgsConstructor
public class TahunAjaranController {
    private final TahunAjaranService tahunAjaranService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran retrieved")
                .data(tahunAjaranService.getAll())
                .code(200).build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TahunAjaran tahun) {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran created")
                .data(tahunAjaranService.create(tahun))
                .code(200).build());
    }

    @GetMapping("/aktif")
    public ResponseEntity<?> getActive() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Active Tahun Ajaran retrieved")
                .data(tahunAjaranService.getActive())
                .code(200).build());
    }
}

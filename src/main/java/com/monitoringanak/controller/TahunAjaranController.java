package com.monitoringanak.controller;

import com.monitoringanak.model.TahunAjaran;
import com.monitoringanak.service.TahunAjaranService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitoringanak.security.RoleValidator;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/akademik/tahun-ajaran")
@AllArgsConstructor
public class TahunAjaranController {
    private final TahunAjaranService tahunAjaranService;
    private final RoleValidator roleValidator;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran retrieved")
                .data(tahunAjaranService.getAll())
                .code(200).build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TahunAjaran tahun, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran created")
                .data(tahunAjaranService.create(tahun))
                .code(200).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TahunAjaran tahun,
            HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran updated")
                .data(tahunAjaranService.update(id, tahun))
                .code(200).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        tahunAjaranService.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Tahun Ajaran deleted")
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

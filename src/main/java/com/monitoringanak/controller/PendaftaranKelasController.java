package com.monitoringanak.controller;

import com.monitoringanak.model.PendaftaranKelas;
import com.monitoringanak.service.PendaftaranKelasService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitoringanak.security.RoleValidator;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/akademik/pendaftaran")
@AllArgsConstructor
public class PendaftaranKelasController {
    private final PendaftaranKelasService pendaftaranKelasService;
    private final RoleValidator roleValidator;

    @PostMapping
    public ResponseEntity<?> daftar(@RequestBody PendaftaranKelas pendaftaran, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.hasFullAccess(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin or Guru only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Pendaftaran created")
                .data(pendaftaranKelasService.daftar(pendaftaran))
                .code(200).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PendaftaranKelas pendaftaran,
            HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.hasFullAccess(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin or Guru only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Pendaftaran updated")
                .data(pendaftaranKelasService.update(id, pendaftaran))
                .code(200).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.hasFullAccess(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin or Guru only").code(403).build());
        }
        pendaftaranKelasService.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Pendaftaran deleted")
                .code(200).build());
    }

    @GetMapping("/semester/{idSemester}")
    public ResponseEntity<?> getBySemester(@PathVariable Integer idSemester) {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Pendaftaran retrieved")
                .data(pendaftaranKelasService.getBySemester(idSemester))
                .code(200).build());
    }
}

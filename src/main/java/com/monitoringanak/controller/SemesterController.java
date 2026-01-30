package com.monitoringanak.controller;

import com.monitoringanak.model.Semester;
import com.monitoringanak.service.SemesterService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitoringanak.security.RoleValidator;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/akademik/semester")
@AllArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;
    private final RoleValidator roleValidator;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester retrieved")
                .data(semesterService.getAll())
                .code(200).build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Semester semester, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester created")
                .data(semesterService.create(semester))
                .code(200).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Semester semester,
            HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester updated")
                .data(semesterService.update(id, semester))
                .code(200).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpServletRequest request) {
        String userRole = (String) request.getAttribute("userRole");
        if (!roleValidator.isAdmin(userRole)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .success(false).message("Unauthorized: Admin only").code(403).build());
        }
        semesterService.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Semester deleted")
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

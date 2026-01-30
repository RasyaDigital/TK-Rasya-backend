package com.monitoringanak.controller;

import com.monitoringanak.model.PendaftaranKelas;
import com.monitoringanak.service.PendaftaranKelasService;
import com.monitoringanak.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/akademik/pendaftaran")
@AllArgsConstructor
public class PendaftaranKelasController {
    private final PendaftaranKelasService pendaftaranKelasService;

    @PostMapping
    public ResponseEntity<?> daftar(@RequestBody PendaftaranKelas pendaftaran) {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Pendaftaran created")
                .data(pendaftaranKelasService.daftar(pendaftaran))
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

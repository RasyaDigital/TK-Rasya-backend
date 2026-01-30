package com.monitoringanak.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendaftaranKelasDTO {
    private Integer idPendaftaran;
    private Integer idAnak;
    private String namaAnak;
    private Integer idSemester;
    private String tahunAjaran;
    private Integer semesterKe;
    private Integer idGuru;
    private String namaGuru;
    private String kelas;
}

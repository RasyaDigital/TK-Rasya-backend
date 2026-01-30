package com.monitoringanak.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pendaftaran_kelas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendaftaranKelas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPendaftaran;

    @ManyToOne
    @JoinColumn(name = "id_anak", nullable = false)
    private Anak anak;

    @ManyToOne
    @JoinColumn(name = "id_semester", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "id_guru")
    private User guru;

    @Column(length = 10)
    private String kelas;
}

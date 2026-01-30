package com.monitoringanak.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "anak")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnak;

    @Column(nullable = false, length = 100)
    private String namaAnak;

    @Column(name = "tgl_lahir")
    private LocalDate tglLahir;

    @Column(name = "jenis_kelamin", length = 10)
    private String jenisKelamin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wali")
    private User wali;

    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;
}

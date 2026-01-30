package com.monitoringanak.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tahun_ajaran")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TahunAjaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTahun;

    @Column(nullable = false, unique = true, length = 20)
    private String tahunAjaran;

    @Column(name = "is_active")
    private Boolean isActive;
}

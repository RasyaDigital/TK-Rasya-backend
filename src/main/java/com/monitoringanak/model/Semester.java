package com.monitoringanak.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSemester;

    @ManyToOne
    @JoinColumn(name = "id_tahun", nullable = false)
    private TahunAjaran tahunAjaran;

    @Column(name = "semester_ke", nullable = false)
    private Integer semesterKe;

    @Column(name = "is_active")
    private Boolean isActive;
}

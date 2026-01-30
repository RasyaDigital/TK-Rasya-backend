-- Database: monitoring_anak
-- Sistem Monitoring Perkembangan Anak (TK/PAUD) - EXPERT REFINED VERSION

-- Disable foreign key checks to allow a clean reset
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables in reverse order of dependencies
DROP TABLE IF EXISTS rapor;
DROP TABLE IF EXISTS penilaian_aspek;
DROP TABLE IF EXISTS penilaian_fisik;
DROP TABLE IF EXISTS pendaftaran_kelas;
DROP TABLE IF EXISTS anak;
DROP TABLE IF EXISTS semester;
DROP TABLE IF EXISTS tahun_ajaran;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
-- Drop old table names just in case
DROP TABLE IF EXISTS perkembangan_aspek;
DROP TABLE IF EXISTS perkembangan_fisik;
DROP TABLE IF EXISTS laporan;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================================
-- 1. ROLES
-- ============================================================================
CREATE TABLE roles (
    id_role INT AUTO_INCREMENT PRIMARY KEY,
    nama_role VARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO roles (nama_role) VALUES ('admin'), ('guru'), ('wali_murid');

-- 2. USERS
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    id_role INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_role) REFERENCES roles(id_role)
);

-- 3. TAHUN AJARAN & SEMESTER
CREATE TABLE tahun_ajaran (
    id_tahun INT AUTO_INCREMENT PRIMARY KEY,
    tahun_ajaran VARCHAR(20) NOT NULL UNIQUE, -- e.g. '2024/2025'
    is_active BOOLEAN DEFAULT false
);

CREATE TABLE semester (
    id_semester INT AUTO_INCREMENT PRIMARY KEY,
    id_tahun INT NOT NULL,
    semester_ke INT NOT NULL, -- 1 or 2
    is_active BOOLEAN DEFAULT false,
    FOREIGN KEY (id_tahun) REFERENCES tahun_ajaran(id_tahun),
    UNIQUE(id_tahun, semester_ke)
);

-- 4. ANAK
CREATE TABLE anak (
    id_anak INT AUTO_INCREMENT PRIMARY KEY,
    nama_anak VARCHAR(100) NOT NULL,
    tgl_lahir DATE,
    jenis_kelamin ENUM('L', 'P'),
    id_wali INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_wali) REFERENCES users(id_user) ON DELETE SET NULL
);

-- 5. PENDAFTARAN KELAS (Tracking history kelas per semester)
CREATE TABLE pendaftaran_kelas (
    id_pendaftaran INT AUTO_INCREMENT PRIMARY KEY,
    id_anak INT NOT NULL,
    id_semester INT NOT NULL,
    id_guru INT, -- Wali Kelas
    kelas VARCHAR(10), -- 'A' or 'B'
    FOREIGN KEY (id_anak) REFERENCES anak(id_anak) ON DELETE CASCADE,
    FOREIGN KEY (id_semester) REFERENCES semester(id_semester),
    FOREIGN KEY (id_guru) REFERENCES users(id_user) ON DELETE SET NULL,
    UNIQUE(id_anak, id_semester)
);

-- 6. PENILAIAN FISIK
CREATE TABLE penilaian_fisik (
    id_fisik INT AUTO_INCREMENT PRIMARY KEY,
    id_anak INT NOT NULL,
    id_semester INT NOT NULL,
    bulan INT NOT NULL CHECK (bulan BETWEEN 1 AND 6),
    tanggal DATE NOT NULL,
    tinggi_badan FLOAT,
    berat_badan FLOAT,
    lingkar_kepala FLOAT,
    catatan TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_anak) REFERENCES anak(id_anak) ON DELETE CASCADE,
    FOREIGN KEY (id_semester) REFERENCES semester(id_semester)
);

-- 7. PENILAIAN ASPEK
CREATE TABLE penilaian_aspek (
    id_aspek INT AUTO_INCREMENT PRIMARY KEY,
    id_anak INT NOT NULL,
    id_semester INT NOT NULL,
    bulan INT NOT NULL CHECK (bulan BETWEEN 1 AND 6),
    tanggal DATE NOT NULL,
    agama_moral INT,
    fisik_motorik INT,
    kognitif INT,
    bahasa INT,
    sosial_emosional INT,
    seni INT,
    catatan TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_anak) REFERENCES anak(id_anak) ON DELETE CASCADE,
    FOREIGN KEY (id_semester) REFERENCES semester(id_semester)
);

-- 8. RAPOR
CREATE TABLE rapor (
    id_rapor INT AUTO_INCREMENT PRIMARY KEY,
    id_anak INT NOT NULL,
    id_semester INT NOT NULL,
    file_pdf VARCHAR(255),
    dibuat_oleh INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_anak) REFERENCES anak(id_anak) ON DELETE CASCADE,
    FOREIGN KEY (id_semester) REFERENCES semester(id_semester),
    FOREIGN KEY (dibuat_oleh) REFERENCES users(id_user)
);

-- DATA DUMMY (Expert Set)
INSERT INTO users (id_user, username, password, nama, email, id_role) VALUES 
(1, 'admin', '$2a$10$5F8K7R9yLxJ3X.8zP.qNZeF/LQZ4.J5N8X9vHJK2Z3Y4wRJK5L6M7', 'Super Admin', 'admin@sekolah.com', 1),
(2, 'guru1', '$2a$10$N.5F8K7R9yLxJ3X.8zP.qNZeF/LQZ4.J5N8X9vHJK2Z3Y4wRJK5L', 'Guru Budi', 'budi@sekolah.com', 2),
(3, 'wali1', '$2a$10$8K7R9yLxJ3X.8zP.qNZeF/LQZ4.J5N8X9vHJK2Z3Y4wRJK5L6M7N', 'Orang Tua Azzam', 'wali1@email.com', 3);

INSERT INTO tahun_ajaran (id_tahun, tahun_ajaran, is_active) VALUES (1, '2024/2025', true);
INSERT INTO semester (id_semester, id_tahun, semester_ke, is_active) VALUES 
(1, 1, 1, true),
(2, 1, 2, false);

INSERT INTO anak (id_anak, nama_anak, tgl_lahir, jenis_kelamin, id_wali) VALUES 
(1, 'AHMAD AZZAM', '2019-01-15', 'L', 3);

INSERT INTO pendaftaran_kelas (id_anak, id_semester, id_guru, kelas) VALUES 
(1, 1, 2, 'A');

INSERT INTO penilaian_fisik (id_anak, id_semester, bulan, tanggal, tinggi_badan, berat_badan, lingkar_kepala) VALUES 
(1, 1, 1, '2024-07-20', 105.0, 18.0, 50.0);

INSERT INTO penilaian_aspek (id_anak, id_semester, bulan, tanggal, agama_moral, fisik_motorik, kognitif, bahasa, sosial_emosional, seni) VALUES 
(1, 1, 1, '2024-07-20', 85, 80, 85, 80, 85, 80);

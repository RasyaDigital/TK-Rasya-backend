-- Insert test data untuk verifikasi
-- These users will be created if the schema was executed successfully

-- Insert test admin user
INSERT INTO users (username, password, nama, email, id_role) 
VALUES ('admin', '$2a$10$2HjTxxk6UHZ/d62AO25ZMekz4Y6FfXoe2W7G4bjcQoqVuY3svprJa', 'Administrator', 'admin@monitoring.com', 1);

-- Insert test guru user  
INSERT INTO users (username, password, nama, email, id_role)
VALUES ('guru1', '$2a$10$.30WbGPq09MYDoD1aqtmU.od364a0AsBzbnK2/QHpcuVAoyf7xphS', 'Guru Satu', 'guru1@monitoring.com', 2);

-- Insert test wali user
INSERT INTO users (username, password, nama, email, id_role)
VALUES ('wali1', '$2a$10$LQrNFmdsy0G/NGcTqlJJGeTv0D6tyU16Oc1Vn/CqlvkeFb5AEhFTy', 'Wali Murid Satu', 'wali1@monitoring.com', 3);

-- Insert test child
INSERT INTO anak (nama_anak, tgl_lahir, jenis_kelamin, id_guru, id_wali)
VALUES ('Ahmad Rizki', '2021-01-15', 'Laki-laki', 2, 3);

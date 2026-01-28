-- Update password untuk semua user dengan BCrypt hash yang sudah diverifikasi (Generated locally)
-- admin123 -> $2a$10$2HjTxxk6UHZ/d62AO25ZMekz4Y6FfXoe2W7G4bjcQoqVuY3svprJa
-- guru123  -> $2a$10$.30WbGPq09MYDoD1aqtmU.od364a0AsBzbnK2/QHpcuVAoyf7xphS
-- wali123  -> $2a$10$LQrNFmdsy0G/NGcTqlJJGeTv0D6tyU16Oc1Vn/CqlvkeFb5AEhFTy

-- Update Admin users (password: admin123)
UPDATE users SET password = '$2a$10$2HjTxxk6UHZ/d62AO25ZMekz4Y6FfXoe2W7G4bjcQoqVuY3svprJa' WHERE id_user IN (1, 2);

-- Update Guru users (password: guru123)
UPDATE users SET password = '$2a$10$.30WbGPq09MYDoD1aqtmU.od364a0AsBzbnK2/QHpcuVAoyf7xphS' WHERE id_user = 3;

-- Update Wali users (password: wali123)
UPDATE users SET password = '$2a$10$LQrNFmdsy0G/NGcTqlJJGeTv0D6tyU16Oc1Vn/CqlvkeFb5AEhFTy' WHERE id_user >= 4;

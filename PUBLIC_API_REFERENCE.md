# PUBLIC API REFERENCE - Monitoring Anak Backend

Dokumentasi teknis untuk integrasi dengan API Monitoring Anak (TK/PAUD) di Railway.

## 🚀 Base URL
**Production:** `https://web-production-f129b.up.railway.app/api`

---

## 🔐 Authentication
API menggunakan **JWT**. Login untuk mendapatkan token, lalu sertakan pada setiap request.
- **Header:** `Authorization: Bearer <token>`

---

## 📂 Modul API

### 1. Auth (`/auth`)
- `POST /auth/login` : Login user.
- `POST /auth/register` : Registrasi (Admin only).
- `GET /health` : Cek status server.

### 2. Data Anak (`/anak`)
- `GET /anak` : List anak (Filter otomatis: Wali hanya lihat anaknya, Admin/Guru lihat semua).
- `GET /anak/{id}` : Detail anak.
- `POST /anak` : Tambah anak (Admin/Guru).
  **Payload (Nested Object for Wali):**
  ```json
  {
    "namaAnak": "Azzam",
    "tglLahir": "2019-01-15",
    "jenisKelamin": "L",
    "wali": {
        "idUser": 4
    }
  }
  ```
- `PUT /anak/{id}` : Update data anak.
- `DELETE /anak/{id}` : Hapus data anak.

### 3. Akademik & Pendaftaran (`/akademik`)
- `GET /akademik/tahun-ajaran` : List semua tahun ajaran.
- `GET /akademik/semester/aktif` : Mendapatkan semester yang sedang berjalan.
- `POST /akademik/pendaftaran` : Daftarkan anak ke kelas & guru untuk semester ini.
  ```json
  {
    "anak": {"idAnak": 1},
    "semester": {"idSemester": 1},
    "guru": {"idUser": 2},
    "kelas": "A"
  }
  ```

### 4. Penilaian Bulanan (Fisik & Aspek)
Endpoint menerima `idSemester` dan `bulan` (1-6).
**Catatan:** Menggunakan DTO, jadi ID dikirim langsung (flat).

**Fisik (`/perkembangan/fisik`):**
- `GET /perkembangan/fisik/{idAnak}` : List history fisik anak.
- `GET /perkembangan/fisik/detail/{id}` : Detail satu record.
- `POST /perkembangan/fisik` : Input nilai.
  ```json
  {
    "idAnak": 1,
    "idSemester": 1,
    "bulan": 1,
    "tanggal": "2024-07-20",
    "tinggiBadan": 105.0,
    "beratBadan": 18.0,
    "lingkarKepala": 50.0,
    "catatan": "Sehat"
  }
  ```

**Aspek (`/perkembangan/aspek`):**
- `GET /perkembangan/aspek/{idAnak}` : List history aspek anak.
- `GET /perkembangan/aspek/{idAnak}/average` : Rata-rata nilai.
- `POST /perkembangan/aspek` : Input nilai.
  ```json
  {
    "idAnak": 1,
    "idSemester": 1,
    "bulan": 1,
    "tanggal": "2024-07-20",
    "agamaMoral": 80,
    "fisikMotorik": 85,
    "kognitif": 80,
    "bahasa": 85,
    "sosialEmosional": 80,
    "seni": 85
  }
  ```

### 5. Laporan & Rapot (`/laporan`)
- `GET /laporan` : List laporan (Filter role).
- `GET /laporan/anak/{idAnak}` : List laporan spesifik anak.
- `GET /laporan/{id}/download` : Download file PDF.
- `POST /laporan` : Buat entri rapot (Admin/Guru).
  **Payload (Nested Objects):**
  ```json
  {
    "anak": { "idAnak": 1 },
    "semester": { "idSemester": 1 },
    "periode": "Semester 1 (Ganjil)",
    "filePdf": "laporan_azzam_s1.pdf",
    "dibuatOleh": { "idUser": 3 }
  }
  ```

### 6. Users (`/users`)
- `GET /users/role/{idRole}` : List user by role (1: Admin, 2: Guru, 3: Wali).
- `GET /users/{id}` : Detail user.

---

## 📊 Standard Response
```json
{
  "success": true,
  "message": "Operasi Berhasil",
  "code": 200,
  "data": { ... }
}
```

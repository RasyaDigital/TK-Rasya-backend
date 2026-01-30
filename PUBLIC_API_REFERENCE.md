# PUBLIC API REFERENCE - Monitoring Anak Backend

Dokumentasi teknis untuk integrasi dengan API Monitoring Anak (TK/PAUD) di Railway.

## 🚀 Base URL
**Production:** `https://web-production-f129b.up.railway.app/api`

---

## 🔐 Authentication
API menggunakan **JWT**. Login untuk mendapatkan token, lalu sertakan pada setiap request.
- **Header:** `Authorization: Bearer <token>`

---

## 🛠 Struktur Akademik (Expert Version)
- **Tahun Ajaran:** Objek master (id, tahunAjaran, isActive).
- **Semester:** Objek master terhubung ke Tahun Ajaran (id, semesterKe, isActive).
- **Pendaftaran:** Menghubungkan Anak ke Kelas dan Guru per Semester.
- **Bulan Penilaian:** `1` s/d `6` (Urutan bulan dalam semester berjalan).

---

## 📂 Modul API

### 1. Auth (`/auth`)
- `POST /auth/login` : Login user.
- `POST /auth/register` : Registrasi (Admin only).

### 2. Data Anak (`/anak`)
- `GET /anak` : List anak (Filter otomatis per Wali).
- `POST /anak` : Tambah anak (Data Master).
  ```json
  {
    "namaAnak": "Azzam",
    "tglLahir": "2019-01-15",
    "jenisKelamin": "L",
    "idWali": 4
  }
  ```

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

**Fisik (`/perkembangan/fisik`):**
```json
{
  "idAnak": 1,
  "idSemester": 1,
  "bulan": 1,
  "tanggal": "2024-07-20",
  "tinggiBadan": 105.0,
  "beratBadan": 18.0
}
```

**Aspek (`/perkembangan/aspek`):**
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
- `POST /laporan` : Buat entri rapot.
  ```json
  {
    "idAnak": 1,
    "idSemester": 1,
    "periode": "Semester 1 (Ganjil)",
    "filePdf": "laporan_azzam_s1.pdf",
    "idGuru": 3
  }
  ```

### 6. Users (`/users`)
- `GET /users/role/{idRole}` : (1: Admin, 2: Guru, 3: Wali).
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

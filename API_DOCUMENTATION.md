# API Documentation - Monitoring Anak Backend

Dokumentasi ini berisi daftar endpoint API yang dapat digunakan oleh Frontend Mobile.

**Base URL (Production):** `https://web-production-f129b.up.railway.app/api`
**Base URL (Local):** `http://localhost:9090/api`

## Authentication

Setiap request ke endpoint yang diproteksi (selain `/auth/**`) **WAJIB** menyertakan Header:
`Authorization: Bearer <token_jwt_disini>`

### 1. Login
- **URL:** `/auth/login`
- **Method:** `POST`
- **Access:** Public
- **Body:**
  ```json
  {
    "username": "admin",
    "password": "password"
  }
  ```
- **Response Success (200 OK):**
  ```json
  {
    "success": true,
    "message": "Login successful",
    "data": {
        "idUser": 1,
        "username": "admin",
        "nama": "Administrator",
        "role": "ROLE_ADMIN", // atau ROLE_GURU, ROLE_WALI
        "token": "eyJhbGciOiJIUzI1NiJ9..."
    },
    "code": 200
  }
  ```

### 2. Register (Admin Only)
- **URL:** `/auth/register`
- **Method:** `POST`
- **Access:** Public (seharusnya dibatasi, tapi saat ini terbuka)
- **Body:**
  ```json
  {
    "username": "userbaru",
    "password": "password123",
    "nama": "Nama Lengkap",
    "email": "email@example.com",
    "idRole": 2 // 1=Admin, 2=Guru, 3=Wali
  }
  ```

---

## Data Anak (`/anak`)

### 1. Get List Anak
- **URL:** `/anak`
- **Method:** `GET`
- **Access:** 
  - **Admin/Guru:** Mendapatkan **SEMUA** data anak.
  - **Wali:** Mendapatkan data anak **MEREKA SENDIRI** saja.
- **Response:** List of Anak objects.

### 2. Get Detail Anak
- **URL:** `/anak/{id}`
- **Method:** `GET`
- **Access:** Sesuai hak akses role (Wali hanya bisa lihat anaknya sendiri).

### 3. Tambah Anak (Admin/Guru Only)
- **URL:** `/anak`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "namaAnak": "Budi Santoso",
    "tglLahir": "2020-05-20",
    "jenisKelamin": "L", // L = Laki-laki, P = Perempuan
    "guru": { "idUser": 3 }, // ID User Guru
    "wali": { "idUser": 4 }  // ID User Wali
  }
  ```

### 4. Update Anak (Admin/Guru Only)
- **URL:** `/anak/{id}`
- **Method:** `PUT`
- **Body:** (Sama seperti Tambah Anak)

### 5. Hapus Anak (Admin/Guru Only)
- **URL:** `/anak/{id}`
- **Method:** `DELETE`

---

## Laporan Perkembangan (`/laporan`)

### 1. Get Semua Laporan
- **URL:** `/laporan`
- **Method:** `GET`
- **Access:** Admin/Guru (Semua), Wali (Hanya laporan anaknya).

### 2. Get Laporan by Anak
- **URL:** `/laporan/anak/{idAnak}`
- **Method:** `GET`
- **Access:** Admin/Guru/Wali (Sesuai hak akses).

### 3. Get Detail Laporan
- **URL:** `/laporan/{id}`
- **Method:** `GET`

### 4. Download File Laporan (PDF)
- **URL:** `/laporan/{id}/download`
- **Method:** `GET`
- **Response:** Binary file (PDF). Browser/Mobile akan medownload file ini.

### 5. Buat Laporan (Admin/Guru Only)
- **URL:** `/laporan`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "anak": { "idAnak": 1 },
    "dibuatOleh": { "idUser": 3 }, // ID Guru pembuat
    "periode": "Januari 2024",
    "filePdf": "laporan_budi_jan24.pdf" // Nama file (upload file terpisah/manual)
  }
  ```

---

## Perkembangan Fisik (`/perkembangan/fisik`)

### 1. Get by Anak
- **URL:** `/perkembangan/fisik/{idAnak}`
- **Method:** `GET`
- **Access:** Admin/Guru/Wali.

### 2. Get by Range Tanggal
- **URL:** `/perkembangan/fisik/{idAnak}/range?startDate=2024-01-01&endDate=2024-12-31`
- **Method:** `GET`

### 3. Input Data Fisik (Admin/Guru Only)
- **URL:** `/perkembangan/fisik`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "anak": { "idAnak": 1 },
    "tanggal": "2024-01-28",
    "tinggiBadan": 110.5,
    "beratBadan": 18.0,
    "lingkarKepala": 50.0,
    "usiaBulan": 48
  }
  ```

---

## Perkembangan Aspek (`/perkembangan/aspek`)
Nilai aspek berupa angka (misal skala 1-4).

### 1. Get by Anak
- **URL:** `/perkembangan/aspek/{idAnak}`
- **Method:** `GET`
- **Response:** List data perkembangan aspek.

### 2. Get Rata-rata Nilai (Average)
- **URL:** `/perkembangan/aspek/{idAnak}/average`
- **Method:** `GET`
- **Response:** `double` (Contoh: `3.5`)

### 3. Input Data Aspek (Admin/Guru Only)
- **URL:** `/perkembangan/aspek`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "anak": { "idAnak": 1 },
    "tanggal": "2024-01-28",
    "agamaMoral": 4,
    "fisikMotorik": 3,
    "kognitif": 4,
    "bahasa": 3,
    "sosialEmosional": 4,
    "seni": 3,
    "catatan": "Anak sangat aktif dan ceria hari ini."
  }
  ```

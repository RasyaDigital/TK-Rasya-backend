# Database Setup Summary

## ✅ Completed Tasks

### 1. Database Schema Created
- **Database**: `railway` (Railway MySQL)
- **Tables Created**: 6 tables
  - `roles` - User roles (admin, guru, wali_murid)
  - `users` - User accounts with authentication
  - `anak` - Child/Student data
  - `perkembangan_fisik` - Physical development tracking
  - `perkembangan_aspek` - Aspect development (6 aspects: agama_moral, fisik_motorik, kognitif, bahasa, sosial_emosional, seni)
  - `laporan` - Reports

### 2. Database Connection Details
- **Host**: `interchange.proxy.rlwy.net`
- **Port**: `3306` (internal Railway port)
- **Username**: `root`
- **Password**: `uISjJluiFwoDoZmuSAUdZEJvkhBNKFhC`
- **Database**: `railway`

### 3. Application Configuration Updated
- **File**: `src/main/resources/application.properties`
- **Connection URL**: `jdbc:mysql://interchange.proxy.rlwy.net:3306/railway?useSSL=false&serverTimezone=UTC`
- **Environment Variables Used**: `DATABASE_URL`, `DATABASE_USER`, `DATABASE_PASSWORD`

### 4. Test Data Inserted
- Admin user created (username: `admin`)
- Guru user created (username: `guru1`)
- Wali user created (username: `wali1`)
- Test child/student created (nama: `Ahmad Rizki`)

## 🔐 Default Test Credentials

### Admin Account
- **Username**: admin
- **Password**: admin123
- **Hashed**: $2a$10$2HjTxxk6UHZ/d62AO25ZMekz4Y6FfXoe2W7G4bjcQoqVuY3svprJa

### Guru Account
- **Username**: guru1
- **Password**: guru123
- **Hashed**: $2a$10$.30WbGPq09MYDoD1aqtmU.od364a0AsBzbnK2/QHpcuVAoyf7xphS

### Wali Account
- **Username**: wali1
- **Password**: wali123
- **Hashed**: $2a$10$LQrNFmdsy0G/NGcTqlJJGeTv0D6tyU16Oc1Vn/CqlvkeFb5AEhFTy

## 📝 Environment Configuration Profiles

### Development (application-dev.properties)
- **Database**: Local MySQL (localhost:3306)
- **Hibernate DDL**: update
- **SQL Logging**: enabled
- **Log Level**: DEBUG

### Production (application-prod.properties)
- **Database**: Railway MySQL (via environment variables)
- **Hibernate DDL**: update
- **Environment Variables**:
  - `DATABASE_URL`: Connection string
  - `MYSQLUSER`: root
  - `MYSQLPASSWORD`: Password
  - `PORT`: Application port

## ✅ Verification Checklist

- [x] Schema created in Railway MySQL
- [x] All 6 tables created successfully
- [x] Default roles inserted (admin, guru, wali_murid)
- [x] Test users created
- [x] Test child data created
- [x] Application properties updated
- [x] Connection credentials verified

## 🚀 Next Steps

1. **Start the Spring Boot application**:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
   ```

2. **Test API endpoints**:
   - Login endpoint: `POST /api/auth/login`
   - Get users: `GET /api/users`
   - Get children: `GET /api/anak`

3. **Monitor database**:
   - Railway Dashboard: Database > Data tab
   - Check table data in real-time

## 📞 Database Connection String Reference

**JDBC URL** (for application.properties):
```
jdbc:mysql://interchange.proxy.rlwy.net:3306/railway?useSSL=false&serverTimezone=UTC
```

**MySQL CLI Connection**:
```bash
mysql -h interchange.proxy.rlwy.net -u root -P 3306 -p"uISjJluiFwoDoZmuSAUdZEJvkhBNKFhC" railway
```

**Railway URL Format**:
```
mysql://root:uISjJluiFwoDoZmuSAUdZEJvkhBNKFhC@interchange.proxy.rlwy.net:3306/railway
```

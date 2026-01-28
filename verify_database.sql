-- Verification SQL - Check all data created

-- 1. Check if tables exist
SHOW TABLES;

-- 2. Check roles
SELECT 'ROLES' as 'Check', COUNT(*) as Count FROM roles;
SELECT * FROM roles;

-- 3. Check users
SELECT 'USERS' as 'Check', COUNT(*) as Count FROM users;
SELECT id_user, username, nama, email, created_at FROM users;

-- 4. Check children
SELECT 'ANAK' as 'Check', COUNT(*) as Count FROM anak;
SELECT * FROM anak;

-- 5. Summary statistics
SELECT 
  (SELECT COUNT(*) FROM roles) as total_roles,
  (SELECT COUNT(*) FROM users) as total_users,
  (SELECT COUNT(*) FROM anak) as total_children;

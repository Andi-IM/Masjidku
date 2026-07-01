-- Data awal untuk user dan profil (Admin & Ketua)
-- File ini akan dijalankan otomatis oleh Hibernate jika hbm2ddl.auto diset ke 'create' atau 'create-drop'

INSERT INTO user (userid, password, username, jabatan, status, created_at, updated_at) VALUES ('paijo', '3c0becdf230ba5a952c9a499a2cf8aade19b56b9309dad1c0dc4cfc5a48a0824', NULL, 'ketua', 'Aktif', NULL, NULL);
INSERT INTO user (userid, password, username, jabatan, status, created_at, updated_at) VALUES ('root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Admin', 'admin', 'Aktif', NULL, NULL);

INSERT INTO profil_user (userid, notelp, alamat) VALUES ('paijo', '12345678', 'jl. Kisanak');

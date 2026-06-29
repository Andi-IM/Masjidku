-- seed.sql
DELETE FROM infak_anakyatim;
DELETE FROM infak_operasional;
DELETE FROM infak_pembangunan;
DELETE FROM infak_tpa;
DELETE FROM kegiatan;
DELETE FROM operasional_keluar;
DELETE FROM pembangunan_keluar;
DELETE FROM pemberi_zakat;
DELETE FROM penerima_anakyatim;
DELETE FROM penerima_zakat;
DELETE FROM tahunanggaran;
DELETE FROM tamu;
DELETE FROM tamukegiatan;
DELETE FROM tpa_keluar;

INSERT INTO infak_anakyatim (id, donatur, jumlah, tanggal, operator) VALUES 
(1, 'Hamba Allah', 500000, '2026-06-01', 'admin'),
(2, 'Budi Santoso', 1000000, '2026-06-15', 'admin');

INSERT INTO infak_operasional (id, donatur, jumlah, tanggal, operator) VALUES 
(1, 'Hamba Allah', 250000, '2026-06-02', 'admin'),
(2, 'Agus Supriyanto', 150000, '2026-06-10', 'admin');

INSERT INTO infak_pembangunan (id, donatur, jumlah, tanggal, operator) VALUES 
(1, 'Keluarga Bapak H. Ahmad', 5000000, '2026-06-05', 'admin');

INSERT INTO infak_tpa (id, donatur, jumlah, tanggal, operator) VALUES 
(1, 'Siti Aminah', 300000, '2026-06-20', 'admin');

INSERT INTO kegiatan (kegiatanID, kegiatanNama, kegiatanWaktu, kegiatanTanggal, kegiatanTempat, operator) VALUES 
('K0001', 'Pengajian Akbar', '08:00:00', '2026-07-10', 'Masjid Utama', 'admin'),
('K0002', 'Rapat Takmir', '20:00:00', '2026-06-30', 'Ruang Rapat', 'admin');

INSERT INTO operasional_keluar (id, nama, jumlah, tanggal, operator) VALUES 
(1, 'Listrik dan Air', 750000, '2026-06-25', 'admin'),
(2, 'Kebersihan', 300000, '2026-06-26', 'admin');

INSERT INTO pembangunan_keluar (id, nama, jumlah, tanggal, operator) VALUES 
(1, 'Beli Semen', 1500000, '2026-06-10', 'admin');

INSERT INTO pemberi_zakat (id, nama, jumlah, tahun, operator) VALUES 
(1, 'Bapak H. Soleh', 350000, 2026, 'admin'),
(2, 'Keluarga Ibu Iin', 500000, 2026, 'admin');

INSERT INTO penerima_anakyatim (id, nama, usia, jumlah, tanggal, operator) VALUES 
(1, 'Yusuf', 10, 250000, '2026-06-28', 'admin'),
(2, 'Fatimah', 12, 250000, '2026-06-28', 'admin');

INSERT INTO penerima_zakat (id, nama, jumlah, tahun, operator) VALUES 
(1, 'Bapak Jono', 500000, 2026, 'admin'),
(2, 'Ibu Sumirah', 500000, 2026, 'admin');

INSERT INTO tahunanggaran (tahun, status) VALUES 
(2025, 'Selesai'),
(2026, 'Aktif');

INSERT INTO tamu (tamuID, tamuNama, tamuAlamat, tamuNotelp, operator) VALUES 
('T0001', 'Ustadz Abdul Somad', 'Pekanbaru', '081234567890', 'admin'),
('T0002', 'Bupati Setempat', 'Kantor Bupati', '081987654321', 'admin');

INSERT INTO tamukegiatan (id_undangan, id_kegiatan, id_tamu, keterangan) VALUES 
('U0001', 'K0001', 'T0001', 'Penceramah Utama'),
('U0002', 'K0002', 'T0002', 'Tamu Kehormatan');

INSERT INTO tpa_keluar (id, nama, jumlah, tanggal, operator) VALUES 
(1, 'Buku Iqro', 250000, '2026-06-05', 'admin'),
(2, 'Gaji Pengajar', 1500000, '2026-06-25', 'admin');

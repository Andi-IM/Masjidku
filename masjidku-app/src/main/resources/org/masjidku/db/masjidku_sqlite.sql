/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------









-- Dumping database structure for masjidku



-- Dumping structure for table masjidku.infakanakyatim
CREATE TABLE IF NOT EXISTS `infakanakyatim` (
  `id` INTEGER DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.infakanakyatim: ~0 rows (approximately)



-- Dumping structure for table masjidku.infakoperasional
CREATE TABLE IF NOT EXISTS `infakoperasional` (
  `id` INTEGER DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.infakoperasional: ~0 rows (approximately)



-- Dumping structure for table masjidku.infakpembangunan
CREATE TABLE IF NOT EXISTS `infakpembangunan` (
  `id` INTEGER DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.infakpembangunan: ~0 rows (approximately)



-- Dumping structure for table masjidku.infaktpa
CREATE TABLE IF NOT EXISTS `infaktpa` (
  `id` INTEGER DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.infaktpa: ~0 rows (approximately)



-- Dumping structure for table masjidku.kegiatan
CREATE TABLE IF NOT EXISTS `kegiatan` (
  `kegiatanID` varchar(5) NOT NULL,
  `kegiatanNama` varchar(50) DEFAULT NULL,
  `kegiatanWaktu` time DEFAULT NULL,
  `kegiatanTanggal` date DEFAULT NULL,
  `kegiatanTempat` varchar(50) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kegiatanID`)
) ;

-- Dumping data for table masjidku.kegiatan: ~0 rows (approximately)



-- Dumping structure for table masjidku.operasionalkeluar
CREATE TABLE IF NOT EXISTS `operasionalkeluar` (
  `id` INTEGER DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.operasionalkeluar: ~0 rows (approximately)



-- Dumping structure for table masjidku.pembangunankeluar
CREATE TABLE IF NOT EXISTS `pembangunankeluar` (
  `id` INTEGER DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.pembangunankeluar: ~0 rows (approximately)



-- Dumping structure for table masjidku.pemberi_zakat
CREATE TABLE IF NOT EXISTS `pemberi_zakat` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` INTEGER DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL
) ;

-- Dumping data for table masjidku.pemberi_zakat: ~0 rows (approximately)



-- Dumping structure for table masjidku.penerimaanakyatim
CREATE TABLE IF NOT EXISTS `penerimaanakyatim` (
  `id` INTEGER DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `usia` INTEGER DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.penerimaanakyatim: ~0 rows (approximately)



-- Dumping structure for table masjidku.penerima_zakat
CREATE TABLE IF NOT EXISTS `penerima_zakat` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` INTEGER DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.penerima_zakat: ~0 rows (approximately)



-- Dumping structure for table masjidku.profil_user
CREATE TABLE IF NOT EXISTS `profil_user` (
  `userid` varchar(15) NOT NULL,
  `notelp` varchar(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  UNIQUE (`userid`),
  CONSTRAINT `foreign_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- Dumping data for table masjidku.profil_user: ~0 rows (approximately)

INSERT OR IGNORE INTO `profil_user` (`userid`, `notelp`, `alamat`) VALUES
	('paijo', '12345678', 'jl. Kisanak');


-- Dumping structure for table masjidku.tahunanggaran
CREATE TABLE IF NOT EXISTS `tahunanggaran` (
  `tahun` INTEGER DEFAULT NULL,
  `status` VARCHAR(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.tahunanggaran: ~0 rows (approximately)



-- Dumping structure for table masjidku.tamu
CREATE TABLE IF NOT EXISTS `tamu` (
  `tamuID` varchar(5) NOT NULL,
  `tamuNama` varchar(50) DEFAULT NULL,
  `tamuAlamat` varchar(255) DEFAULT NULL,
  `tamuNotelp` varchar(20) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tamuID`)
) ;

-- Dumping data for table masjidku.tamu: ~0 rows (approximately)



-- Dumping structure for table masjidku.tamukegiatan
CREATE TABLE IF NOT EXISTS `tamukegiatan` (
  `id_undangan` varchar(5) NOT NULL,
  `id_kegiatan` varchar(5) DEFAULT NULL,
  `id_tamu` varchar(5) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_undangan`),
  UNIQUE (`id_kegiatan`),
  UNIQUE (`id_tamu`),
  CONSTRAINT `tamukegiatan_kegiatan_kegiatanID_fk` FOREIGN KEY (`id_kegiatan`) REFERENCES `kegiatan` (`kegiatanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tamukegiatan_tamu_tamuID_fk` FOREIGN KEY (`id_tamu`) REFERENCES `tamu` (`tamuID`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- Dumping data for table masjidku.tamukegiatan: ~0 rows (approximately)



-- Dumping structure for table masjidku.tpakeluar
CREATE TABLE IF NOT EXISTS `tpakeluar` (
  `id` INTEGER DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ;

-- Dumping data for table masjidku.tpakeluar: ~0 rows (approximately)



-- Dumping structure for table masjidku.user
CREATE TABLE IF NOT EXISTS `user` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `jabatan` VARCHAR(50) DEFAULT NULL,
  `status` VARCHAR(50) DEFAULT 'Nonaktif',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ;

-- Dumping data for table masjidku.user: ~2 rows (approximately)

INSERT OR IGNORE INTO `user` (`userid`, `password`, `username`, `jabatan`, `status`, `created_at`, `updated_at`) VALUES
	('paijo', '3c0becdf230ba5a952c9a499a2cf8aade19b56b9309dad1c0dc4cfc5a48a0824', NULL, 'ketua', 'Aktif', NULL, NULL),
	('root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Admin', 'admin', 'Aktif', NULL, NULL);







CREATE TABLE IF NOT EXISTS `sessions` (
  `session_id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `timestamp` varchar(50) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  CONSTRAINT `fk_sessions_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
);

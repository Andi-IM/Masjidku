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

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for masjidku
CREATE DATABASE IF NOT EXISTS `masjidku` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `masjidku`;

-- Dumping structure for table masjidku.infakanakyatim
CREATE TABLE IF NOT EXISTS `infakanakyatim` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.infakanakyatim: ~0 rows (approximately)
/*!40000 ALTER TABLE `infakanakyatim` DISABLE KEYS */;
/*!40000 ALTER TABLE `infakanakyatim` ENABLE KEYS */;

-- Dumping structure for table masjidku.infakoperasional
CREATE TABLE IF NOT EXISTS `infakoperasional` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.infakoperasional: ~0 rows (approximately)
/*!40000 ALTER TABLE `infakoperasional` DISABLE KEYS */;
/*!40000 ALTER TABLE `infakoperasional` ENABLE KEYS */;

-- Dumping structure for table masjidku.infakpembangunan
CREATE TABLE IF NOT EXISTS `infakpembangunan` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.infakpembangunan: ~0 rows (approximately)
/*!40000 ALTER TABLE `infakpembangunan` DISABLE KEYS */;
/*!40000 ALTER TABLE `infakpembangunan` ENABLE KEYS */;

-- Dumping structure for table masjidku.infaktpa
CREATE TABLE IF NOT EXISTS `infaktpa` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.infaktpa: ~0 rows (approximately)
/*!40000 ALTER TABLE `infaktpa` DISABLE KEYS */;
/*!40000 ALTER TABLE `infaktpa` ENABLE KEYS */;

-- Dumping structure for table masjidku.kegiatan
CREATE TABLE IF NOT EXISTS `kegiatan` (
  `kegiatanID` varchar(5) NOT NULL,
  `kegiatanNama` varchar(50) DEFAULT NULL,
  `kegiatanWaktu` time DEFAULT NULL,
  `kegiatanTanggal` date DEFAULT NULL,
  `kegiatanTempat` varchar(50) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kegiatanID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.kegiatan: ~0 rows (approximately)
/*!40000 ALTER TABLE `kegiatan` DISABLE KEYS */;
/*!40000 ALTER TABLE `kegiatan` ENABLE KEYS */;

-- Dumping structure for table masjidku.operasionalkeluar
CREATE TABLE IF NOT EXISTS `operasionalkeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.operasionalkeluar: ~0 rows (approximately)
/*!40000 ALTER TABLE `operasionalkeluar` DISABLE KEYS */;
/*!40000 ALTER TABLE `operasionalkeluar` ENABLE KEYS */;

-- Dumping structure for table masjidku.pembangunankeluar
CREATE TABLE IF NOT EXISTS `pembangunankeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.pembangunankeluar: ~0 rows (approximately)
/*!40000 ALTER TABLE `pembangunankeluar` DISABLE KEYS */;
/*!40000 ALTER TABLE `pembangunankeluar` ENABLE KEYS */;

-- Dumping structure for table masjidku.pemberi_zakat
CREATE TABLE IF NOT EXISTS `pemberi_zakat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` year(4) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.pemberi_zakat: ~0 rows (approximately)
/*!40000 ALTER TABLE `pemberi_zakat` DISABLE KEYS */;
/*!40000 ALTER TABLE `pemberi_zakat` ENABLE KEYS */;

-- Dumping structure for table masjidku.penerimaanakyatim
CREATE TABLE IF NOT EXISTS `penerimaanakyatim` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `usia` int(11) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.penerimaanakyatim: ~0 rows (approximately)
/*!40000 ALTER TABLE `penerimaanakyatim` DISABLE KEYS */;
/*!40000 ALTER TABLE `penerimaanakyatim` ENABLE KEYS */;

-- Dumping structure for table masjidku.penerima_zakat
CREATE TABLE IF NOT EXISTS `penerima_zakat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` year(4) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.penerima_zakat: ~0 rows (approximately)
/*!40000 ALTER TABLE `penerima_zakat` DISABLE KEYS */;
/*!40000 ALTER TABLE `penerima_zakat` ENABLE KEYS */;

-- Dumping structure for table masjidku.profil_user
CREATE TABLE IF NOT EXISTS `profil_user` (
  `userid` varchar(15) NOT NULL,
  `notelp` varchar(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  UNIQUE KEY `index_userid` (`userid`),
  CONSTRAINT `foreign_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.profil_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `profil_user` DISABLE KEYS */;
INSERT INTO `profil_user` (`userid`, `notelp`, `alamat`) VALUES
	('paijo', '12345678', 'jl. Kisanak');
/*!40000 ALTER TABLE `profil_user` ENABLE KEYS */;

-- Dumping structure for table masjidku.tahunanggaran
CREATE TABLE IF NOT EXISTS `tahunanggaran` (
  `tahun` year(4) DEFAULT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.tahunanggaran: ~0 rows (approximately)
/*!40000 ALTER TABLE `tahunanggaran` DISABLE KEYS */;
/*!40000 ALTER TABLE `tahunanggaran` ENABLE KEYS */;

-- Dumping structure for table masjidku.tamu
CREATE TABLE IF NOT EXISTS `tamu` (
  `tamuID` varchar(5) NOT NULL,
  `tamuNama` varchar(50) DEFAULT NULL,
  `tamuAlamat` varchar(255) DEFAULT NULL,
  `tamuNotelp` varchar(20) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tamuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.tamu: ~0 rows (approximately)
/*!40000 ALTER TABLE `tamu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamu` ENABLE KEYS */;

-- Dumping structure for table masjidku.tamukegiatan
CREATE TABLE IF NOT EXISTS `tamukegiatan` (
  `id_undangan` varchar(5) NOT NULL,
  `id_kegiatan` varchar(5) DEFAULT NULL,
  `id_tamu` varchar(5) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_undangan`),
  UNIQUE KEY `tamukegiatan_id_kegiatan_uindex` (`id_kegiatan`),
  UNIQUE KEY `tamukegiatan_id_tamu_uindex` (`id_tamu`),
  CONSTRAINT `tamukegiatan_kegiatan_kegiatanID_fk` FOREIGN KEY (`id_kegiatan`) REFERENCES `kegiatan` (`kegiatanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tamukegiatan_tamu_tamuID_fk` FOREIGN KEY (`id_tamu`) REFERENCES `tamu` (`tamuID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.tamukegiatan: ~0 rows (approximately)
/*!40000 ALTER TABLE `tamukegiatan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamukegiatan` ENABLE KEYS */;

-- Dumping structure for table masjidku.tpakeluar
CREATE TABLE IF NOT EXISTS `tpakeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.tpakeluar: ~0 rows (approximately)
/*!40000 ALTER TABLE `tpakeluar` DISABLE KEYS */;
/*!40000 ALTER TABLE `tpakeluar` ENABLE KEYS */;

-- Dumping structure for table masjidku.user
CREATE TABLE IF NOT EXISTS `user` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `jabatan` enum('admin','ketua','sekretaris','bendahara') DEFAULT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT 'Nonaktif',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table masjidku.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userid`, `password`, `username`, `jabatan`, `status`, `created_at`, `updated_at`) VALUES
	('paijo', '3c0becdf230ba5a952c9a499a2cf8aade19b56b9309dad1c0dc4cfc5a48a0824', NULL, 'ketua', 'Aktif', NULL, NULL),
	('root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Admin', 'admin', 'Aktif', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

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

-- Dumping structure for function masjidku.getInfakYatimBalance
DELIMITER //
CREATE FUNCTION `getInfakYatimBalance`() RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE infakYatim_in double;
    DECLARE infakYatim_out double;

    SELECT SUM(jumlah) INTO infakYatim_in
    FROM infak_anakyatim;

    SELECT SUM(jumlah) INTO infakYatim_out
    FROM penerima_anakyatim;

    RETURN (infakYatim_in-infakYatim_out);

end//
DELIMITER ;

-- Dumping structure for function masjidku.getOperationalBalance
DELIMITER //
CREATE FUNCTION `getOperationalBalance`() RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE operational_in double;
    DECLARE operational_out double;

    SELECT SUM(jumlah) INTO operational_in
    FROM infak_operasional;

    SELECT SUM(jumlah) INTO operational_out
    FROM operasional_keluar;

    RETURN (operational_in-operational_out);

end//
DELIMITER ;

-- Dumping structure for function masjidku.getPembangunanBalance
DELIMITER //
CREATE FUNCTION `getPembangunanBalance`() RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE pembangunan_in double;
    DECLARE pembangunan_out double;

    SELECT SUM(jumlah) INTO pembangunan_in
    FROM infak_pembangunan;

    SELECT SUM(jumlah) INTO pembangunan_out
    FROM pembangunan_keluar;

    RETURN (pembangunan_in-pembangunan_out);

end//
DELIMITER ;

-- Dumping structure for function masjidku.getTpaBalance
DELIMITER //
CREATE FUNCTION `getTpaBalance`() RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE tpa_in double;
    DECLARE tpa_out double;

    SELECT SUM(jumlah) INTO tpa_in
    FROM infak_tpa;

    SELECT SUM(jumlah) INTO tpa_out
    FROM tpa_keluar;

    RETURN (tpa_in-tpa_out);

end//
DELIMITER ;

-- Dumping structure for function masjidku.getZakatBalance
DELIMITER //
CREATE FUNCTION `getZakatBalance`() RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE zakat_in double;
    DECLARE zakat_out double;

    SELECT SUM(jumlah) INTO zakat_in
    FROM pemberi_zakat;

    SELECT SUM(jumlah) INTO zakat_out
    FROM penerima_zakat;

    RETURN (zakat_in-zakat_out);

end//
DELIMITER ;

-- Dumping structure for table masjidku.infak_anakyatim
CREATE TABLE IF NOT EXISTS `infak_anakyatim` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.infak_operasional
CREATE TABLE IF NOT EXISTS `infak_operasional` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.infak_pembangunan
CREATE TABLE IF NOT EXISTS `infak_pembangunan` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.infak_tpa
CREATE TABLE IF NOT EXISTS `infak_tpa` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

-- Dumping structure for table masjidku.operasional_keluar
CREATE TABLE IF NOT EXISTS `operasional_keluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.pembangunan_keluar
CREATE TABLE IF NOT EXISTS `pembangunan_keluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.pemberi_zakat
CREATE TABLE IF NOT EXISTS `pemberi_zakat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.penerima_anakyatim
CREATE TABLE IF NOT EXISTS `penerima_anakyatim` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `usia` int(11) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.penerima_zakat
CREATE TABLE IF NOT EXISTS `penerima_zakat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.profil_user
CREATE TABLE IF NOT EXISTS `profil_user` (
  `userid` varchar(50) NOT NULL,
  `notelp` varchar(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  UNIQUE KEY `index_userid` (`userid`),
  CONSTRAINT `foreign_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.reports
CREATE TABLE IF NOT EXISTS `reports` (
  `report_name` varchar(150) NOT NULL,
  `report_jasper` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.sessions
CREATE TABLE IF NOT EXISTS `sessions` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(15) DEFAULT NULL,
  `session_date` date DEFAULT NULL,
  `session_long` int(11) DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='user sessions for monitoring activity';

-- Data exporting was unselected.

-- Dumping structure for table masjidku.tahunanggaran
CREATE TABLE IF NOT EXISTS `tahunanggaran` (
  `tahun` year(4) DEFAULT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table masjidku.tamu
CREATE TABLE IF NOT EXISTS `tamu` (
  `tamuID` varchar(5) NOT NULL,
  `tamuNama` varchar(50) DEFAULT NULL,
  `tamuAlamat` varchar(255) DEFAULT NULL,
  `tamuNotelp` varchar(20) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tamuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

-- Dumping structure for table masjidku.tpa_keluar
CREATE TABLE IF NOT EXISTS `tpa_keluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

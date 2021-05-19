-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 19 Bulan Mei 2021 pada 11.56
-- Versi server: 5.7.24
-- Versi PHP: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `masjidku`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `datatamu`
--

CREATE TABLE `datatamu` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `notelp` varchar(20) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `infakanakyatim`
--

CREATE TABLE `infakanakyatim` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `infakoperasional`
--

CREATE TABLE `infakoperasional` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `infakpembangunan`
--

CREATE TABLE `infakpembangunan` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `infaktpa`
--

CREATE TABLE `infaktpa` (
  `id` int(11) DEFAULT NULL,
  `donatur` varchar(255) DEFAULT 'HAMBA ALLAH',
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kegiatan`
--

CREATE TABLE `kegiatan` (
  `id` int(11) DEFAULT NULL,
  `namaKegiatan` varchar(50) DEFAULT NULL,
  `waktu` varchar(50) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `tempat` varchar(50) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `operasionalkeluar`
--

CREATE TABLE `operasionalkeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembangunankeluar`
--

CREATE TABLE `pembangunankeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemberi_zakat`
--

CREATE TABLE `pemberi_zakat` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` year(4) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penerimaanakyatim`
--

CREATE TABLE `penerimaanakyatim` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `usia` int(11) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penerima_zakat`
--

CREATE TABLE `penerima_zakat` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tahun` year(4) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `profil_user`
--

CREATE TABLE `profil_user` (
  `userid` varchar(15) NOT NULL,
  `notelp` varchar(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `profil_user`
--

INSERT INTO `profil_user` (`userid`, `notelp`, `alamat`) VALUES
('paijo', '12345678', 'jl. Kisanak');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tahunanggaran`
--

CREATE TABLE `tahunanggaran` (
  `tahun` year(4) DEFAULT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tamukegiatan`
--

CREATE TABLE `tamukegiatan` (
  `id_kegiatan` int(11) DEFAULT NULL,
  `id_tamu` int(11) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tpakeluar`
--

CREATE TABLE `tpakeluar` (
  `id` int(11) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `jabatan` enum('admin','ketua','sekretaris','bendahara') DEFAULT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT 'Nonaktif',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`userid`, `password`, `username`, `jabatan`, `status`, `created_at`, `updated_at`) VALUES
('paijo', '3c0becdf230ba5a952c9a499a2cf8aade19b56b9309dad1c0dc4cfc5a48a0824', NULL, 'ketua', 'Aktif', NULL, NULL),
('root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Admin', 'admin', 'Aktif', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pemberi_zakat`
--
ALTER TABLE `pemberi_zakat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `penerima_zakat`
--
ALTER TABLE `penerima_zakat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `profil_user`
--
ALTER TABLE `profil_user`
  ADD UNIQUE KEY `index_userid` (`userid`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pemberi_zakat`
--
ALTER TABLE `pemberi_zakat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `penerima_zakat`
--
ALTER TABLE `penerima_zakat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `profil_user`
--
ALTER TABLE `profil_user`
  ADD CONSTRAINT `foreign_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

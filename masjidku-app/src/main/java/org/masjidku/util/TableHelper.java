package org.masjidku.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableHelper {

    private TableHelper(){}

    public static void setupUndanganColumns(
            TableColumn<?, ?> colNama,
            TableColumn<?, ?> colAlamat,
            TableColumn<?, ?> colKeterangan,
            TableColumn<?, ?> colKegiatan,
            TableColumn<?, ?> colNotelp,
            TableColumn<?, ?> colOperator) {
        if (colNama != null) colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        if (colAlamat != null) colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        if (colKeterangan != null) colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        if (colKegiatan != null) colKegiatan.setCellValueFactory(new PropertyValueFactory<>("kegiatan"));
        if (colNotelp != null) colNotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
        if (colOperator != null) colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }

    public static void setupKegiatanColumns(
            TableColumn<?, ?> colNomor,
            TableColumn<?, ?> colNmKegiatan,
            TableColumn<?, ?> colTempatKegiatan,
            TableColumn<?, ?> colWaktuKegiatan,
            TableColumn<?, ?> colTanggalKegiatan,
            TableColumn<?, ?> colOperator) {
        if (colNomor != null) colNomor.setCellValueFactory(new PropertyValueFactory<>(""));
        if (colNmKegiatan != null) colNmKegiatan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        if (colTempatKegiatan != null) colTempatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tempat"));
        if (colWaktuKegiatan != null) colWaktuKegiatan.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        if (colTanggalKegiatan != null) colTanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        if (colOperator != null) colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
    }
}

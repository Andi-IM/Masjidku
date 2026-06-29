/*
 * Copyright (c) 2026. Creative Commons Legal Code
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

package org.masjidku.events.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.impl.KegiatanRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class EventsClientImpl implements EventsClient {
    private final KegiatanRepository repository;

    public EventsClientImpl() {
        this.repository = new KegiatanRepositoryImpl();
    }

    @Override
    public List<Kegiatan> getAllKegiatan() {
        List<org.masjidku.events.domain.entity.Kegiatan> entities = repository.getAllKegiatan();
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private Kegiatan toModel(org.masjidku.events.domain.entity.Kegiatan entity) {
        return new Kegiatan(
                entity.getIdKegiatan(),
                entity.getNama(),
                entity.getWaktu().toString(),
                entity.getTanggal().toString(),
                entity.getTempat(),
                entity.getOperator()
        );
    }

    private org.masjidku.events.domain.entity.Kegiatan toEntity(Kegiatan model) {
        return new org.masjidku.events.domain.entity.Kegiatan(
          model.idKegiatan(),
          model.nama(),
          java.time.LocalTime.parse(model.waktu()),
          java.time.LocalDate.parse(model.tanggal()),
          model.tempat(),
          model.operator()
        );
    }

    @Override
    public boolean isKegiatanExist(String id) {
        return repository.exists(id);
    }

    @Override
    public void save(Kegiatan kegiatan) {
        repository.saveKegiatan(toEntity(kegiatan));
    }

    @Override
    public void delete(String id) {
        repository.deleteKegiatan(id);
    }

    @Override
    public void update(Kegiatan kegiatan) {
        repository.updateKegiatan(toEntity(kegiatan));
    }

    @Override
    public ObservableList<Tamu> getAllTamu() {
        return null;
    }
}

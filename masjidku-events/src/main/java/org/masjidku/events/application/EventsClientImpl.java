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

import javafx.collections.ObservableList;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.impl.KegiatanRepositoryImpl;

import java.sql.SQLException;

public class EventsClientImpl implements EventsClient {
    private final KegiatanRepository repository;

    public EventsClientImpl() {
        this.repository = new KegiatanRepositoryImpl();
    }

    @Override
    public ObservableList<Kegiatan> getAllKegiatan() {
        try {
            return repository.getAllKegiatan();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching kegiatan", e);
        }
    }

    @Override
    public boolean isKegiatanExist(String id) {
        try {
            return kegiatanUseCase.exists(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking kegiatan existence", e);
        }
    }

    @Override
    public void deleteKegiatan(String id) {
        try {
            kegiatanUseCase.deleteKegiatan(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting kegiatan", e);
        }
    }
}

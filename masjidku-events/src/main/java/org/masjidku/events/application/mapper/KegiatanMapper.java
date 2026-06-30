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

package org.masjidku.events.application.mapper;

import org.masjidku.events.client.model.Kegiatan;

import java.time.LocalDate;
import java.time.LocalTime;

public class KegiatanMapper {

    private KegiatanMapper() {
        // Private constructor to hide the implicit public one
    }

    public static Kegiatan toModel(org.masjidku.events.domain.entity.Kegiatan entity) {
        if (entity == null) {
            return null;
        }
        return new Kegiatan(
                entity.getIdKegiatan(),
                entity.getNama(),
                entity.getWaktu() != null ? entity.getWaktu().toString() : null,
                entity.getTanggal() != null ? entity.getTanggal().toString() : null,
                entity.getTempat(),
                entity.getOperator()
        );
    }

    public static org.masjidku.events.domain.entity.Kegiatan toEntity(Kegiatan model) {
        if (model == null) {
            return null;
        }
        return new org.masjidku.events.domain.entity.Kegiatan(
                model.idKegiatan(),
                model.nama(),
                model.waktu() != null ? LocalTime.parse(model.waktu()) : null,
                model.tanggal() != null ? LocalDate.parse(model.tanggal()) : null,
                model.tempat(),
                model.operator()
        );
    }
}

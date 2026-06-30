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

import org.masjidku.events.client.model.Tamu;

public class TamuMapper {

    private TamuMapper() {
        // Private constructor to hide the implicit public one
    }

    public static Tamu toModel(org.masjidku.events.domain.entity.Tamu entity) {
        if (entity == null) {
            return null;
        }
        return new Tamu(
                entity.getIdTamu(),
                entity.getNama(),
                entity.getAlamat(),
                entity.getNotelp(),
                entity.getOperator()
        );
    }

    public static org.masjidku.events.domain.entity.Tamu toEntity(Tamu model) {
        if (model == null) {
            return null;
        }
        return new org.masjidku.events.domain.entity.Tamu(
                model.idTamu(),
                model.nama(),
                model.alamat(),
                model.notelp(),
                model.operator()
        );
    }
}

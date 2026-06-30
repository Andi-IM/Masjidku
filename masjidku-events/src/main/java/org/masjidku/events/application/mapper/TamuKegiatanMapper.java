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

import org.masjidku.events.client.model.TamuKegiatan;

public class TamuKegiatanMapper {

    private TamuKegiatanMapper() {
        // Private constructor to hide the implicit public one
    }

    public static TamuKegiatan toModel(org.masjidku.events.domain.entity.TamuKegiatan entity) {
        if (entity == null) {
            return null;
        }
        return new TamuKegiatan(
                entity.getIdUndangan(),
                TamuMapper.toModel(entity.getTamu()),
                KegiatanMapper.toModel(entity.getKegiatanModel()),
                entity.getKeterangan(),
                entity.getOperator()
        );
    }

    public static org.masjidku.events.domain.entity.TamuKegiatan toEntity(TamuKegiatan model) {
        if (model == null) {
            return null;
        }
        return new org.masjidku.events.domain.entity.TamuKegiatan(
                model.idUndangan(),
                TamuMapper.toEntity(model.tamu()),
                KegiatanMapper.toEntity(model.kegiatan()),
                model.keterangan(),
                model.operator()
        );
    }
}

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

import org.hibernate.SessionFactory;
import org.masjidku.common.HibernateContext;
import org.masjidku.common.TransactionHelper;
import org.masjidku.events.application.mapper.KegiatanMapper;
import org.masjidku.events.application.mapper.TamuKegiatanMapper;
import org.masjidku.events.application.mapper.TamuMapper;
import org.masjidku.events.client.EventsClient;
import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.impl.KegiatanRepositoryImpl;
import org.masjidku.events.domain.repository.impl.TamuKegiatanRepositoryImpl;
import org.masjidku.events.domain.repository.impl.TamuRepositoryImpl;

import javax.inject.Inject;
import java.util.List;

public class EventsClientImpl implements EventsClient {
    private final TransactionHelper transactionHelper;

    @Inject
    public EventsClientImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;

        this.kegiatanRepository = new KegiatanRepositoryImpl(sessionFactory);
        this.tamuRepository = new TamuRepositoryImpl(sessionFactory);
        this.undanganRepository = new TamuKegiatanRepositoryImpl(sessionFactory);
    }

    public EventsClientImpl() {
        this(HibernateContext.getSessionFactory(), HibernateContext.getTransactionHelper());
    }


    private final KegiatanRepository kegiatanRepository;
    private final TamuRepository tamuRepository;
    private final TamuKegiatanRepository undanganRepository;


    @Override
    public List<Kegiatan> getAllKegiatan() {
        return transactionHelper.executeInTransaction(() -> {
            List<org.masjidku.events.domain.entity.Kegiatan> entities = kegiatanRepository.getAllKegiatan();
            return entities.stream()
                    .map(KegiatanMapper::toModel)
                    .toList();
        });
    }


    @Override
    public boolean isKegiatanExist(String id) {
        return transactionHelper.executeInTransaction(() -> kegiatanRepository.exists(id));
    }

    @Override
    public void save(Kegiatan kegiatan) {
        transactionHelper.executeInTransaction(() -> kegiatanRepository.saveKegiatan(KegiatanMapper.toEntity(kegiatan)));
    }

    @Override
    public void delete(Kegiatan kegiatan) {
        transactionHelper.executeInTransaction(() -> kegiatanRepository.deleteKegiatan(kegiatan.idKegiatan()));
    }

    @Override
    public void delete(Tamu tamu) {
        transactionHelper.executeInTransaction(() -> tamuRepository.delete(TamuMapper.toEntity(tamu).getIdTamu()));
    }

    @Override
    public List<TamuKegiatan> getAllUndangan() {
        return transactionHelper.executeInTransaction(() -> undanganRepository.getAllTamuKegiatan().stream()
                .map(TamuKegiatanMapper::toModel)
                .toList());
    }

    @Override
    public boolean isUndanganExist(String id) {
        return transactionHelper.executeInTransaction(() -> undanganRepository.isUndanganExist(id));
    }

    @Override
    public void delete(TamuKegiatan undangan) {
        transactionHelper.executeInTransaction(() -> undanganRepository.delete(undangan.idUndangan()));
    }

    @Override
    public void save(TamuKegiatan undangan) {
        transactionHelper.executeInTransaction(() -> undanganRepository.save(TamuKegiatanMapper.toEntity(undangan)));
    }

    @Override
    public void update(TamuKegiatan undangan) {
        transactionHelper.executeInTransaction(() -> undanganRepository.update(TamuKegiatanMapper.toEntity(undangan)));
    }

    @Override
    public void update(Kegiatan kegiatan) {
        transactionHelper.executeInTransaction(() -> kegiatanRepository.updateKegiatan(KegiatanMapper.toEntity(kegiatan)));
    }

    @Override
    public List<Tamu> getAllTamu() {
        return transactionHelper.executeInTransaction(() -> tamuRepository.getAll()
                .stream().map(TamuMapper::toModel)
                .toList());
    }


    @Override
    public boolean isTamuExist(String id) {
        return transactionHelper.executeInTransaction(() -> tamuRepository.isTamuExist(id));
    }

    @Override
    public void save(Tamu tamu) {
        transactionHelper.executeInTransaction(() -> tamuRepository.save(TamuMapper.toEntity(tamu)));
    }

    @Override
    public void update(Tamu tamu) {
        transactionHelper.executeInTransaction(() -> tamuRepository.update(TamuMapper.toEntity(tamu)));
    }
}

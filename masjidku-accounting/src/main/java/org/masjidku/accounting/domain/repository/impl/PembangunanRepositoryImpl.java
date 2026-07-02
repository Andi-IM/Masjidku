package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.PembangunanEntity;
import org.masjidku.accounting.domain.repository.PembangunanRepository;

import javax.inject.Inject;

public class PembangunanRepositoryImpl extends BaseRepositoryImpl<PembangunanEntity> implements PembangunanRepository {

    @Inject
    public PembangunanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, PembangunanEntity.class);
    }
}

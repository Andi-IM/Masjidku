package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.DonasiPembangunanEntity;
import org.masjidku.accounting.domain.repository.DonasiPembangunanRepository;

import javax.inject.Inject;

public class DonasiPembangunanRepositoryImpl extends BaseRepositoryImpl<DonasiPembangunanEntity> implements DonasiPembangunanRepository {

    @Inject
    public DonasiPembangunanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, DonasiPembangunanEntity.class);
    }
}

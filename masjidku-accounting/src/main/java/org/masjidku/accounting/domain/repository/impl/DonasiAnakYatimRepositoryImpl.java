package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.DonasiAnakYatimEntity;
import org.masjidku.accounting.domain.repository.DonasiAnakYatimRepository;

import javax.inject.Inject;

public class DonasiAnakYatimRepositoryImpl extends BaseRepositoryImpl<DonasiAnakYatimEntity> implements DonasiAnakYatimRepository {

    @Inject
    public DonasiAnakYatimRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, DonasiAnakYatimEntity.class);
    }
}

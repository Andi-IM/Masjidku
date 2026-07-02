package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.DonasiOperasionalEntity;
import org.masjidku.accounting.domain.repository.DonasiOperasionalRepository;

import javax.inject.Inject;

public class DonasiOperasionalRepositoryImpl extends BaseRepositoryImpl<DonasiOperasionalEntity> implements DonasiOperasionalRepository {

    @Inject
    public DonasiOperasionalRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, DonasiOperasionalEntity.class);
    }
}

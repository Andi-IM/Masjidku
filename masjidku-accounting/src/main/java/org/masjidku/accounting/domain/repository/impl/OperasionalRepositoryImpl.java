package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.OperasionalEntity;
import org.masjidku.accounting.domain.repository.OperasionalRepository;

import javax.inject.Inject;

public class OperasionalRepositoryImpl extends BaseRepositoryImpl<OperasionalEntity> implements OperasionalRepository {

    @Inject
    public OperasionalRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, OperasionalEntity.class);
    }
}

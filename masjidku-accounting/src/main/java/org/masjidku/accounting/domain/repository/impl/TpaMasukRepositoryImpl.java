package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.TpaMasukEntity;
import org.masjidku.accounting.domain.repository.TpaMasukRepository;

import javax.inject.Inject;

public class TpaMasukRepositoryImpl extends BaseRepositoryImpl<TpaMasukEntity> implements TpaMasukRepository {

    @Inject
    public TpaMasukRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, TpaMasukEntity.class);
    }
}

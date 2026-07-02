package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.TpaKeluarEntity;
import org.masjidku.accounting.domain.repository.TpaKeluarRepository;

import javax.inject.Inject;

public class TpaKeluarRepositoryImpl extends BaseRepositoryImpl<TpaKeluarEntity> implements TpaKeluarRepository {

    @Inject
    public TpaKeluarRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, TpaKeluarEntity.class);
    }
}

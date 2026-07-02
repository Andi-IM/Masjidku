package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.ZakatKeluarEntity;
import org.masjidku.accounting.domain.repository.ZakatKeluarRepository;

import javax.inject.Inject;

public class ZakatKeluarRepositoryImpl extends BaseRepositoryImpl<ZakatKeluarEntity> implements ZakatKeluarRepository {

    @Inject
    public ZakatKeluarRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, ZakatKeluarEntity.class);
    }
}

package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.ZakatMasukEntity;
import org.masjidku.accounting.domain.repository.ZakatMasukRepository;

import javax.inject.Inject;

public class ZakatMasukRepositoryImpl extends BaseRepositoryImpl<ZakatMasukEntity> implements ZakatMasukRepository {

    @Inject
    public ZakatMasukRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, ZakatMasukEntity.class);
    }
}

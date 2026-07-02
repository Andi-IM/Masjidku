package org.masjidku.accounting.domain.repository.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import org.masjidku.accounting.domain.repository.AnakYatimRepository;

import javax.inject.Inject;

public class AnakYatimRepositoryImpl extends BaseRepositoryImpl<AnakYatimEntity> implements AnakYatimRepository {

    @Inject
    public AnakYatimRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, AnakYatimEntity.class);
    }
}

package org.masjidku.events.domain.repository.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.base.HibernateUtil;

public class TamuRepositoryImpl implements TamuRepository {

    public TamuRepositoryImpl() {
    }

    @Override
    public Tamu get(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tamu.class, id);
        }
    }

    @Override
    public ObservableList<Tamu> getAll() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var list = session.createQuery("FROM Tamu", Tamu.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        }
    }

    @Override
    public void save(Tamu tamu) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.persist(tamu);
            transaction.commit();
        }
    }

    @Override
    public void update(String[] params) {
        // params: tamuNama, tamuAlamat, tamuNotelp, operator, tamuID
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            Tamu tamu = session.get(Tamu.class, params[4]);
            if (tamu != null) {
                tamu.setNama(params[0]);
                tamu.setAlamat(params[1]);
                tamu.setNotelp(params[2]);
                tamu.setOperator(params[3]);
                session.merge(tamu);
            }
            transaction.commit();
        }
    }

    @Override
    public void delete(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            Tamu tamu = session.get(Tamu.class, id);
            if (tamu != null) {
                session.remove(tamu);
            }
            transaction.commit();
        }
    }

    @Override
    public boolean isTamuExist(String id) {
        return get(id) != null;
    }

    @Override
    public ObservableList<String> getAllTamuName() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var list = session.createQuery("SELECT t.nama FROM Tamu t", String.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        }
    }

    @Override
    public String getIdByName(String name) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("SELECT t.idTamu FROM Tamu t WHERE t.nama = :name", String.class);
            query.setParameter("name", name);
            var result = query.uniqueResult();
            return result != null ? result : "";
        }
    }
}

package org.masjidku.events.domain.repository.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.repository.KegiatanRepository;
import org.masjidku.events.domain.repository.base.HibernateUtil;

public class KegiatanRepositoryImpl implements KegiatanRepository {

    public KegiatanRepositoryImpl() {
    }

    @Override
    public Kegiatan getKegiatanById(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Kegiatan.class, id);
        }
    }

    @Override
    public ObservableList<Kegiatan> getAllKegiatan() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var list = session.createQuery("FROM Kegiatan", Kegiatan.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        }
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.persist(kegiatan);
            transaction.commit();
        }
    }

    @Override
    public void updateKegiatan(String[] params) {
        // params: kegiatanNama, kegiatanWaktu, kegiatanTanggal, kegiatanTempat, operator, kegiatanID
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            Kegiatan kegiatan = session.get(Kegiatan.class, params[5]);
            if (kegiatan != null) {
                kegiatan.setNama(params[0]);
                kegiatan.setWaktu(java.time.LocalTime.parse(params[1]));
                kegiatan.setTanggal(java.time.LocalDate.parse(params[2]));
                kegiatan.setTempat(params[3]);
                kegiatan.setOperator(params[4]);
                session.merge(kegiatan);
            }
            transaction.commit();
        }
    }

    @Override
    public void deleteKegiatan(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            Kegiatan kegiatan = session.get(Kegiatan.class, id);
            if (kegiatan != null) {
                session.remove(kegiatan);
            }
            transaction.commit();
        }
    }

    @Override
    public boolean exists(String id) {
        return getKegiatanById(id) != null;
    }

    @Override
    public ObservableList<String> getAllKegiatanNames() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var list = session.createQuery("SELECT k.nama FROM Kegiatan k", String.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        }
    }

    @Override
    public String getIdByName(String name) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("SELECT k.idKegiatan FROM Kegiatan k WHERE k.nama = :name", String.class);
            query.setParameter("name", name);
            var result = query.uniqueResult();
            return result != null ? result : "";
        }
    }

    @Override
    public Kegiatan getLastKegiatan() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("FROM Kegiatan ORDER BY idKegiatan DESC", Kegiatan.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    @Override
    public String getTotalKegiatanCount() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var count = session.createQuery("SELECT count(k) FROM Kegiatan k", Long.class).uniqueResult();
            return count != null ? count.toString() : "0";
        }
    }
}

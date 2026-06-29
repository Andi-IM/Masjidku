package org.masjidku.events.domain.repository.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.domain.entity.Kegiatan;
import org.masjidku.events.domain.entity.Tamu;
import org.masjidku.events.domain.entity.TamuKegiatan;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;
import org.masjidku.events.domain.repository.base.HibernateUtil;

public class TamuKegiatanRepositoryImpl implements TamuKegiatanRepository {

    public TamuKegiatanRepositoryImpl() {
    }

    @Override
    public TamuKegiatan getTamuKegiatanById(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TamuKegiatan.class, id);
        }
    }

    @Override
    public ObservableList<TamuKegiatan> getAllTamuKegiatan() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var list = session.createQuery("FROM TamuKegiatan tk JOIN FETCH tk.tamu JOIN FETCH tk.kegiatan", TamuKegiatan.class).list();
            return javafx.collections.FXCollections.observableArrayList(list);
        }
    }

    @Override
    public void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            TamuKegiatan tk = new TamuKegiatan();
            tk.setKegiatanModel(session.getReference(Kegiatan.class, idKegiatan));
            tk.setTamu(session.getReference(Tamu.class, idTamu));
            tk.setKeterangan(keterangan);
            session.persist(tk);
            transaction.commit();
        }
    }

    @Override
    public void updateTamuKegiatan(String[] params) {
        // params: keterangan, id_tamu, id_kegiatan, id_undangan
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            TamuKegiatan tk = session.get(TamuKegiatan.class, params[3]);
            if (tk != null) {
                tk.setKeterangan(params[0]);
                tk.setTamu(session.getReference(Tamu.class, params[1]));
                tk.setKegiatanModel(session.getReference(Kegiatan.class, params[2]));
                session.merge(tk);
            }
            transaction.commit();
        }
    }

    @Override
    public void deleteTamuKegiatan(String id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            TamuKegiatan tk = session.get(TamuKegiatan.class, id);
            if (tk != null) {
                session.remove(tk);
            }
            transaction.commit();
        }
    }

    @Override
    public boolean isUndanganExist(String id) {
        return getTamuKegiatanById(id) != null;
    }
}

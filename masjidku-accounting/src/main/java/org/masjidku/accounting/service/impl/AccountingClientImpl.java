package org.masjidku.accounting.service.impl;

import org.hibernate.SessionFactory;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.accounting.domain.entity.*;
import org.masjidku.accounting.domain.repository.*;
import org.masjidku.accounting.domain.repository.impl.*;
import org.masjidku.domain.repository.base.HibernateContext;
import org.masjidku.domain.repository.base.TransactionHelper;
import org.masjidku.accounting.di.DaggerAccountingComponent;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;


public class AccountingClientImpl implements AccountingClient {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;
    private final ZakatMasukRepository zakatMasukRepository;
    private final ZakatKeluarRepository zakatKeluarRepository;
    private final TpaMasukRepository tpaMasukRepository;
    private final TpaKeluarRepository tpaKeluarRepository;
    private final DonasiPembangunanRepository donasiPembangunanRepository;
    private final PembangunanRepository pembangunanRepository;
    private final DonasiOperasionalRepository donasiOperasionalRepository;
    private final OperasionalRepository operasionalRepository;
    private final DonasiAnakYatimRepository donasiAnakYatimRepository;
    private final AnakYatimRepository anakYatimRepository;

    public static AccountingClientImpl provider() {
        return DaggerAccountingComponent.create().getAccountingClientImpl();
    }

    @Inject
    public AccountingClientImpl(
            SessionFactory sessionFactory,
            TransactionHelper transactionHelper,
            ZakatMasukRepository zakatMasukRepository,
            ZakatKeluarRepository zakatKeluarRepository,
            TpaMasukRepository tpaMasukRepository,
            TpaKeluarRepository tpaKeluarRepository,
            DonasiPembangunanRepository donasiPembangunanRepository,
            PembangunanRepository pembangunanRepository,
            DonasiOperasionalRepository donasiOperasionalRepository,
            OperasionalRepository operasionalRepository,
            DonasiAnakYatimRepository donasiAnakYatimRepository,
            AnakYatimRepository anakYatimRepository
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
        this.zakatMasukRepository = zakatMasukRepository;
        this.zakatKeluarRepository = zakatKeluarRepository;
        this.tpaMasukRepository = tpaMasukRepository;
        this.tpaKeluarRepository = tpaKeluarRepository;
        this.donasiPembangunanRepository = donasiPembangunanRepository;
        this.pembangunanRepository = pembangunanRepository;
        this.donasiOperasionalRepository = donasiOperasionalRepository;
        this.operasionalRepository = operasionalRepository;
        this.donasiAnakYatimRepository = donasiAnakYatimRepository;
        this.anakYatimRepository = anakYatimRepository;
    }

    public AccountingClientImpl() {
        this.sessionFactory = HibernateContext.getSessionFactory();
        this.transactionHelper = HibernateContext.getTransactionHelper();
        this.zakatMasukRepository = new ZakatMasukRepositoryImpl(sessionFactory);
        this.zakatKeluarRepository = new ZakatKeluarRepositoryImpl(sessionFactory);
        this.tpaMasukRepository = new TpaMasukRepositoryImpl(sessionFactory);
        this.tpaKeluarRepository = new TpaKeluarRepositoryImpl(sessionFactory);
        this.donasiPembangunanRepository = new DonasiPembangunanRepositoryImpl(sessionFactory);
        this.pembangunanRepository = new PembangunanRepositoryImpl(sessionFactory);
        this.donasiOperasionalRepository = new DonasiOperasionalRepositoryImpl(sessionFactory);
        this.operasionalRepository = new OperasionalRepositoryImpl(sessionFactory);
        this.donasiAnakYatimRepository = new DonasiAnakYatimRepositoryImpl(sessionFactory);
        this.anakYatimRepository = new AnakYatimRepositoryImpl(sessionFactory);
    }

    private ZakatMasukEntity toEntity(ZakatMasuk model) {
        ZakatMasukEntity entity = new ZakatMasukEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setDonatur(model.donatur());
        return entity;
    }

    private ZakatMasuk toModel(ZakatMasukEntity entity) {
        if (entity == null) return new ZakatMasuk();
        return new ZakatMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<ZakatMasuk> getAllZakatMasuk() {
        return transactionHelper.executeInTransaction(() ->
                zakatMasukRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public ZakatMasuk getZakatMasuk(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(zakatMasukRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isZakatMasukExist(String id) {
        return transactionHelper.executeInTransaction(() -> zakatMasukRepository.exists(id));
    }

    @Override
    public void save(ZakatMasuk model) {
        transactionHelper.executeInTransaction(() -> zakatMasukRepository.save(toEntity(model)));
    }

    @Override
    public void update(ZakatMasuk model) {
        transactionHelper.executeInTransaction(() -> zakatMasukRepository.update(toEntity(model)));
    }

    @Override
    public void delete(ZakatMasuk model) {
        transactionHelper.executeInTransaction(() -> zakatMasukRepository.delete(model.id()));
    }

    @Override
    public ZakatMasuk getLastZakatMasuk() {
        return transactionHelper.executeInTransaction(() -> toModel(zakatMasukRepository.getLastRecord()));
    }

    @Override
    public String getTotalZakatMasuk() {
        return transactionHelper.executeInTransaction(zakatMasukRepository::getTotal);
    }

    private ZakatKeluarEntity toEntity(ZakatKeluar model) {
        ZakatKeluarEntity entity = new ZakatKeluarEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setTujuan(model.tujuan());
        return entity;
    }

    private ZakatKeluar toModel(ZakatKeluarEntity entity) {
        if (entity == null) return new ZakatKeluar();
        return new ZakatKeluar(entity.getId(), entity.getTujuan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<ZakatKeluar> getAllZakatKeluar() {
        return transactionHelper.executeInTransaction(() ->
                zakatKeluarRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public ZakatKeluar getZakatKeluar(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(zakatKeluarRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isZakatKeluarExist(String id) {
        return transactionHelper.executeInTransaction(() -> zakatKeluarRepository.exists(id));
    }

    @Override
    public void save(ZakatKeluar model) {
        transactionHelper.executeInTransaction(() -> zakatKeluarRepository.save(toEntity(model)));
    }

    @Override
    public void update(ZakatKeluar model) {
        transactionHelper.executeInTransaction(() -> zakatKeluarRepository.update(toEntity(model)));
    }

    @Override
    public void delete(ZakatKeluar model) {
        transactionHelper.executeInTransaction(() -> zakatKeluarRepository.delete(model.id()));
    }

    @Override
    public ZakatKeluar getLastZakatKeluar() {
        return transactionHelper.executeInTransaction(() -> toModel(zakatKeluarRepository.getLastRecord()));
    }

    @Override
    public String getTotalZakatKeluar() {
        return transactionHelper.executeInTransaction(zakatKeluarRepository::getTotal);
    }

    private TpaMasukEntity toEntity(TpaMasuk model) {
        TpaMasukEntity entity = new TpaMasukEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setDonatur(model.donatur());
        return entity;
    }

    private TpaMasuk toModel(TpaMasukEntity entity) {
        if (entity == null) return new TpaMasuk();
        return new TpaMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<TpaMasuk> getAllTpaMasuk() {
        return transactionHelper.executeInTransaction(() ->
                tpaMasukRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public TpaMasuk getTpaMasuk(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(tpaMasukRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isTpaMasukExist(String id) {
        return transactionHelper.executeInTransaction(() -> tpaMasukRepository.exists(id));
    }

    @Override
    public void save(TpaMasuk model) {
        transactionHelper.executeInTransaction(() -> tpaMasukRepository.save(toEntity(model)));
    }

    @Override
    public void update(TpaMasuk model) {
        transactionHelper.executeInTransaction(() -> tpaMasukRepository.update(toEntity(model)));
    }

    @Override
    public void delete(TpaMasuk model) {
        transactionHelper.executeInTransaction(() -> tpaMasukRepository.delete(model.id()));
    }

    @Override
    public TpaMasuk getLastTpaMasuk() {
        return transactionHelper.executeInTransaction(() -> toModel(tpaMasukRepository.getLastRecord()));
    }

    @Override
    public String getTotalTpaMasuk() {
        return transactionHelper.executeInTransaction(tpaMasukRepository::getTotal);
    }

    private TpaKeluarEntity toEntity(TpaKeluar model) {
        TpaKeluarEntity entity = new TpaKeluarEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setTujuan(model.tujuan());
        entity.setKeterangan(model.keterangan());
        return entity;
    }

    private TpaKeluar toModel(TpaKeluarEntity entity) {
        if (entity == null) return new TpaKeluar();
        return new TpaKeluar(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<TpaKeluar> getAllTpaKeluar() {
        return transactionHelper.executeInTransaction(() ->
                tpaKeluarRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public TpaKeluar getTpaKeluar(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(tpaKeluarRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isTpaKeluarExist(String id) {
        return transactionHelper.executeInTransaction(() -> tpaKeluarRepository.exists(id));
    }

    @Override
    public void save(TpaKeluar model) {
        transactionHelper.executeInTransaction(() -> tpaKeluarRepository.save(toEntity(model)));
    }

    @Override
    public void update(TpaKeluar model) {
        transactionHelper.executeInTransaction(() -> tpaKeluarRepository.update(toEntity(model)));
    }

    @Override
    public void delete(TpaKeluar model) {
        transactionHelper.executeInTransaction(() -> tpaKeluarRepository.delete(model.id()));
    }

    @Override
    public TpaKeluar getLastTpaKeluar() {
        return transactionHelper.executeInTransaction(() -> toModel(tpaKeluarRepository.getLastRecord()));
    }

    @Override
    public String getTotalTpaKeluar() {
        return transactionHelper.executeInTransaction(tpaKeluarRepository::getTotal);
    }

    private DonasiPembangunanEntity toEntity(DonasiPembangunan model) {
        DonasiPembangunanEntity entity = new DonasiPembangunanEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setDonatur(model.donatur());
        return entity;
    }

    private DonasiPembangunan toModel(DonasiPembangunanEntity entity) {
        if (entity == null) return new DonasiPembangunan();
        return new DonasiPembangunan(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiPembangunan> getAllDonasiPembangunan() {
        return transactionHelper.executeInTransaction(() ->
                donasiPembangunanRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public DonasiPembangunan getDonasiPembangunan(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(donasiPembangunanRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isDonasiPembangunanExist(String id) {
        return transactionHelper.executeInTransaction(() -> donasiPembangunanRepository.exists(id));
    }

    @Override
    public void save(DonasiPembangunan model) {
        transactionHelper.executeInTransaction(() -> donasiPembangunanRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiPembangunan model) {
        transactionHelper.executeInTransaction(() -> donasiPembangunanRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiPembangunan model) {
        transactionHelper.executeInTransaction(() -> donasiPembangunanRepository.delete(model.id()));
    }

    @Override
    public DonasiPembangunan getLastDonasiPembangunan() {
        return transactionHelper.executeInTransaction(() -> toModel(donasiPembangunanRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiPembangunan() {
        return transactionHelper.executeInTransaction(donasiPembangunanRepository::getTotal);
    }

    private PembangunanEntity toEntity(Pembangunan model) {
        PembangunanEntity entity = new PembangunanEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setTujuan(model.tujuan());
        entity.setKeterangan(model.keterangan());
        return entity;
    }

    private Pembangunan toModel(PembangunanEntity entity) {
        if (entity == null) return new Pembangunan();
        return new Pembangunan(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<Pembangunan> getAllPembangunan() {
        return transactionHelper.executeInTransaction(() ->
                pembangunanRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public Pembangunan getPembangunan(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(pembangunanRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isPembangunanExist(String id) {
        return transactionHelper.executeInTransaction(() -> pembangunanRepository.exists(id));
    }

    @Override
    public void save(Pembangunan model) {
        transactionHelper.executeInTransaction(() -> pembangunanRepository.save(toEntity(model)));
    }

    @Override
    public void update(Pembangunan model) {
        transactionHelper.executeInTransaction(() -> pembangunanRepository.update(toEntity(model)));
    }

    @Override
    public void delete(Pembangunan model) {
        transactionHelper.executeInTransaction(() -> pembangunanRepository.delete(model.id()));
    }

    @Override
    public Pembangunan getLastPembangunan() {
        return transactionHelper.executeInTransaction(() -> toModel(pembangunanRepository.getLastRecord()));
    }

    @Override
    public String getTotalPembangunan() {
        return transactionHelper.executeInTransaction(pembangunanRepository::getTotal);
    }

    private DonasiOperasionalEntity toEntity(DonasiOperasional model) {
        DonasiOperasionalEntity entity = new DonasiOperasionalEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setDonatur(model.nama());
        return entity;
    }

    private DonasiOperasional toModel(DonasiOperasionalEntity entity) {
        if (entity == null) return new DonasiOperasional();
        return new DonasiOperasional(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiOperasional> getAllDonasiOperasional() {
        return transactionHelper.executeInTransaction(() ->
                donasiOperasionalRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public DonasiOperasional getDonasiOperasional(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(donasiOperasionalRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isDonasiOperasionalExist(String id) {
        return transactionHelper.executeInTransaction(() -> donasiOperasionalRepository.exists(id));
    }

    @Override
    public void save(DonasiOperasional model) {
        transactionHelper.executeInTransaction(() -> donasiOperasionalRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiOperasional model) {
        transactionHelper.executeInTransaction(() -> donasiOperasionalRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiOperasional model) {
        transactionHelper.executeInTransaction(() -> donasiOperasionalRepository.delete(model.id()));
    }

    @Override
    public DonasiOperasional getLastDonasiOperasional() {
        return transactionHelper.executeInTransaction(() -> toModel(donasiOperasionalRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiOperasional() {
        return transactionHelper.executeInTransaction(donasiOperasionalRepository::getTotal);
    }

    private OperasionalEntity toEntity(Operasional model) {
        OperasionalEntity entity = new OperasionalEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setNama(model.tujuan());
        entity.setKeterangan(model.keterangan());
        return entity;
    }

    private Operasional toModel(OperasionalEntity entity) {
        if (entity == null) return new Operasional();
        return new Operasional(entity.getId(), entity.getNama(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<Operasional> getAllOperasional() {
        return transactionHelper.executeInTransaction(() ->
                operasionalRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public Operasional getOperasional(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(operasionalRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isOperasionalExist(String id) {
        return transactionHelper.executeInTransaction(() -> operasionalRepository.exists(id));
    }

    @Override
    public void save(Operasional model) {
        transactionHelper.executeInTransaction(() -> operasionalRepository.save(toEntity(model)));
    }

    @Override
    public void update(Operasional model) {
        transactionHelper.executeInTransaction(() -> operasionalRepository.update(toEntity(model)));
    }

    @Override
    public void delete(Operasional model) {
        transactionHelper.executeInTransaction(() -> operasionalRepository.delete(model.id()));
    }

    @Override
    public Operasional getLastOperasional() {
        return transactionHelper.executeInTransaction(() -> toModel(operasionalRepository.getLastRecord()));
    }

    @Override
    public String getTotalOperasional() {
        return transactionHelper.executeInTransaction(operasionalRepository::getTotal);
    }

    private DonasiAnakYatimEntity toEntity(DonasiAYatim model) {
        DonasiAnakYatimEntity entity = new DonasiAnakYatimEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setDonatur(model.donatur());
        return entity;
    }

    private DonasiAYatim toModel(DonasiAnakYatimEntity entity) {
        if (entity == null) return new DonasiAYatim();
        return new DonasiAYatim(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiAYatim> getAllDonasiAYatim() {
        return transactionHelper.executeInTransaction(() ->
                donasiAnakYatimRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public DonasiAYatim getDonasiAYatim(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(donasiAnakYatimRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isDonasiAYatimExist(String id) {
        return transactionHelper.executeInTransaction(() -> donasiAnakYatimRepository.exists(id));
    }

    @Override
    public void save(DonasiAYatim model) {
        transactionHelper.executeInTransaction(() -> donasiAnakYatimRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiAYatim model) {
        transactionHelper.executeInTransaction(() -> donasiAnakYatimRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiAYatim model) {
        transactionHelper.executeInTransaction(() -> donasiAnakYatimRepository.delete(model.id()));
    }

    @Override
    public DonasiAYatim getLastDonasiAYatim() {
        return transactionHelper.executeInTransaction(() -> toModel(donasiAnakYatimRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiAYatim() {
        return transactionHelper.executeInTransaction(donasiAnakYatimRepository::getTotal);
    }

    private AnakYatimEntity toEntity(AnakYatim model) {
        AnakYatimEntity entity = new AnakYatimEntity();
        entity.setId(model.id());
        entity.setJumlah(model.jumlah());
        entity.setTanggal(model.tanggal());
        entity.setOperator(model.operator());
        entity.setTujuan(model.tujuan());
        entity.setKeterangan(model.keterangan());
        entity.setUsia(model.usia());
        return entity;
    }

    private AnakYatim toModel(AnakYatimEntity entity) {
        if (entity == null) return new AnakYatim();
        return new AnakYatim(
                entity.getId(),
                entity.getTujuan(),
                entity.getUsia(),
                entity.getJumlah(),
                entity.getTanggal(),
                null,
                entity.getOperator()
        );
    }

    @Override
    public List<AnakYatim> getAllAnakYatim() {
        return transactionHelper.executeInTransaction(() ->
                anakYatimRepository.findAll().stream().map(this::toModel).toList()
        );
    }

    @Override
    public AnakYatim getAnakYatim(String id) {
        return transactionHelper.executeInTransaction(() ->
                Objects.requireNonNull(anakYatimRepository.findById(id).map(this::toModel).orElse(null))
        );
    }

    @Override
    public boolean isAnakYatimExist(String id) {
        return transactionHelper.executeInTransaction(() -> anakYatimRepository.exists(id));
    }

    @Override
    public void save(AnakYatim model) {
        transactionHelper.executeInTransaction(() -> anakYatimRepository.save(toEntity(model)));
    }

    @Override
    public void update(AnakYatim model) {
        transactionHelper.executeInTransaction(() -> anakYatimRepository.update(toEntity(model)));
    }

    @Override
    public void delete(AnakYatim model) {
        transactionHelper.executeInTransaction(() -> anakYatimRepository.delete(model.id()));
    }

    @Override
    public AnakYatim getLastAnakYatim() {
        return transactionHelper.executeInTransaction(() -> toModel(anakYatimRepository.getLastRecord()));
    }

    @Override
    public String getTotalAnakYatim() {
        return transactionHelper.executeInTransaction(anakYatimRepository::getTotal);
    }


    private String getBalance(String query) {
        return transactionHelper.executeInTransaction(() -> {
            var session = sessionFactory.getCurrentSession();
            var count = session.createNativeQuery(query, Double.class).uniqueResult();
            return count != null ? String.valueOf(count.longValue()) : "0";
        });
    }

    @Override
    public String getInfakYatimBalance() {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_anakyatim) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_anakyatim)");
    }

    @Override
    public String getOperationalBalance() {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_operasional) - (SELECT COALESCE(SUM(jumlah), 0) FROM operasional_keluar)");
    }

    @Override
    public String getPembangunanBalance() {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_pembangunan) - (SELECT COALESCE(SUM(jumlah), 0) FROM pembangunan_keluar)");
    }

    @Override
    public String getTpaBalance() {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_tpa) - (SELECT COALESCE(SUM(jumlah), 0) FROM tpa_keluar)");
    }

    @Override
    public String getZakatBalance() {
        return getBalance("SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM pemberi_zakat) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_zakat)");
    }
}

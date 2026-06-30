package org.masjidku.accounting.service.impl;

import org.masjidku.accounting.client.service.AccountingClient;
import org.masjidku.accounting.domain.repository.base.HibernateUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.masjidku.accounting.client.model.zakat.ZakatMasuk;
import org.masjidku.accounting.domain.entity.ZakatMasukEntity;
import org.masjidku.accounting.domain.repository.ZakatMasukRepository;
import org.masjidku.accounting.domain.repository.impl.ZakatMasukRepositoryImpl;
import org.masjidku.accounting.client.model.zakat.ZakatKeluar;
import org.masjidku.accounting.domain.entity.ZakatKeluarEntity;
import org.masjidku.accounting.domain.repository.ZakatKeluarRepository;
import org.masjidku.accounting.domain.repository.impl.ZakatKeluarRepositoryImpl;
import org.masjidku.accounting.client.model.tpa.TpaMasuk;
import org.masjidku.accounting.domain.entity.TpaMasukEntity;
import org.masjidku.accounting.domain.repository.TpaMasukRepository;
import org.masjidku.accounting.domain.repository.impl.TpaMasukRepositoryImpl;
import org.masjidku.accounting.client.model.tpa.TpaKeluar;
import org.masjidku.accounting.domain.entity.TpaKeluarEntity;
import org.masjidku.accounting.domain.repository.TpaKeluarRepository;
import org.masjidku.accounting.domain.repository.impl.TpaKeluarRepositoryImpl;
import org.masjidku.accounting.client.model.pembangunan.DonasiPembangunan;
import org.masjidku.accounting.domain.entity.DonasiPembangunanEntity;
import org.masjidku.accounting.domain.repository.DonasiPembangunanRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiPembangunanRepositoryImpl;
import org.masjidku.accounting.client.model.pembangunan.Pembangunan;
import org.masjidku.accounting.domain.entity.PembangunanEntity;
import org.masjidku.accounting.domain.repository.PembangunanRepository;
import org.masjidku.accounting.domain.repository.impl.PembangunanRepositoryImpl;
import org.masjidku.accounting.client.model.operasional.DonasiOperasional;
import org.masjidku.accounting.domain.entity.DonasiOperasionalEntity;
import org.masjidku.accounting.domain.repository.DonasiOperasionalRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiOperasionalRepositoryImpl;
import org.masjidku.accounting.client.model.operasional.Operasional;
import org.masjidku.accounting.domain.entity.OperasionalEntity;
import org.masjidku.accounting.domain.repository.OperasionalRepository;
import org.masjidku.accounting.domain.repository.impl.OperasionalRepositoryImpl;
import org.masjidku.accounting.client.model.anakyatim.DonasiAYatim;
import org.masjidku.accounting.domain.entity.DonasiAnakYatimEntity;
import org.masjidku.accounting.domain.repository.DonasiAnakYatimRepository;
import org.masjidku.accounting.domain.repository.impl.DonasiAnakYatimRepositoryImpl;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.domain.entity.AnakYatimEntity;
import org.masjidku.accounting.domain.repository.AnakYatimRepository;
import org.masjidku.accounting.domain.repository.impl.AnakYatimRepositoryImpl;


public class AccountingClientImpl implements AccountingClient {
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


    public AccountingClientImpl() {
        this.zakatMasukRepository = new ZakatMasukRepositoryImpl();
        this.zakatKeluarRepository = new ZakatKeluarRepositoryImpl();
        this.tpaMasukRepository = new TpaMasukRepositoryImpl();
        this.tpaKeluarRepository = new TpaKeluarRepositoryImpl();
        this.donasiPembangunanRepository = new DonasiPembangunanRepositoryImpl();
        this.pembangunanRepository = new PembangunanRepositoryImpl();
        this.donasiOperasionalRepository = new DonasiOperasionalRepositoryImpl();
        this.operasionalRepository = new OperasionalRepositoryImpl();
        this.donasiAnakYatimRepository = new DonasiAnakYatimRepositoryImpl();
        this.anakYatimRepository = new AnakYatimRepositoryImpl();

    }

    private ZakatMasukEntity toEntity(ZakatMasuk model) {
        ZakatMasukEntity entity = new ZakatMasukEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private ZakatMasuk toModel(ZakatMasukEntity entity) {
        if (entity == null) return new ZakatMasuk();
        return new ZakatMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<ZakatMasuk> getAllZakatMasuk() {
        return HibernateUtil.executeInTransaction(() -> 
            zakatMasukRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public ZakatMasuk getZakatMasuk(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            zakatMasukRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isZakatMasukExist(String id) {
        return HibernateUtil.executeInTransaction(() -> zakatMasukRepository.exists(id));
    }

    @Override
    public void save(ZakatMasuk model) {
        HibernateUtil.executeInTransaction(() -> zakatMasukRepository.save(toEntity(model)));
    }

    @Override
    public void update(ZakatMasuk model) {
        HibernateUtil.executeInTransaction(() -> zakatMasukRepository.update(toEntity(model)));
    }

    @Override
    public void delete(ZakatMasuk model) {
        HibernateUtil.executeInTransaction(() -> zakatMasukRepository.delete(model.getId()));
    }

    @Override
    public ZakatMasuk getLastZakatMasuk() {
        return HibernateUtil.executeInTransaction(() -> toModel(zakatMasukRepository.getLastRecord()));
    }

    @Override
    public String getTotalZakatMasuk() {
        return HibernateUtil.executeInTransaction(() -> zakatMasukRepository.getTotal());
    }
    private ZakatKeluarEntity toEntity(ZakatKeluar model) {
        ZakatKeluarEntity entity = new ZakatKeluarEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        return entity;
    }

    private ZakatKeluar toModel(ZakatKeluarEntity entity) {
        if (entity == null) return new ZakatKeluar();
        return new ZakatKeluar(entity.getId(), entity.getTujuan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<ZakatKeluar> getAllZakatKeluar() {
        return HibernateUtil.executeInTransaction(() -> 
            zakatKeluarRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public ZakatKeluar getZakatKeluar(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            zakatKeluarRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isZakatKeluarExist(String id) {
        return HibernateUtil.executeInTransaction(() -> zakatKeluarRepository.exists(id));
    }

    @Override
    public void save(ZakatKeluar model) {
        HibernateUtil.executeInTransaction(() -> zakatKeluarRepository.save(toEntity(model)));
    }

    @Override
    public void update(ZakatKeluar model) {
        HibernateUtil.executeInTransaction(() -> zakatKeluarRepository.update(toEntity(model)));
    }

    @Override
    public void delete(ZakatKeluar model) {
        HibernateUtil.executeInTransaction(() -> zakatKeluarRepository.delete(model.getId()));
    }

    @Override
    public ZakatKeluar getLastZakatKeluar() {
        return HibernateUtil.executeInTransaction(() -> toModel(zakatKeluarRepository.getLastRecord()));
    }

    @Override
    public String getTotalZakatKeluar() {
        return HibernateUtil.executeInTransaction(() -> zakatKeluarRepository.getTotal());
    }
    private TpaMasukEntity toEntity(TpaMasuk model) {
        TpaMasukEntity entity = new TpaMasukEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private TpaMasuk toModel(TpaMasukEntity entity) {
        if (entity == null) return new TpaMasuk();
        return new TpaMasuk(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<TpaMasuk> getAllTpaMasuk() {
        return HibernateUtil.executeInTransaction(() -> 
            tpaMasukRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public TpaMasuk getTpaMasuk(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            tpaMasukRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isTpaMasukExist(String id) {
        return HibernateUtil.executeInTransaction(() -> tpaMasukRepository.exists(id));
    }

    @Override
    public void save(TpaMasuk model) {
        HibernateUtil.executeInTransaction(() -> tpaMasukRepository.save(toEntity(model)));
    }

    @Override
    public void update(TpaMasuk model) {
        HibernateUtil.executeInTransaction(() -> tpaMasukRepository.update(toEntity(model)));
    }

    @Override
    public void delete(TpaMasuk model) {
        HibernateUtil.executeInTransaction(() -> tpaMasukRepository.delete(model.getId()));
    }

    @Override
    public TpaMasuk getLastTpaMasuk() {
        return HibernateUtil.executeInTransaction(() -> toModel(tpaMasukRepository.getLastRecord()));
    }

    @Override
    public String getTotalTpaMasuk() {
        return HibernateUtil.executeInTransaction(() -> tpaMasukRepository.getTotal());
    }
    private TpaKeluarEntity toEntity(TpaKeluar model) {
        TpaKeluarEntity entity = new TpaKeluarEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private TpaKeluar toModel(TpaKeluarEntity entity) {
        if (entity == null) return new TpaKeluar();
        return new TpaKeluar(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<TpaKeluar> getAllTpaKeluar() {
        return HibernateUtil.executeInTransaction(() -> 
            tpaKeluarRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public TpaKeluar getTpaKeluar(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            tpaKeluarRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isTpaKeluarExist(String id) {
        return HibernateUtil.executeInTransaction(() -> tpaKeluarRepository.exists(id));
    }

    @Override
    public void save(TpaKeluar model) {
        HibernateUtil.executeInTransaction(() -> tpaKeluarRepository.save(toEntity(model)));
    }

    @Override
    public void update(TpaKeluar model) {
        HibernateUtil.executeInTransaction(() -> tpaKeluarRepository.update(toEntity(model)));
    }

    @Override
    public void delete(TpaKeluar model) {
        HibernateUtil.executeInTransaction(() -> tpaKeluarRepository.delete(model.getId()));
    }

    @Override
    public TpaKeluar getLastTpaKeluar() {
        return HibernateUtil.executeInTransaction(() -> toModel(tpaKeluarRepository.getLastRecord()));
    }

    @Override
    public String getTotalTpaKeluar() {
        return HibernateUtil.executeInTransaction(() -> tpaKeluarRepository.getTotal());
    }
    private DonasiPembangunanEntity toEntity(DonasiPembangunan model) {
        DonasiPembangunanEntity entity = new DonasiPembangunanEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiPembangunan toModel(DonasiPembangunanEntity entity) {
        if (entity == null) return new DonasiPembangunan();
        return new DonasiPembangunan(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiPembangunan> getAllDonasiPembangunan() {
        return HibernateUtil.executeInTransaction(() -> 
            donasiPembangunanRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public DonasiPembangunan getDonasiPembangunan(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            donasiPembangunanRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isDonasiPembangunanExist(String id) {
        return HibernateUtil.executeInTransaction(() -> donasiPembangunanRepository.exists(id));
    }

    @Override
    public void save(DonasiPembangunan model) {
        HibernateUtil.executeInTransaction(() -> donasiPembangunanRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiPembangunan model) {
        HibernateUtil.executeInTransaction(() -> donasiPembangunanRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiPembangunan model) {
        HibernateUtil.executeInTransaction(() -> donasiPembangunanRepository.delete(model.getId()));
    }

    @Override
    public DonasiPembangunan getLastDonasiPembangunan() {
        return HibernateUtil.executeInTransaction(() -> toModel(donasiPembangunanRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiPembangunan() {
        return HibernateUtil.executeInTransaction(() -> donasiPembangunanRepository.getTotal());
    }
    private PembangunanEntity toEntity(Pembangunan model) {
        PembangunanEntity entity = new PembangunanEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private Pembangunan toModel(PembangunanEntity entity) {
        if (entity == null) return new Pembangunan();
        return new Pembangunan(entity.getId(), entity.getTujuan(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<Pembangunan> getAllPembangunan() {
        return HibernateUtil.executeInTransaction(() -> 
            pembangunanRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public Pembangunan getPembangunan(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            pembangunanRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isPembangunanExist(String id) {
        return HibernateUtil.executeInTransaction(() -> pembangunanRepository.exists(id));
    }

    @Override
    public void save(Pembangunan model) {
        HibernateUtil.executeInTransaction(() -> pembangunanRepository.save(toEntity(model)));
    }

    @Override
    public void update(Pembangunan model) {
        HibernateUtil.executeInTransaction(() -> pembangunanRepository.update(toEntity(model)));
    }

    @Override
    public void delete(Pembangunan model) {
        HibernateUtil.executeInTransaction(() -> pembangunanRepository.delete(model.getId()));
    }

    @Override
    public Pembangunan getLastPembangunan() {
        return HibernateUtil.executeInTransaction(() -> toModel(pembangunanRepository.getLastRecord()));
    }

    @Override
    public String getTotalPembangunan() {
        return HibernateUtil.executeInTransaction(() -> pembangunanRepository.getTotal());
    }
    private DonasiOperasionalEntity toEntity(DonasiOperasional model) {
        DonasiOperasionalEntity entity = new DonasiOperasionalEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiOperasional toModel(DonasiOperasionalEntity entity) {
        if (entity == null) return new DonasiOperasional();
        return new DonasiOperasional(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiOperasional> getAllDonasiOperasional() {
        return HibernateUtil.executeInTransaction(() -> 
            donasiOperasionalRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public DonasiOperasional getDonasiOperasional(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            donasiOperasionalRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isDonasiOperasionalExist(String id) {
        return HibernateUtil.executeInTransaction(() -> donasiOperasionalRepository.exists(id));
    }

    @Override
    public void save(DonasiOperasional model) {
        HibernateUtil.executeInTransaction(() -> donasiOperasionalRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiOperasional model) {
        HibernateUtil.executeInTransaction(() -> donasiOperasionalRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiOperasional model) {
        HibernateUtil.executeInTransaction(() -> donasiOperasionalRepository.delete(model.getId()));
    }

    @Override
    public DonasiOperasional getLastDonasiOperasional() {
        return HibernateUtil.executeInTransaction(() -> toModel(donasiOperasionalRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiOperasional() {
        return HibernateUtil.executeInTransaction(() -> donasiOperasionalRepository.getTotal());
    }
    private OperasionalEntity toEntity(Operasional model) {
        OperasionalEntity entity = new OperasionalEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setNama(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        return entity;
    }

    private Operasional toModel(OperasionalEntity entity) {
        if (entity == null) return new Operasional();
        return new Operasional(entity.getId(), entity.getNama(), entity.getKeterangan(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<Operasional> getAllOperasional() {
        return HibernateUtil.executeInTransaction(() -> 
            operasionalRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public Operasional getOperasional(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            operasionalRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isOperasionalExist(String id) {
        return HibernateUtil.executeInTransaction(() -> operasionalRepository.exists(id));
    }

    @Override
    public void save(Operasional model) {
        HibernateUtil.executeInTransaction(() -> operasionalRepository.save(toEntity(model)));
    }

    @Override
    public void update(Operasional model) {
        HibernateUtil.executeInTransaction(() -> operasionalRepository.update(toEntity(model)));
    }

    @Override
    public void delete(Operasional model) {
        HibernateUtil.executeInTransaction(() -> operasionalRepository.delete(model.getId()));
    }

    @Override
    public Operasional getLastOperasional() {
        return HibernateUtil.executeInTransaction(() -> toModel(operasionalRepository.getLastRecord()));
    }

    @Override
    public String getTotalOperasional() {
        return HibernateUtil.executeInTransaction(() -> operasionalRepository.getTotal());
    }
    private DonasiAnakYatimEntity toEntity(DonasiAYatim model) {
        DonasiAnakYatimEntity entity = new DonasiAnakYatimEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setDonatur(model.getDonatur());
        return entity;
    }

    private DonasiAYatim toModel(DonasiAnakYatimEntity entity) {
        if (entity == null) return new DonasiAYatim();
        return new DonasiAYatim(entity.getId(), entity.getDonatur(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<DonasiAYatim> getAllDonasiAYatim() {
        return HibernateUtil.executeInTransaction(() -> 
            donasiAnakYatimRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public DonasiAYatim getDonasiAYatim(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            donasiAnakYatimRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isDonasiAYatimExist(String id) {
        return HibernateUtil.executeInTransaction(() -> donasiAnakYatimRepository.exists(id));
    }

    @Override
    public void save(DonasiAYatim model) {
        HibernateUtil.executeInTransaction(() -> donasiAnakYatimRepository.save(toEntity(model)));
    }

    @Override
    public void update(DonasiAYatim model) {
        HibernateUtil.executeInTransaction(() -> donasiAnakYatimRepository.update(toEntity(model)));
    }

    @Override
    public void delete(DonasiAYatim model) {
        HibernateUtil.executeInTransaction(() -> donasiAnakYatimRepository.delete(model.getId()));
    }

    @Override
    public DonasiAYatim getLastDonasiAYatim() {
        return HibernateUtil.executeInTransaction(() -> toModel(donasiAnakYatimRepository.getLastRecord()));
    }

    @Override
    public String getTotalDonasiAYatim() {
        return HibernateUtil.executeInTransaction(() -> donasiAnakYatimRepository.getTotal());
    }
    private AnakYatimEntity toEntity(AnakYatim model) {
        AnakYatimEntity entity = new AnakYatimEntity();
        entity.setId(model.getId());
        entity.setJumlah(model.getJumlah());
        entity.setTanggal(model.getTanggal());
        entity.setOperator(model.getOperator());
        entity.setTujuan(model.getTujuan());
        entity.setKeterangan(model.getKeterangan());
        entity.setUsia(model.getUsia());
        return entity;
    }

    private AnakYatim toModel(AnakYatimEntity entity) {
        if (entity == null) return new AnakYatim();
        return new AnakYatim(entity.getId(), entity.getTujuan(), entity.getUsia(), entity.getJumlah(), entity.getTanggal(), entity.getOperator());
    }

    @Override
    public List<AnakYatim> getAllAnakYatim() {
        return HibernateUtil.executeInTransaction(() -> 
            anakYatimRepository.findAll().stream().map(this::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public AnakYatim getAnakYatim(String id) {
        return HibernateUtil.executeInTransaction(() -> 
            anakYatimRepository.findById(id).map(this::toModel).orElse(null)
        );
    }

    @Override
    public boolean isAnakYatimExist(String id) {
        return HibernateUtil.executeInTransaction(() -> anakYatimRepository.exists(id));
    }

    @Override
    public void save(AnakYatim model) {
        HibernateUtil.executeInTransaction(() -> anakYatimRepository.save(toEntity(model)));
    }

    @Override
    public void update(AnakYatim model) {
        HibernateUtil.executeInTransaction(() -> anakYatimRepository.update(toEntity(model)));
    }

    @Override
    public void delete(AnakYatim model) {
        HibernateUtil.executeInTransaction(() -> anakYatimRepository.delete(model.getId()));
    }

    @Override
    public AnakYatim getLastAnakYatim() {
        return HibernateUtil.executeInTransaction(() -> toModel(anakYatimRepository.getLastRecord()));
    }

    @Override
    public String getTotalAnakYatim() {
        return HibernateUtil.executeInTransaction(() -> anakYatimRepository.getTotal());
    }


    private String getBalance(String query) {
        return HibernateUtil.executeInTransaction(() -> {
            var session = HibernateUtil.getSessionFactory().getCurrentSession();
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

module org.masjidku.events {
    requires transitive org.masjidku.events.client;
    requires java.sql;
    requires org.masjidku.common;
    
    provides org.masjidku.events.client.service.KegiatanService with org.masjidku.events.dao.impl.KegiatanDao;
    provides org.masjidku.events.client.service.TamuService with org.masjidku.events.dao.impl.TamuDao;
    provides org.masjidku.events.client.service.TamuKegiatanService with org.masjidku.events.dao.impl.TamuKegiatanDao;
}

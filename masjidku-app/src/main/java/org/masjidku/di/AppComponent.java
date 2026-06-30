package org.masjidku.di;

import dagger.Component;
import org.masjidku.model.session.dao.SessionManager;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    SessionManager getSessionManager();
}

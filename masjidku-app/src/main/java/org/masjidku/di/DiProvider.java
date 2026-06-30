package org.masjidku.di;

import org.masjidku.common.HibernateContext;

public class DiProvider {
    private DiProvider() {
    }

    private static AppComponent appComponent;

    public static void init() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.create();
            HibernateContext.initialize(
                appComponent.getSessionFactory(),
                appComponent.getTransactionHelper()
            );
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            init();
        }
        return appComponent;
    }
}

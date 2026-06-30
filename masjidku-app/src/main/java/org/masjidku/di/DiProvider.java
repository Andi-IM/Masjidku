package org.masjidku.di;

public class DiProvider {
    private DiProvider() {
    }

    private static AppComponent appComponent;

    public static void init() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.create();
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            init();
        }
        return appComponent;
    }
}

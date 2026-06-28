package org.masjidku.util;

import java.util.ServiceLoader;

/**
 * Service Locator to centralize SPI ServiceLoader logic.
 * Avoids code duplication across controllers.
 */
public class ServiceProvider {
    
    /**
     * Retrieves the first available service provider for the given class.
     * @param serviceClass the class or interface of the service to be loaded
     * @return the service instance
     */
    public static <T> T get(Class<T> serviceClass) {
        return ServiceLoader.load(serviceClass).findFirst().orElseThrow();
    }
}

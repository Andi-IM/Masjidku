package org.masjidku.reporting.client.service;

import java.sql.Connection;
import java.util.function.Consumer;

@FunctionalInterface
public interface ReportConnectionProvider {
    /**
     * Executes the given action with a valid java.sql.Connection.
     * The connection lifecycle (opening, closing) is managed by the provider.
     */
    void executeWithConnection(Consumer<Connection> action);
}

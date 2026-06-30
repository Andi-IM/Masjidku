/*
 * Copyright (c) 2026. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.common;

import org.hibernate.SessionFactory;

public class HibernateContext {
    private static SessionFactory sessionFactory;
    private static TransactionHelper transactionHelper;

    private HibernateContext(){}

    public static void initialize(SessionFactory sf, TransactionHelper th) {
        sessionFactory = sf;
        transactionHelper = th;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) throw new IllegalStateException("HibernateContext not initialized. Call initialize() first.");
        return sessionFactory;
    }

    public static TransactionHelper getTransactionHelper() {
        if (transactionHelper == null) throw new IllegalStateException("HibernateContext not initialized.");
        return transactionHelper;
    }
}

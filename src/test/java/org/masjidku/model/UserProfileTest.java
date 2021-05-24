/*
 * Copyright (c) 2021. Creative Commons Legal Code
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

package org.masjidku.model;

import org.junit.Before;
import org.junit.Test;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;

import static org.junit.Assert.assertEquals;

public class UserProfileTest extends User {
    private UserProfile profile;

    @Before
    public void setUp() {
        profile = new UserProfile(
                "ucok",
                "123456",
                "Ucok",
                "Medan",
                "Sekretaris",
                "123456"
        );
    }

    @Test
    public void testGetId() {
        assertEquals("ucok", profile.getUserId());
    }

    @Test
    public void testGetJabatan() {
        assertEquals("sekretaris", profile.getJabatan().toString);
    }
}
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

package org.masjidku.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {
    static final String thisDate = "22-June-2020";
    static final String anotherDate = "2020-06-22";
    final DateUtil util = new DateUtil();

    @Test
    public void thisDateTest(){
        String[] anotherMethod;
        anotherMethod = util.dateFormatter(thisDate).split("-");
        assertEquals("06", anotherMethod[1]);
    }

    @Test
    public void anotherDateTest(){
        String[] anotherMethod;
        anotherMethod = util.dateFormatter(anotherDate).split("-");
        assertEquals(2020, Integer.parseInt(anotherMethod[2]));
    }
}
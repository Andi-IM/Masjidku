package org.masjidku;

import org.junit.Assert;
import org.junit.Test;
import org.masjidku.util.DateUtil;

public class DateTest {
    static final String thisDate = "22-June-2020";
    static final String anotherDate = "2020-06-22";
    DateUtil util = new DateUtil();

    @Test
    public void thisDateTest(){
        String[] anotherMethod;
        anotherMethod = util.dateFormatter(thisDate).split("-");
        Assert.assertEquals("06", anotherMethod[1]);
    }

    @Test
    public void anotherDateTest(){
        String[] anotherMethod;
        anotherMethod = util.dateFormatter(anotherDate).split("-");
        Assert.assertEquals(2020, Integer.parseInt(anotherMethod[2]));
    }
}

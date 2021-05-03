package org.masjidku;

import org.hamcrest.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SampleTest {

    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }
}

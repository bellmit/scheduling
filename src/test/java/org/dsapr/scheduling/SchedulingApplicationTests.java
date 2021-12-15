package org.dsapr.scheduling;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class SchedulingApplicationTests {

    @Test
    void contextLoads() {
        String l = "a,b,b";
        for (String s : l.split(",")) {
            System.out.println(l.split(",")[0]);
        }
    }

}

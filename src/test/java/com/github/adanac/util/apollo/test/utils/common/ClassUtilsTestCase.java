package com.github.adanac.util.apollo.test.utils.common;

import com.github.adanac.util.apollo.utils.common.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author adanac
 * @version 2014-9-1
 */
public class ClassUtilsTestCase {

    /**
     *
     */
    @Test
    public void getValeByTypeTest() {

        try {

            Integer integer = (Integer) ClassUtils.getValeByType(Integer.class,
                    "3000       ");
            System.out.println(integer);
            Assert.assertEquals(new Integer(3000), integer);

        } catch (Exception e) {

            Assert.assertTrue(false);
        }

    }
}

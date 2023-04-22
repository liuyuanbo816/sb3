package org.zmz.sb3.newfeatures.test.env;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class CommonsLang3StringUtilTest {
    @Test
    public void testSubStringAfter() {
        String s = "/code/image";
        String s1 = StringUtils.substringAfter(s, "/code/");
        System.out.println(s1);
    }

    @Test
    public void testSubStringBefore() {
        String s = "smsValidateCodeProcessor";
        String sub = "ValidateCodeProcessor";
        String prefix = StringUtils.substringBefore(s, sub);
        System.out.println(prefix);
    }
}

package com.itdreamworks.yao.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class Md5UtilTest {

    @Test
    public void encode() {
        String str = Md5Util.encode("123456");
        assertNotEquals("123456",str);
    }
}
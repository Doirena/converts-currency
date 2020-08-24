package com.dovile.convertscurrency.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public class LtBankTest {

    @Test
    public void getData() {
        LtBank ltBank = new LtBank();
        Map<String, String> result = ltBank.getData();
        if (result.isEmpty()){
            assertEquals(new HashMap<String, String>(), result);
        }else {
            assertNotNull(result);
            assertEquals("1", result.get("EUR"));
        }
    }
}
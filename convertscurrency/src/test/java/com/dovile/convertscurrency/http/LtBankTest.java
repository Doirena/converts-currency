package com.dovile.convertscurrency.http;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Test
    public void testGetUrl() throws IOException {
        String url = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
        LtBank ltBank = new LtBank();
        InputStream result = ltBank.getUrl(url);
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.addRequestProperty("User-Agent", "Mozilla/4.76");
        int responseCode = con.getResponseCode();
        int expResult = 200;
        assertEquals(expResult, responseCode);
        assertNotNull(result);
    }
}
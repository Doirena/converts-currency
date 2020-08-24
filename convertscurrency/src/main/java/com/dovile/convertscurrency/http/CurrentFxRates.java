package com.dovile.convertscurrency.http;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public abstract class CurrentFxRates {
    /**
     *
     * @return InputStream with DocumentBuilderFactory parse the data by tag
     */
    abstract InputStream getUrl(String url);

    /**
     * @return just new hashMap, but it can be override and change value
     */
    public abstract Map<String, String> getData() ;
}

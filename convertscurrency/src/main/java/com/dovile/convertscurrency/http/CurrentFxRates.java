package com.dovile.convertscurrency.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public abstract class CurrentFxRates {
    /**
     * @return just new hashMap, but it can be override and change value
     */
    public Map<String, String> getData() {
        return new HashMap<String, String>();
    }
}

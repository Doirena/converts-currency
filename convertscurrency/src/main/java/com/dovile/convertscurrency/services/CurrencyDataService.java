package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.CurrencyData;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface CurrencyDataService {
    /**
     * @return Currency Data
     */
    List<CurrencyData> getAllCurrencyData();

    /**
     * action for data
     * {@link com.dovile.convertscurrency.repositories.ConfigDateRepository} if date.equal new Date() continue
     * else will be update ConfigDate date and update database
     */
    void checkData();

    /**
     * parse data from xml file to dataBase and save rate and type in database
     * use {@link com.dovile.convertscurrency.repositories.CurrencyDataRepository}
     */
    void insertDataBase();

    /**
     * update data from xml file, if condition from checkData() let it
     */
    void updateDataBase();

    /**
     * @param type1
     * @param type2
     * @param value
     * @return convert amount, which clients add by types: from and to
     */
    BigDecimal calculateCurrent(String type1, String type2, String value);
}

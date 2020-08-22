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
     * action for data, if it is empty then add, if need update data per date
     */
    void checkData();

    /**
     * parse data from xml file to dataBase
     */
    void insertDataBase();

    /**
     * update data from xml file it is need
     */
    void updateDataBase();

    /**
     * @param type1
     * @param type2
     * @param value
     * @return convert amount, which cliens add by types from to
     */
    BigDecimal calculateCurrent(String type1, String type2, String value);
}

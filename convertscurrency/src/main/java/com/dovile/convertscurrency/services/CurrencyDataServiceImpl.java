package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.ConfigDate;
import com.dovile.convertscurrency.entities.CurrencyData;
import com.dovile.convertscurrency.http.CurrentFxRates;
import com.dovile.convertscurrency.http.GetCurrentFxRatesFactory;
import com.dovile.convertscurrency.repositories.ConfigDateRepository;
import com.dovile.convertscurrency.repositories.CurrencyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Service
public class CurrencyDataServiceImpl implements CurrencyDataService {

    private final static Logger logger = Logger.getLogger(CurrencyDataServiceImpl.class.getName());

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    @Autowired
    private ConfigDateRepository configDateRepository;

    public List<CurrencyData> getAllCurrencyData() {
        logger.info("Get all type and rate of Currency from database");
        return currencyDataRepository.findAll();
    }

    @Transactional
    public void checkData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ConfigDate configDate = null;
        try {
            configDate = configDateRepository.findById(1).get();
            if (sdf.format(configDate.getDate()).equals(sdf.format(new Date()))) {
                logger.info("Data have been builded before");
            } else {
                logger.info("Update Data");
                configDate.setDate(new Date());
                configDateRepository.save(configDate);
                updateDataBase();
            }
        } catch (NoSuchElementException e) {
            insertDataBase();
            configDate = new ConfigDate();
            configDate.setDate(new Date());
            configDateRepository.save(configDate);
        }
    }

    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void insertDataBase() {
        GetCurrentFxRatesFactory getFxRatesFactory = new GetCurrentFxRatesFactory();
        CurrentFxRates currentFxRates = getFxRatesFactory.getCurrentFxRates();
        Map<String, String> data = currentFxRates.getData();

        for (Map.Entry<String, String> cData : data.entrySet()) {
            CurrencyData currencyData = new CurrencyData();
            currencyData.setType(cData.getKey());
            currencyData.setRate(new BigDecimal(cData.getValue()));
            currencyDataRepository.save(currencyData);
        }
    }

    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void updateDataBase() {
        GetCurrentFxRatesFactory getFxRatesFactory = new GetCurrentFxRatesFactory();
        CurrentFxRates currentFxRates = getFxRatesFactory.getCurrentFxRates();
        Map<String, String> data = currentFxRates.getData();
        CurrencyData currencyData = null;
        for (Map.Entry<String, String> cData : data.entrySet()) {
            try {
                currencyData = currencyDataRepository.findByType(cData.getKey());
                currencyData.setRate(new BigDecimal(cData.getValue()));
                currencyDataRepository.save(currencyData);
            } catch (NullPointerException e) {
                logger.info("Find new rate type");
                currencyData = new CurrencyData();
                currencyData.setType(cData.getKey());
                currencyData.setRate(new BigDecimal(cData.getValue()));
                currencyDataRepository.save(currencyData);
            }
        }
    }

    public BigDecimal calculateCurrent(String type1, String type2, String value) {
        BigDecimal amount = null;

        if (value != null) {
            try {
                BigDecimal valueCurrency = new BigDecimal(value);
                logger.info("Get amount of currency");
                if (type1.equals("EUR")) {
                    amount = currencyDataRepository.findByType(type2).getRate()
                            .multiply(valueCurrency);
                } else if (type2.equals("EUR")) {
                    amount = valueCurrency
                            .divide(currencyDataRepository.findByType(type1).getRate(), 6, RoundingMode.CEILING);
                    System.out.println(currencyDataRepository.findByType(type1).getRate());
                } else {
                    amount = valueCurrency
                            .divide(currencyDataRepository.findByType(type1).getRate(), 6, RoundingMode.CEILING)
                            .multiply(currencyDataRepository.findByType(type2).getRate());
                }
            } catch (NumberFormatException|NullPointerException e){
                logger.severe("Client try to brake page: " + e.getMessage());
                    return null;
            }
        }
        logger.warning("Amount is null");
        return amount;
    }
}


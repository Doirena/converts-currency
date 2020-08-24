package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.ConfigDate;
import com.dovile.convertscurrency.entities.CurrencyData;
import com.dovile.convertscurrency.repositories.ConfigDateRepository;
import com.dovile.convertscurrency.repositories.CurrencyDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyDataServiceImplTest {

    @InjectMocks
    private CurrencyDataServiceImpl currencyDataServiceImpl;
    @Mock
    private CurrencyDataRepository currencyDataRepository;
    @Mock
    private ConfigDateRepository configDateRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCurrencyData() {
        List<CurrencyData> currencyDataList =new ArrayList<>();
        currencyDataList.add(new CurrencyData(1, "EUR", new BigDecimal("1")));
        currencyDataList.add(new CurrencyData(2, "USD", new BigDecimal("2")));
        currencyDataList.add(new CurrencyData(3, "GBP", new BigDecimal("3")));

        when(currencyDataRepository.findAll()).thenReturn(currencyDataList);
        List<CurrencyData> exCurrencyDataList =currencyDataServiceImpl.getAllCurrencyData();
        assertEquals(3, exCurrencyDataList.size());
        assertEquals(currencyDataList.get(0).getType(), exCurrencyDataList.get(0).getType());
        assertEquals(currencyDataList.get(1).getRate(), exCurrencyDataList.get(1).getRate());
        assertEquals(currencyDataList.get(2).getType(), exCurrencyDataList.get(2).getType());
    }

    @Test
    public void checkData() {
        currencyDataServiceImpl.checkData();
        verify(configDateRepository, times(1)).save(any(ConfigDate.class));
    }

    @Test
    public void insertDataBase() {
        currencyDataServiceImpl.insertDataBase();
        verify(currencyDataRepository, times(88)).save(any(CurrencyData.class));
    }

    @Test
    public void calculateCurrent() {
        CurrencyData currencyData = new CurrencyData(1, "EUR", new BigDecimal("1"));
        String type = "EUR";
        when(currencyDataRepository.findByType(type)).thenReturn(currencyData);
        BigDecimal amount = currencyData.getRate().multiply(currencyData.getRate()).multiply(new BigDecimal(10));

        BigDecimal amount_expected = currencyDataServiceImpl.calculateCurrent(type, type, "10");
        assertEquals(amount,amount_expected);
    }
}
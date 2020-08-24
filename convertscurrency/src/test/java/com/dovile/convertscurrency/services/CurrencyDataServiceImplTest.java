package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.CurrencyData;
import com.dovile.convertscurrency.repositories.CurrencyDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyDataServiceImplTest {

    @InjectMocks
    private CurrencyDataServiceImpl currencyDataServiceImpl;
    @Mock
    private CurrencyDataRepository currencyDataRepository;

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
    }

    @Test
    public void insertDataBase() {
    }

    @Test
    public void updateDataBase() {
    }

    @Test
    public void calculateCurrent() {
//        CurrencyData currencyData = new CurrencyData(1, "EUR", new BigDecimal("1"));
//        String type = "EUR";
//        when(currencyDataRepository.findByType(type)).thenReturn(currencyData);
//
//
//        BigDecimal amount = currencyDataServiceImpl.calculateCurrent("EUR", "EUR", "10");

    }
}
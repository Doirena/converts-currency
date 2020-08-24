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

import static org.mockito.BDDMockito.given;
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

    @Test(expected = NullPointerException.class)
    public void checkData() {
        ConfigDate configDate = new ConfigDate(null, new Date());
        Integer id =1;
       // when(configDateRepository.findById(id).get()).thenThrow(NoSuchElementException.class);
        //when(configDateRepository.save(configDate)).thenReturn(configDate);
////                .then(configDateRepository.save(configDate))
//                .thenReturn(Optional.of(configDate));
//        doThrow(new NoSuchElementException ()).when(configDateRepository).save(configDate);
//        when(configDateRepository.save(configDate)).thenReturn(configDate);
//        given(configDateRepository.save(configDate)).willAnswer(invocation -> invocation.getArgument(0));

        doThrow(NoSuchElementException.class)
                .when(configDateRepository)
                .findById(id).get();
//        when(configDateRepository.save(configDate)).thenReturn(configDate);
        given(configDateRepository.save(configDate)).willAnswer(invocation -> invocation.getArgument(0));
        currencyDataServiceImpl.checkData();
        verify(configDateRepository, times(10)).save(configDate);
        assertEquals(new Date(), configDate.getDate());
    }

    @Test
    public void insertDataBase() {
    }

    @Test
    public void updateDataBase() {
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
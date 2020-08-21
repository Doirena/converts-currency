package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.CurrencyData;
import com.dovile.convertscurrency.repositories.CurrencyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.List;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Service
public class CurrencyDataServiceImpl implements CurrencyDataService {

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    public List<CurrencyData> getAllCurrencyData() {
        return currencyDataRepository.findAll();
    }

    public void insertDataBase() {
        if (currencyDataRepository.findAll() != null) {
            currencyDataRepository.deleteAll();
        }
        try {
            String url = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.76");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));

            NodeList errNodes = doc.getElementsByTagName("FxRate");

            for (int i = 0; i < errNodes.getLength(); i++) {
                Element err = (Element) errNodes.item(i);
                for (int j = 0; j < err.getElementsByTagName("Ccy").getLength(); j++) {
                    try {
                        CurrencyData currencyData = new CurrencyData();
                        currencyData.setType(err.getElementsByTagName("Ccy").item(j).getTextContent());
                        currencyData.setRate(new BigDecimal(err.getElementsByTagName("Amt").item(j).getTextContent()));
                        currencyDataRepository.save(currencyData);
                    } catch (DataIntegrityViolationException e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public BigDecimal calculateCurrent(String type1, String type2, String value) {
        BigDecimal amount = null;
        if (value != null) {
            if (type1.equals("EUR")) {
                amount = currencyDataRepository.findByType(type2).getRate()
                        .multiply(new BigDecimal(value));
            } else if (type2.equals("EUR")) {
                amount = new BigDecimal(value)
                        .divide(currencyDataRepository.findByType(type1).getRate(), 6, RoundingMode.CEILING);
                System.out.println(currencyDataRepository.findByType(type1).getRate());
            } else {
                amount = new BigDecimal(value)
                        .divide(currencyDataRepository.findByType(type1).getRate(), 6, RoundingMode.CEILING)
                        .multiply(currencyDataRepository.findByType(type2).getRate());
            }
        }
        return amount;
    }

}


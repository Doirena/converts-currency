package com.dovile.convertscurrency.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public class LtBank extends CurrentFxRates {

    private final static Logger logger = Logger.getLogger(LtBank.class.getName());

    private final String url = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=";
    private final String tp = "EU";
    private final String rate = "FxRate";
    private final String tagNameC = "Ccy";
    private final String tagNameA = "Amt";

    /**
     *
     * @param url
     * @return return inputStream
     */
    InputStream getUrl(String url) {
        URL obj = null;
        try {
            obj = new URL(url + tp);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            InputStream is = connection.getInputStream();
            return is;
        } catch (Exception e) {
            logger.severe("Error with path!" + e.getMessage());
            return null;
        }
    }

    /**
     * @return data from xml file, where will be type and rate
     */
    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        try {
            logger.info("Connetion for the parse data");
            InputStream is = getUrl(url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            NodeList errNodes = doc.getElementsByTagName(rate);
            for (int i = 0; i < errNodes.getLength(); i++) {
                Element err = (Element) errNodes.item(i);
                for (int j = 0; j < err.getElementsByTagName(tagNameC).getLength(); j++) {
                    map.put(err.getElementsByTagName(tagNameC).item(j).getTextContent(),
                            err.getElementsByTagName(tagNameA).item(j).getTextContent());
                }
            }
        } catch (Exception e) {
            logger.severe("Problem with connetion, problem to parse data!" + e.getMessage());
        }
        return map;
    }
}

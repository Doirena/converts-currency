package com.dovile.convertscurrency.controllers;

import com.dovile.convertscurrency.entities.CurrencyData;
import com.dovile.convertscurrency.services.ClientActionService;
import com.dovile.convertscurrency.services.CurrencyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/currency")
public class MainController {

    @Autowired
    private CurrencyDataService currencyDataService;

    @Autowired
    private ClientActionService clientActionService;

    @GetMapping
    public String getCal(@RequestParam (value = "from", required = false) String from,
                         @RequestParam (value = "to", required = false) String to,
                         @RequestParam (name = "currencyAmount", required = false) String currencyAmount,
                         Model model){

        String fieldValue = "1";
        if (currencyAmount != null){
            fieldValue = currencyAmount;
        }

        List<CurrencyData> currencyDatas = currencyDataService.getAllCurrencyData();
        model.addAttribute("currencyDatas", currencyDatas);
        BigDecimal sum = currencyDataService.calculateCurrent(from, to, currencyAmount);
        if (sum !=null){
            clientActionService.saveClientAction(from, to, currencyAmount, sum);
        }
        model.addAttribute("type", from);
        model.addAttribute("type1",to);
        model.addAttribute("sum", sum);
        model.addAttribute("fieldValue", fieldValue);
        return "calculate";
    }
}

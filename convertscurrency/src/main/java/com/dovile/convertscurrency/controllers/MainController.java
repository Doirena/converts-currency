package com.dovile.convertscurrency.controllers;

import com.dovile.convertscurrency.services.ClientActionService;
import com.dovile.convertscurrency.services.CurrencyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Controller
@RequestMapping("/currency")
public class MainController {

    @Autowired
    private CurrencyDataService currencyDataService;

    @Autowired
    private ClientActionService clientActionService;

    @GetMapping
    public String getCal(@RequestParam(value = "from", required = false) String from,
                         @RequestParam(value = "to", required = false) String to,
                         @RequestParam(name = "currencyAmount", required = false) String currencyAmount,
                         Model model) {

        currencyDataService.checkData();
        model.addAttribute("currencyDatas", currencyDataService.getAllCurrencyData());
        BigDecimal sum = currencyDataService.calculateCurrent(from, to, currencyAmount);
        if (sum != null) {
            clientActionService.saveClientAction(from, to, currencyAmount, sum);
        } else {
            currencyAmount = "1";
            from = null;
            to = null;
        }
        model.addAttribute("type", from);
        model.addAttribute("fromValue", currencyAmount);
        model.addAttribute("type1", to);
        model.addAttribute("sum", sum);
        model.addAttribute("fieldValue", currencyAmount);
        return "calculate";
    }
}

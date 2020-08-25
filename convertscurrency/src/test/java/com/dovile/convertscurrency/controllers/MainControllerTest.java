package com.dovile.convertscurrency.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String getRootUrl() {
        return "/currency";
    }

    @Test
    public void getCal_without_parameters() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.model().attribute("fieldValue", "1"))
                .andExpect(MockMvcResultMatchers.view().name("calculate"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCal_with_parameters() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl()).param("from", "EUR")
                .param("to", "EUR").param("currencyAmount", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("type", "EUR"))
                .andExpect(MockMvcResultMatchers.model().attribute("type1", "EUR"))
                .andExpect(MockMvcResultMatchers.model().attribute("sum", new BigDecimal("1")))
                .andExpect(MockMvcResultMatchers.model().attribute("fieldValue", "1"))
                .andExpect(MockMvcResultMatchers.view().name("calculate"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCal_getNull_when_param_is_bad() throws Exception {
        String result = null;
        this.mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl()).param("from", "kkk")
                .param("to", "vvvv").param("currencyAmount", "ddd"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("type", result))
                .andExpect(MockMvcResultMatchers.model().attribute("type1", result))
                .andExpect(MockMvcResultMatchers.model().attribute("sum", result))
                .andExpect(MockMvcResultMatchers.model().attribute("fieldValue", "1"))
                .andExpect(MockMvcResultMatchers.view().name("calculate"))
                .andDo(MockMvcResultHandlers.print());
    }
}
package com.dovile.convertscurrency.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@Entity
@Table(name = "currency_data")
@NamedQuery(name = "CurrencyData.findByType", query = "SELECT c FROM CurrencyData c WHERE c.type = :type")
public class CurrencyData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    private String type;

    @NotNull
    private BigDecimal rate;

    public CurrencyData() {
    }

    public CurrencyData(Integer id, String type, BigDecimal rate) {
        this.id = id;
        this.type = type;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}


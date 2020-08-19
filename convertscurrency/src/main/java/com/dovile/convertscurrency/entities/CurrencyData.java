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
@NamedQueries({
        @NamedQuery(name = "CurrencyData.findAll", query = "SELECT c FROM CurrencyData c"),
        @NamedQuery(name = "CurrencyData.findById", query = "SELECT c FROM CurrencyData c WHERE c.id = :id"),
        @NamedQuery(name = "CurrencyData.findByType", query = "SELECT c FROM CurrencyData c WHERE c.type = :type"),
        @NamedQuery(name = "CurrencyData.findByRate", query = "SELECT c FROM CurrencyData c WHERE c.rate = :rate")})
public class CurrencyData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private BigDecimal rate;

    public CurrencyData() {
    }

    public CurrencyData(Integer id) {
        this.id = id;
    }

    public CurrencyData(Integer id, String type, BigDecimal rate) {
        this.id = id;
        this.type = type;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrencyData)) {
            return false;
        }
        CurrencyData other = (CurrencyData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}


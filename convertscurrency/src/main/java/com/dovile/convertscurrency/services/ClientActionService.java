package com.dovile.convertscurrency.services;

import java.math.BigDecimal;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface ClientActionService {
   /**
    *
    * @param from
    * @param to
    * @param currencyAmount
    * @param sum
    * save Client action, used {@link com.dovile.convertscurrency.repositories.ClientActionRepository}
    */
   public void saveClientAction(String from, String to, String currencyAmount, BigDecimal sum);
}

package com.dovile.convertscurrency.http;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
public class GetCurrentFxRatesFactory {
    /**
     *
     * @return new LtBank(), but if where will be more page, where will be possibility add new class with other source not from LT
     * then need add condition in this method
     */
    public CurrentFxRates getCurrentFxRates(){
            return new LtBank();
    }
}

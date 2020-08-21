package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.ClientAction;
import com.dovile.convertscurrency.repositories.ClientActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Service
public class ClientActionServiceImpl implements ClientActionService {

    @Autowired
    private ClientActionRepository clientActionRepository;

    public void saveClientAction(String from, String to, String currencyAmount, BigDecimal sum){
        ClientAction clientAction = new ClientAction();
        clientAction.setAction("From "+from+" to "+to+" change "+ currencyAmount+" GET "+sum.toString());
        clientActionRepository.save(clientAction);
    }
}

package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.ClientAction;
import com.dovile.convertscurrency.repositories.ClientActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Service
public class ClientActionServiceImpl implements ClientActionService {

    private final static Logger logger = Logger.getLogger(ClientActionServiceImpl.class.getName());

    @Autowired
    private ClientActionRepository clientActionRepository;

    public void saveClientAction(String from, String to, String currencyAmount, BigDecimal sum) {
        logger.info("Create Client action");
        ClientAction clientAction = new ClientAction();
        clientAction.setAction("From " + from + " to " + to + " change " + currencyAmount + " GET " + sum);
        clientActionRepository.save(clientAction);
    }
}

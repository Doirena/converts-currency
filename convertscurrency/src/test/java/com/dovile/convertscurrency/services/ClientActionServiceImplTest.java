package com.dovile.convertscurrency.services;

import com.dovile.convertscurrency.entities.ClientAction;
import com.dovile.convertscurrency.repositories.ClientActionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientActionServiceImplTest {

    @InjectMocks
    private ClientActionServiceImpl clientActionService;
    @Mock
    private ClientActionRepository clientActionRepository;

    @Test
    public void saveClientAction() {
        ClientAction clientAction = new ClientAction(null, "From EUR to USD change 10 GET 11.933000");
        given(clientActionRepository.save(clientAction)).willAnswer(invocation -> invocation.getArgument(0));
        clientActionService.saveClientAction("EUR", "USD", "10",new BigDecimal("11.933000"));
        verify(clientActionRepository, times(1)).save(clientAction);
    }
}
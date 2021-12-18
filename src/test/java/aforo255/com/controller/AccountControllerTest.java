package aforo255.com.controller;

import static aforo255.com.msazaccount.util.TestUtil.buildAccountEntity;

import aforo255.com.msazaccount.service.IAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private IAccountService service;

    @InjectMocks
    private AccountController accountController;

    @Test
    void whenListAllAccountsThenReturnAccounts() {

        var accountEntity = buildAccountEntity();

        Mockito.when(service.findAll())
                .thenReturn(Collections.singletonList(accountEntity));

        var accountResponses = accountController.listar();
        var accountResponse = accountResponses.get(0);

        Assertions.assertNotNull(accountResponses);
        Assertions.assertEquals(accountEntity.getIdAccount(), accountResponse.getIdAccount());
    }

    @Test
    void whenFindByIdThenReturnAccount() {

        var accountEntity = buildAccountEntity();

        Mockito.when(service.findById(1))
                .thenReturn(accountEntity);

        var accountResponse = accountController.detalle(1);

        Assertions.assertNotNull(accountResponse);
        Assertions.assertEquals(accountEntity.getIdAccount(), accountResponse.getIdAccount());
    }

}

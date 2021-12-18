package aforo255.com.msazaccount.service.impl;

import static aforo255.com.msazaccount.util.TestUtil.buildAccountEntity;

import aforo255.com.msazaccount.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void whenListAllAccountsThenReturnAccounts() {

        var accountEntity = buildAccountEntity();

        Mockito.when(accountRepository.findAll())
                .thenReturn(Collections.singletonList(accountEntity));

        var accountEntities = accountService.findAll();
        var entity = accountEntities.get(0);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(accountEntity.getIdAccount(), entity.getIdAccount());

    }

    @Test
    void whenFindByIdThenReturnAccount() {

        var accountEntity = buildAccountEntity();

        Mockito.when(accountRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(accountEntity));

        var entity = accountService.findById(1);

        Assertions.assertEquals(accountEntity.getIdAccount(), entity.getIdAccount());
    }

    @Test
    void whenFindByIdThenReturnAccountEmpty() {

        Mockito.when(accountRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        var entity = accountService.findById(1);

        Assertions.assertNull(entity);
    }

    @Test
    void whenSaveAccountThenReturnAccount() {

        var accountEntity = buildAccountEntity();

        Mockito.when(accountRepository.save(Mockito.any()))
                .thenReturn(accountEntity);

        var entity = accountService.save(accountEntity);

        Assertions.assertEquals(accountEntity.getIdAccount(), entity.getIdAccount());

    }
}

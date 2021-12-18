package aforo255.com.msazaccount.topic;

import static aforo255.com.msazaccount.util.TestUtil.buildAccountEntity;

import aforo255.com.msazaccount.model.domain.Transaction;
import aforo255.com.msazaccount.service.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionEventsTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private TransactionEvents transactionEvents;

    @Test
    void test() throws JsonProcessingException {

        Mockito.when(accountService.findById(Mockito.anyInt()))
                .thenReturn(buildAccountEntity());

        Mockito.when(accountService.save(Mockito.any()))
                .thenReturn(buildAccountEntity());

        var transaction = transactionBuilder().type("deposit").build();

        var json = mapper.writeValueAsString(transaction);

        transactionEvents.processTransactionEvent(json);

        Assertions.assertNotNull(json);

    }

    @Test
    void test1() throws JsonProcessingException {

        Mockito.when(accountService.findById(Mockito.anyInt()))
                .thenReturn(buildAccountEntity());

        Mockito.when(accountService.save(Mockito.any()))
                .thenReturn(buildAccountEntity());

        var transaction = transactionBuilder().type("withdrawal").build();

        var json = mapper.writeValueAsString(transaction);

        transactionEvents.processTransactionEvent(json);

        Assertions.assertNotNull(json);

    }

    public static Transaction.TransactionBuilder transactionBuilder() {
        return Transaction.builder()
                .accountId(1)
                .amount(100.00)
                .id(1);
    }
}

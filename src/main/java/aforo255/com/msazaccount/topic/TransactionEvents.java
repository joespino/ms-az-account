package aforo255.com.msazaccount.topic;

import aforo255.com.msazaccount.model.domain.Transaction;
import aforo255.com.msazaccount.model.entity.AccountEntity;
import aforo255.com.msazaccount.service.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionEvents {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IAccountService accountService;

    public void processTransactionEvent(String transaction) throws JsonProcessingException {

        log.debug("Impimiendo el valor {}", transaction);

        double newAmount = 0;

        Transaction event = mapper.readValue(transaction, Transaction.class);

        AccountEntity account = accountService.findById(event.getAccountId());

        log.debug("Impimiendo el event.getType() " + event.getType());

        switch (event.getType()) {
            case "deposit":
                newAmount = account.getTotalAmount() + event.getAmount();
                break;

            case "withdrawal":
                newAmount = account.getTotalAmount() - event.getAmount();
                break;
        }
        account.setTotalAmount(newAmount);
        log.info("print new amoumt ***" + newAmount);
        log.info("update N° account ***" + event.getAccountId());
        accountService.save(account);

    }

}

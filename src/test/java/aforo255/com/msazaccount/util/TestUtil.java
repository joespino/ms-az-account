package aforo255.com.msazaccount.util;

import aforo255.com.msazaccount.model.entity.AccountEntity;
import aforo255.com.msazaccount.model.entity.CustomerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {

    public static AccountEntity buildAccountEntity() {
        return AccountEntity.builder()
                .IdAccount(1)
                .customer(CustomerEntity.builder()
                        .IdCustomer(1)
                        .FullName("fullName")
                        .build())
                .TotalAmount(100.00)
                .IdCustomer(1)
                .build();
    }

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        var json = objectMapper.writeValueAsString(buildAccountEntity());
        var accountEntity = objectMapper.readValue(json, AccountEntity.class);

        System.out.println(json);
        System.out.println(accountEntity);

    }
}

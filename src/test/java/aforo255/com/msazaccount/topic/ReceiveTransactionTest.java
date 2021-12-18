//package aforo255.com.msazaccount.topic;
//
//import aforo255.com.config.TopicProperties;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class ReceiveTransactionTest {
//
//    @Mock
//    private TopicProperties properties;
//
//    @Mock
//    private TransactionEvents transactionEvents;
//
//    @InjectMocks
//    private ReceiveTransaction receiveTransaction;
//
//    @Test
//    void test() throws JsonProcessingException, InterruptedException {
//
//        var subscriptionName = "Endpoint=sb://aforo255fv.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=COe9rGUebe+kldzaaKuAUkbAgAulw4abCgchGzFmM8Y=";
//
//        Mockito.when(properties.getSubscriptionName())
//                .thenReturn(subscriptionName);
//
//        Mockito.when(properties.getTopicName())
//                .thenReturn("transaction-topic");
//
//        Mockito.when(properties.getConnectionString())
//                .thenReturn("account-subscription");
//
//        transactionEvents.processTransactionEvent(Mockito.any());
//
//        receiveTransaction.receiveMessages();
//
//        Assertions.assertNotNull(subscriptionName);
//    }
//
//}

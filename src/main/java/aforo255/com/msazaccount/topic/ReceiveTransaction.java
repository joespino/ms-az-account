package aforo255.com.msazaccount.topic;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusException;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.fasterxml.jackson.core.JsonProcessingException;

import aforo255.com.config.TopicProperties;

@Slf4j
@Component
public class ReceiveTransaction {

	@Autowired
	private TopicProperties properties;

	@Autowired
	private TransactionEvents transactionEvents;


	@Scheduled(fixedRate = 30000)
	void receiveMessages() throws InterruptedException {

		// Create an instance of the processor through the ServiceBusClientBuilder
		ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
				.connectionString(properties.getConnectionString()).processor().topicName(properties.getTopicName())
				.subscriptionName(properties.getSubscriptionName()).processMessage(onMessage).processError(onError)
				.buildProcessorClient();

		log.debug("Starting the processor");
		processorClient.start();

		TimeUnit.SECONDS.sleep(10);
		log.debug("Stopping and closing the processor");
		processorClient.close();
	}

	Consumer<ServiceBusReceivedMessageContext> onMessage = context -> {
		ServiceBusReceivedMessage message = context.getMessage();

		log.debug("Processing message. Sequence #: {}. Contents: {}", message.getSequenceNumber(),
				message.getBody());
		
		try {
			
			transactionEvents.processTransactionEvent(message.getBody().toString());
			context.complete();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			e.printStackTrace();
		}
	};

	Consumer<ServiceBusErrorContext> onError = context -> {
		log.error("Error when receiving messages from namespace: '{}'. Entity: '{}'",
				context.getFullyQualifiedNamespace(), context.getEntityPath());

		if (context.getException() instanceof ServiceBusException) {
			ServiceBusException exception = (ServiceBusException) context.getException();
			log.error("Error source: {}, reason {}", context.getErrorSource(), exception.getReason());
		} else {
			log.error("Error occurred: ", context.getException());
		}
	};

}

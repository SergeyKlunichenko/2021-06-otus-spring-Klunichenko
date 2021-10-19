package ru.otus.underwriting;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.underwriting.domain.CreditLoan;
import ru.otus.underwriting.domain.CreditRequest;
import ru.otus.underwriting.messages.Bank;

import java.math.BigDecimal;
import java.util.Date;

@IntegrationComponentScan
@SuppressWarnings({ "resource", "Duplicates", "InfiniteLoopStatement" })
@ComponentScan
@Configuration
@EnableIntegration

public class UnderwritingApplication {

	@Bean
	public QueueChannel prepareChannel(){
		return MessageChannels.queue(10).get();
	}

	@Bean
	public PublishSubscribeChannel underwritingChannel(){
		return MessageChannels.publishSubscribe().get();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller(){
		return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
	}


	@Bean
	public IntegrationFlow underwritingFlow(){
		return IntegrationFlows.from("prepareChannel")
				.handle("underwritingService", "approval")
				.channel("underwritingChannel")
				.get();
	}

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( UnderwritingApplication.class );

		Bank bank = ctx.getBean(Bank.class);
		while (true) {
			CreditRequest creditRequest = new CreditRequest("r" + (new Date()).getTime(), RandomUtils.nextDouble(1, 1000000));
			System.out.println("Кредитная заявка оформлена: " + creditRequest);
			CreditLoan creditLoan = bank.process(creditRequest);
			System.out.println("Кредит получен: " + creditLoan);
		}
	}

}

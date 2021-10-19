package ru.otus.underwriting.messages;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.underwriting.domain.CreditLoan;
import ru.otus.underwriting.domain.CreditRequest;

@MessagingGateway
public interface Bank {
    @Gateway(requestChannel = "prepareChannel", replyChannel = "underwritingChannel")
    CreditLoan process(CreditRequest creditRequest);
}

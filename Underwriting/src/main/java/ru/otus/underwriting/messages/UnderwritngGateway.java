package ru.otus.underwriting.messages;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.underwriting.domain.CreditLoan;
import ru.otus.underwriting.domain.CreditRequest;

import java.net.CacheRequest;

@MessagingGateway
public interface UnderwritngGateway {
    @Gateway(replyChannel = "approvedChannel")
    CreditLoan approval(CreditRequest creditRequest);
}

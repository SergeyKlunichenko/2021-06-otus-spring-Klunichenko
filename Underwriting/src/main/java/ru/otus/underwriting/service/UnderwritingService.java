package ru.otus.underwriting.service;

import org.springframework.stereotype.Service;
import ru.otus.underwriting.domain.CreditLoan;
import ru.otus.underwriting.domain.CreditRequest;

@Service
public class UnderwritingService {
    public CreditLoan approval(CreditRequest creditRequest) throws  Exception{
        System.out.printf("Кредитная заявка №  %s отправлена на одобрение \n", creditRequest.getRequestNumber());
        Thread.sleep(3000);
        CreditLoan creditLoan = new CreditLoan("loan_"+creditRequest.getRequestNumber(), creditRequest.getQty());
        System.out.printf("Кредит № %s одобрен\n", creditRequest.getRequestNumber());

        return creditLoan;
    }
}

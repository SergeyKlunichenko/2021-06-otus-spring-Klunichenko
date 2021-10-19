package ru.otus.underwriting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreditLoan {
    private  String loanNumber;
    private double qty;
}

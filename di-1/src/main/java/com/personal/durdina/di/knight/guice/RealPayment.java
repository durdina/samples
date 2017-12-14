package com.personal.durdina.di.knight.guice;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.math.BigDecimal;
import java.util.Date;

public class RealPayment implements Payment {

    private final CreditService creditService;
    private final Date startDate;
    private final BigDecimal amount;

    @Inject
    public RealPayment(
            CreditService creditService,
            @Assisted Date startDate,
            @Assisted BigDecimal amount) {
        this.creditService = creditService;
        this.startDate = startDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return startDate + " for " + amount;
    }
}
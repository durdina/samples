package com.personal.durdina.di.knight.guice;

import java.math.BigDecimal;
import java.util.Date;

public interface PaymentFactory {
  Payment create(Date startDate, BigDecimal amount);
}
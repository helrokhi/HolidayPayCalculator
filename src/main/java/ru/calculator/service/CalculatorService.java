package ru.calculator.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface CalculatorService {
    //Среднемесячное число календарных дней для расчета отпускных
    BigDecimal MONTHLY_AVERAGE_NUMBER_OF_CALENDAR_DAYS = new BigDecimal("29.3");

    String getVacationPayAmount(
            Double averageSalary,
            Integer numberOfVacationDays,
            OffsetDateTime dateOfLeavingForVacation,
            OffsetDateTime dateOfReturnToWork);
}

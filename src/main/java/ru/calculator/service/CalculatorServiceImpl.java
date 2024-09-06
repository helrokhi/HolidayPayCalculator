package ru.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    //Среднемесячное число календарных дней для расчета отпускных
    private static final BigDecimal MONTHLY_AVERAGE_NUMBER_OF_CALENDAR_DAYS = new BigDecimal("29.3");

    @Override
    public String getResult(Double salary, Integer days) {
        return getVacationPay(getAverageDailyEarnings(salary), days);
    }

    private BigDecimal getAverageDailyEarnings(Double salary) {
        //Расчет среднего дневного заработка для оплаты отпуска
        BigDecimal averageDailyEarnings = new BigDecimal(salary)
                .divide(MONTHLY_AVERAGE_NUMBER_OF_CALENDAR_DAYS, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        log.info("get Average Daily Earnings {}", averageDailyEarnings.toEngineeringString());
        return averageDailyEarnings;
    }

    private String getVacationPay(BigDecimal averageDailyEarnings, Integer days) {
        // Расчет размера отпускных выплат
        BigDecimal vacationPay = averageDailyEarnings
                .multiply(new BigDecimal(days))
                .setScale(2, RoundingMode.HALF_UP);
        log.info("get Vacation Pay {}", vacationPay.toEngineeringString());
        return vacationPay.toEngineeringString();
    }
}

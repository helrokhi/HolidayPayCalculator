package ru.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public String getVacationPayAmount(Double averageSalary,
                                       Integer numberOfVacationDays,
                                       OffsetDateTime dateOfLeavingForVacation,
                                       OffsetDateTime dateOfReturnToWork) {
        if (numberOfVacationDays == 0) {
            numberOfVacationDays = getNumberOfDaysBetweenDates(dateOfReturnToWork,
                    dateOfLeavingForVacation);
        }

        return getVacationPay(getAverageDailyEarnings(averageSalary), numberOfVacationDays);
    }

    private BigDecimal getAverageDailyEarnings(Double averageSalary) {
        //Расчет среднего дневного заработка для оплаты отпуска
        BigDecimal averageDailyEarnings = new BigDecimal(averageSalary)
                .divide(MONTHLY_AVERAGE_NUMBER_OF_CALENDAR_DAYS, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        log.info("get Average Daily Earnings {}", averageDailyEarnings.toEngineeringString());
        return averageDailyEarnings;
    }

    private String getVacationPay(BigDecimal averageDailyEarnings, Integer numberOfVacationDays) {
        // Расчет размера отпускных выплат
        BigDecimal vacationPay = averageDailyEarnings
                .multiply(new BigDecimal(numberOfVacationDays))
                .setScale(2, RoundingMode.HALF_UP);
        log.info("get Vacation Pay {}", vacationPay.toEngineeringString());
        return vacationPay.toEngineeringString();
    }

    private Integer getNumberOfDaysBetweenDates(OffsetDateTime dateOfLeavingForVacation,
                                                OffsetDateTime dateOfReturnToWork) {
        long numberOfDaysBetweenDates = ChronoUnit.DAYS
                .between(dateOfReturnToWork, dateOfLeavingForVacation);
        log.info("get Number Of Days Between Dates {}", numberOfDaysBetweenDates);
        return Math.toIntExact(numberOfDaysBetweenDates);
    }
}

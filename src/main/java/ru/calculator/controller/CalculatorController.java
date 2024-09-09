package ru.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.calculator.service.CalculatorService;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/calculacte")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<String> getVacationPayAmount(
            @RequestParam("salary") Double salary,
            @RequestParam(name = "days", required = false, defaultValue = "0") Integer days,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTo
            ) {

        log.info("get result by average salary {}; number of vacation days {} {} {}", salary, days, dateFrom, dateTo);

        try {
            String result = calculatorService.getVacationPayAmount(salary, days, dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

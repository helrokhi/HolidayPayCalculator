package ru.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.calculator.service.CalculatorService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/calculacte")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<String> getResult(
            @RequestParam("salary") Double salary,
            @RequestParam ("days") Integer days) {

        log.info("get result by average salary {}; number of vacation days {}", salary, days);

        try {
            String result = calculatorService.getResult(salary, days);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

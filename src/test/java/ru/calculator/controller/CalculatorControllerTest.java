package ru.calculator.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.calculator.service.CalculatorService;

import java.time.OffsetDateTime;
@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getVacationPayAmountByDateOf() throws Exception {
        OffsetDateTime dateOfLeavingForVacation =
                OffsetDateTime.parse("2024-08-13T16:00:05.829402900Z");
        OffsetDateTime dateOfReturnToWork =
                OffsetDateTime.parse("2024-08-14T16:00:05.829402900Z");

        Double averageSalary = 41809.99;

        String vacationPayAmount = "1426.96";

        Mockito.when(calculatorService.getVacationPayAmount(
                        averageSalary,
                        0,
                        dateOfLeavingForVacation,
                        dateOfReturnToWork))
                .thenReturn(vacationPayAmount);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/calculacte")
                                .param("salary", String.valueOf(averageSalary))
                                .param("days", "0")
                                .param("dateFrom", String.valueOf(dateOfLeavingForVacation))
                                .param("dateTo", String.valueOf(dateOfReturnToWork))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(vacationPayAmount));


        Mockito.verify(calculatorService, Mockito.times(1))
                .getVacationPayAmount(averageSalary,
                        0,
                        dateOfLeavingForVacation,
                        dateOfReturnToWork);

    }

    @Test
    void getVacationPayAmountByDays() throws Exception {

        Double averageSalary = 41809.99;

        Integer numberOfVacationDays = 1;

        String vacationPayAmount = "1426.96";

        Mockito.when(calculatorService.getVacationPayAmount(
                        averageSalary,
                        numberOfVacationDays,
                        null,
                        null))
                .thenReturn(vacationPayAmount);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/calculacte")
                                .param("salary", String.valueOf(averageSalary))
                                .param("days", String.valueOf(numberOfVacationDays))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(vacationPayAmount));


        Mockito.verify(calculatorService, Mockito.times(1))
                .getVacationPayAmount(averageSalary,
                        numberOfVacationDays,
                        null,
                        null);

    }
}
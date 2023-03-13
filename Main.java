package com.project;

import com.project.model.InputData;
import com.project.model.Overpayment;
import com.project.model.RateType;
import com.project.service.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        Map<Integer, BigDecimal> overpaymentSchema = Map.of(
                5, BigDecimal.valueOf(10000),
                6, BigDecimal.valueOf(10000),
                7, BigDecimal.valueOf(10000),
                8, BigDecimal.valueOf(10000),
                9, BigDecimal.valueOf(10000),
                10, BigDecimal.valueOf(10000)
        );
        Map<Integer, BigDecimal> overpaymentSchema2 = Map.of(
                15, BigDecimal.valueOf(10000),
                16, BigDecimal.valueOf(10000),
                17, BigDecimal.valueOf(10000),
                18, BigDecimal.valueOf(10000),
                19, BigDecimal.valueOf(10000),
                20, BigDecimal.valueOf(10000)
        );
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("2700000"))
                .withRateType(RateType.DECREASING)
                .withMonthsDuration(BigDecimal.valueOf(360))
                .withOverpaymentSchema(Collections.emptyMap())
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);

        PrintingService printingService = new PrintingServiceImpl();

        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountCalculationServiceImpl(),
                        new DecreasingAmountCalculationServiceImpl()
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create());
        mortgageCalculationService.calculate(inputData);

    }
}
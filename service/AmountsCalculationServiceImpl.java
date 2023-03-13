package com.project.service;

import com.project.model.InputData;
import com.project.model.Overpayment;
import com.project.model.Rate;
import com.project.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private final ConstantAmountCalculationService constantAmountCalculationService;

    private final DecreasingAmountCalculationService decreasingAmountCalculationService;

    public AmountsCalculationServiceImpl(
            ConstantAmountCalculationService constantAmountCalculationService,
            DecreasingAmountCalculationService decreasingAmountCalculationService
    ) {
        this.constantAmountCalculationService = constantAmountCalculationService;
        this.decreasingAmountCalculationService = decreasingAmountCalculationService;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountCalculationService.calculate(inputData, overpayment);
            default:
                throw new MortgageException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
            switch (inputData.getRateType()) {
                case CONSTANT:
                    return constantAmountCalculationService.calculate(inputData,overpayment, previousRate);
                case DECREASING:
                    return decreasingAmountCalculationService.calculate(inputData,overpayment, previousRate);
                default:
                    throw new MortgageException();
            }
        }





}

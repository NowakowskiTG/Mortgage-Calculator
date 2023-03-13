package com.project.service;

import com.project.model.InputData;
import com.project.model.Overpayment;
import com.project.model.Rate;
import com.project.model.RateAmounts;

public interface ConstantAmountCalculationService {
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}

package com.project.service;

import com.project.model.InputData;
import com.project.model.MortgageResidual;
import com.project.model.Rate;
import com.project.model.RateAmounts;

public interface ResidualCalculationService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);
    MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}

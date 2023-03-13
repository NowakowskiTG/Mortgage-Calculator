package com.project.service;

import com.project.model.InputData;
import com.project.model.MortgageReference;
import com.project.model.Rate;
import com.project.model.RateAmounts;

public interface ReferenceCalculationService {

    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}

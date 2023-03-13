package com.project.service;

import com.project.model.InputData;
import com.project.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {

    Overpayment calculate(BigDecimal rateNumber, InputData inputData);
}

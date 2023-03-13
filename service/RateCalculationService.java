package com.project.service;

import com.project.model.InputData;
import com.project.model.Rate;

import java.util.List;

public interface RateCalculationService {

    List<Rate> calculate(final InputData inputData);
}

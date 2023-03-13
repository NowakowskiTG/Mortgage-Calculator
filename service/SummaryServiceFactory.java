package com.project.service;

import com.project.model.Rate;
import com.project.model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getInterestAmount()
            );
            BigDecimal provisions = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount()
            );
            BigDecimal totalLost = interestSum.add(provisions);
            return new Summary(interestSum, provisions, totalLost);
        };
    }

    private static BigDecimal calculate(List<Rate> rates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Rate rate : rates) {
//            sum = sum.add(rate.getRateAmounts().getInterestAmount());
//            sum = sum.add(rate.getRateAmounts().getOverpayment().getProvisionAmount());
            sum = sum.add(function.calculate(rate));
        }
        return sum;
    }

}

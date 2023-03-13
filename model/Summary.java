package com.project.model;

import java.math.BigDecimal;

public class Summary {

    private final BigDecimal interestSum;

    private final BigDecimal overpaymentProvisionSum;

    private final BigDecimal totalLost;

    public Summary(BigDecimal interestSum, BigDecimal overpaymentProvisionSum, BigDecimal totalLost) {
        this.interestSum = interestSum;
        this.overpaymentProvisionSum = overpaymentProvisionSum;
        this.totalLost = totalLost;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public BigDecimal getOverpaymentProvisionSum() {
        return overpaymentProvisionSum;
    }

    public BigDecimal getTotalLost() {
        return totalLost;
    }
}

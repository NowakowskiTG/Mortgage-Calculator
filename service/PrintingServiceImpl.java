package com.project.service;

import com.project.model.InputData;
import com.project.model.Overpayment;
import com.project.model.Rate;
import com.project.model.Summary;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {
    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        Optional.ofNullable(inputData.getOverpaymentSchema())
                        .filter(schema -> schema.size() > 0)
                        .ifPresent(schema -> logOverpayment(msg, inputData));

        printMessage(msg.toString());
    }

    private void logOverpayment(StringBuilder msg, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();
        }

        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpaymentSchema());
        msg.append(NEW_LINE);

    }

    @Override
    public void printRates(List<Rate> rates) {
        String format = "%3s %3s |" +
                "%7s %2s |" +
                "%5s %2s |" +
                "%5s %2s |" +
                "%5s %7s |" +
                "%7s %7s |" +
                "%5s %5s |" +
                "%5s %8s |" +
                "%5s %8s |" +
                "%5s %2s |";

        for (Rate rate : rates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                    LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration()
                    );
            printMessage(message);

            if (rate.getRateNumber().intValue() % 12 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisionSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(summary.getTotalLost()).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(msg.toString());
    }

    private void printMessage(String sb) {
        System.out.println(sb);
    }
}

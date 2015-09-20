package com.blocksolid.income;

/**
 * Created by Dan Buckland on 16/09/2015.
 */

public class TaxCalculator {

    int MAX_PERSONAL_ALLOWANCE = 1060000;           // £10,600
    int PERSONAL_ALLOWANCE_THRESHOLD = 10000000;    // £100,000
    int BASIC_RATE_THRESHOLD = 3178500;             // £31,785
    int HIGHER_RATE_THRESHOLD = 15000000;           // £150,000

    int annualIncome;
    int personalAllowance;

    public TaxCalculator(int annualIncome) {
        this.annualIncome = annualIncome;
        calculatePersonalAllowance();
        //calculateDeductions(personalAllowance);
    }

    public void setAnnualIncome(int newAnnualIncome) {
        annualIncome = newAnnualIncome;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public int calculatePersonalAllowance() {
        if (annualIncome > PERSONAL_ALLOWANCE_THRESHOLD) {
            int difference = annualIncome - PERSONAL_ALLOWANCE_THRESHOLD;
            if (difference % 200 > 0) {
                difference = difference - 100;
            }
            personalAllowance = MAX_PERSONAL_ALLOWANCE - (difference / 2);
            if (personalAllowance < 0) {
                personalAllowance = 0;
            }
        } else {
            personalAllowance = MAX_PERSONAL_ALLOWANCE;
        }
        return personalAllowance;
    }

}
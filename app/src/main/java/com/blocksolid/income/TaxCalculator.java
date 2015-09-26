package com.blocksolid.income;

public class TaxCalculator {

    int MAX_PERSONAL_ALLOWANCE = 1060000;           // £10,600
    int PERSONAL_ALLOWANCE_THRESHOLD = 10000000;    // £100,000
    int BASIC_RATE_THRESHOLD = 3178500;             // £31,785
    int HIGHER_RATE_THRESHOLD = 15000000;           // £150,000
    double BASIC_RATE = 0.2;                        // 20%
    double HIGHER_RATE = 0.4;                       // 40%
    double ADDITIONAL_RATE = 0.45;                  // 45%

    int grossAnnualIncome = 0;
    int personalAllowance = MAX_PERSONAL_ALLOWANCE;
    int basicRateDeduction = 0;
    int higherRateDeduction = 0;
    int additionalRateDeduction = 0;
    int nationalInsuranceContribution = 0;
    int totalDeductions = 0;
    int netAnnualIncome = 0;

    public TaxCalculator() {

    }

    public void setGrossAnnualIncome(int newGrossAnnualIncome) {
        grossAnnualIncome = newGrossAnnualIncome;
        calculateTotalTaxDeductions(grossAnnualIncome);
    }

    public int getGrossAnnualIncome() {
        return grossAnnualIncome;
    }

    public int calculatePersonalAllowance(int grossAnnualIncome) {

        /**
         * The standard Personal Allowance is £10,600, which is the amount of income that tax will
         * not be paid on.
         *
         * A persons Personal Allowance may be bigger if they were born before 6 April 1938 or if
         * they get Blind Person’s Allowance.
         *
         * The Personal Allowance goes down by £1 for every £2 that the adjusted net income is
         * above £100,000. This means the allowance is zero if a persons income is £121,200 or over.
         */

        if (grossAnnualIncome > PERSONAL_ALLOWANCE_THRESHOLD) {
            int difference = grossAnnualIncome - PERSONAL_ALLOWANCE_THRESHOLD;
            // £1 is removed for each £2 earned above the personal allowance
            // Remove the odd pound so that the difference can be divided by 2
            if (difference % 200 > 0) {
                difference = difference - 100;
            }
            personalAllowance = MAX_PERSONAL_ALLOWANCE - (difference / 2);
            // If the calculation results in a negative number, set personal allowance to 0
            if (personalAllowance < 0) {
                personalAllowance = 0;
            }
        } else {
            // Personal allowance set to the maximum if salary is below the threshold
            personalAllowance = MAX_PERSONAL_ALLOWANCE;
        }
        return personalAllowance;
    }

}
package com.blocksolid.income;

/**
 * Created by Dan Buckland on 16/09/2015.
 */

public class TaxCalculator {

    int MAX_PERSONAL_ALLOWANCE = 1060000;           // £10,600
    int PERSONAL_ALLOWANCE_THRESHOLD = 10000000;    // £100,000
    int BASIC_RATE_THRESHOLD = 3178500;             // £31,785
    int HIGHER_RATE_THRESHOLD = 15000000;           // £150,000

    int annualIncome = 0;
    int personalAllowance = MAX_PERSONAL_ALLOWANCE;

    public TaxCalculator() {

    }

    public void setAnnualIncome(int newAnnualIncome) {
        annualIncome = newAnnualIncome;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public int calculatePersonalAllowance(int annualIncome) {

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

        if (annualIncome > PERSONAL_ALLOWANCE_THRESHOLD) {
            int difference = annualIncome - PERSONAL_ALLOWANCE_THRESHOLD;
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
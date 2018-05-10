package com.blocksolid.income;

public class TaxCalculator {

    // 2015-16 Tax constants
    private static final int MAX_PERSONAL_ALLOWANCE = 1185000;           // £11,850
    private static final int PERSONAL_ALLOWANCE_THRESHOLD = 10000000;    // £100,000
    private static final int TAX_BASIC_RATE_THRESHOLD = 3450000;         // £34,500
    private static final int TAX_HIGHER_RATE_THRESHOLD = 15000000;       // £150,000
    private static final double TAX_BASIC_RATE = 0.2;                    // 20%
    private static final double TAX_HIGHER_RATE = 0.4;                   // 40%
    private static final double TAX_ADDITIONAL_RATE = 0.45;              // 45%

    // 2015-16 National Insurance constants
    // All values and calculations assume National Insurance Category A
    private static final int LEL_NI = 582400;    // £5,824     Lower Earnings Limit
    private static final int PT_NI = 806000;     // £8,060     Primary Threshold
    private static final int ST_NI = 811200;     // £8,112     Secondary Threshold
    private static final int UAP_NI = 4004000;   // £40,040    Upper Accrual Point
    private static final int UST_NI = 4238500;   // £42,385    Upper Secondary Threshold (under 21)
    private static final int UEL_NI = 4238500;   // £42,385    Upper Earnings Limit
    private static final double LOWER_RATE_NI = 0.02;    //  2.00%
    private static final double MIDDLE_RATE_NI = 0.0585; //  5.85%
    private static final double UPPER_RATE_NI = 0.106;   // 10.60%
    private static final double TOP_RATE_NI = 0.12;      // 12.00%

    int grossIncome = 0;
    int totalDeductions = 0;
    int netIncome = 0;

    // Tax
    int personalAllowance = MAX_PERSONAL_ALLOWANCE;
    int basicRateDeduction = 0;
    int higherRateDeduction = 0;
    int additionalRateDeduction = 0;
    int totalTaxDeductions = 0;

    // National Insurance
    int contributionBetweenLelAndPt = 0;
    int contributionBetweenPtAndUap = 0;
    int contributionBetweenUapAndUel = 0;
    int contributionAboveUel = 0;
    int nationalInsuranceContributions = 0;

    public TaxCalculator() {

    }

    public void setGrossIncome(int newGrossIncome) {
        grossIncome = newGrossIncome;
        // All calculations should be done each time the salary is set
        calculateNetIncome();
    }

    public int calculatePersonalAllowance() {
        if (grossIncome <= PERSONAL_ALLOWANCE_THRESHOLD) {
            // Personal allowance set to the maximum if salary is below the threshold
            return personalAllowance = MAX_PERSONAL_ALLOWANCE;
        } else {
            // £1 is removed for each £2 earned above the personal allowance
            int difference = grossIncome - PERSONAL_ALLOWANCE_THRESHOLD;
            if (difference % 200 > 0) {
                // Remove the odd pound so that the difference can be divided by 2
                difference = difference - 100;
            }
            personalAllowance = MAX_PERSONAL_ALLOWANCE - (difference / 2);
            // If the calculation results in a negative number, set personal allowance to 0
            if (personalAllowance < 0) {
                personalAllowance = 0;
            }
        }
        return personalAllowance;
    }

    public int calculateTotalDeductions() {
        totalDeductions = calculateTotalTaxDeductions() + calculateNationalInsuranceContributions();
        return totalDeductions;
    }

    public int calculateTotalTaxDeductions() {
        // Important to reset these to 0 in case getter methods are added later for these
        basicRateDeduction = 0;
        higherRateDeduction = 0;
        additionalRateDeduction = 0;
        personalAllowance = calculatePersonalAllowance();

        if (grossIncome <= personalAllowance) {
            return totalTaxDeductions = 0;
        }

        // Calculation basic rate tax deduction
        int assessedBasicRateThreshold = TAX_BASIC_RATE_THRESHOLD + personalAllowance;
        if (grossIncome <= assessedBasicRateThreshold) {
            basicRateDeduction = (int)(TAX_BASIC_RATE * (grossIncome - personalAllowance));
            return totalTaxDeductions = basicRateDeduction;
        } else {
            basicRateDeduction = (int)(TAX_BASIC_RATE
                    * (assessedBasicRateThreshold - personalAllowance));
        }

        // Calculate higher rate tax deduction
        if (grossIncome <= TAX_HIGHER_RATE_THRESHOLD) {
            higherRateDeduction = (int)(TAX_HIGHER_RATE
                    * (grossIncome - assessedBasicRateThreshold));
            return totalTaxDeductions = basicRateDeduction + higherRateDeduction;
        } else {
            higherRateDeduction = (int)(TAX_HIGHER_RATE
                    * (TAX_HIGHER_RATE_THRESHOLD - assessedBasicRateThreshold));
        }

        // Calculate additional rate tax deduction
        additionalRateDeduction = (int)(TAX_ADDITIONAL_RATE
                * (grossIncome - TAX_HIGHER_RATE_THRESHOLD));
        return totalTaxDeductions = basicRateDeduction
                                    + higherRateDeduction
                                    + additionalRateDeduction;
    }

    public int calculateNationalInsuranceContributions() {
        // Important to reset these to 0 here for future getter methods
        contributionBetweenLelAndPt = 0;
        contributionBetweenPtAndUap = 0;
        contributionBetweenUapAndUel = 0;
        contributionAboveUel = 0;

        if (grossIncome <= LEL_NI) {
            return nationalInsuranceContributions = 0;
        }

        // Calculate earnings between LEL_NI and PT_NI and multiply by 0%
        if (grossIncome <= PT_NI) {
            contributionBetweenLelAndPt = (grossIncome - LEL_NI) * 0;
            return nationalInsuranceContributions = contributionBetweenLelAndPt;
        } else {
            contributionBetweenLelAndPt = (PT_NI - LEL_NI)*0;
        }

        // Calculate earnings between PT_NI and UAP_NI and multiply by TOP_RATE_NI %
        if (grossIncome <= UAP_NI) {
            contributionBetweenPtAndUap = (int)((grossIncome - PT_NI) * TOP_RATE_NI);
            return nationalInsuranceContributions = contributionBetweenLelAndPt
                                                    + contributionBetweenPtAndUap;
        } else {
            contributionBetweenPtAndUap = (int)((UAP_NI - PT_NI) * TOP_RATE_NI);
        }

        // Calculate earnings between UAP and UEL and multiply by TOP_RATE_NI %
        if (grossIncome <= UEL_NI) {
            contributionBetweenUapAndUel = (int)((grossIncome - UAP_NI) * TOP_RATE_NI);
            return nationalInsuranceContributions = contributionBetweenLelAndPt
                                                    + contributionBetweenPtAndUap
                                                    + contributionBetweenUapAndUel;
        } else {
            contributionBetweenUapAndUel = (int)((UEL_NI - UAP_NI) * TOP_RATE_NI);
        }

        // Calculate earnings above UEL and multiply by LOWER_RATE_NI %
        contributionAboveUel = (int)((grossIncome - UEL_NI) * LOWER_RATE_NI);

        // Add all the values together and return
        return nationalInsuranceContributions = contributionBetweenLelAndPt
                                                + contributionBetweenPtAndUap
                                                + contributionBetweenUapAndUel
                                                + contributionAboveUel;
    }

    public void calculateNetIncome() {
        totalDeductions = calculateTotalDeductions();
        netIncome = grossIncome - totalDeductions;
    }

    public int getAnnualGrossIncome() {
        return grossIncome;
    }

    public int getAnnualPersonalAllowance() {
        return personalAllowance;
    }

    public int getAnnualTotalTaxDeductions() {
        return totalTaxDeductions;
    }

    public int getAnnualNiContributions() {
        return nationalInsuranceContributions;
    }

    public int getAnnualTotalDeductions() {
        return totalDeductions;
    }

    public int getAnnualNetIncome() {
        return netIncome;
    }

    public int monthly(int value) {
        double result = value / 12.0;
        return (int) (Math.round(result));
    }

    public int getMonthlyGrossIncome() {
        return monthly(grossIncome);
    }

    public int getMonthlyPersonalAllowance() {
        return monthly(personalAllowance);
    }

    public int getMonthlyTotalTaxDeductions() {
        return monthly(totalTaxDeductions);
    }

    public int getMonthlyNiContributions() {
        return monthly(nationalInsuranceContributions);
    }

    public int getMonthlyTotalDeductions() {
        return monthly(totalDeductions);
    }

    public int getMonthlyNetIncome() {
        return monthly(netIncome);
    }

    public int weekly(int value) {
        double result = value / 52.0;
        return (int) (Math.round(result));
    }

    public int getWeeklyGrossIncome() {
        return weekly(grossIncome);
    }

    public int getWeeklyPersonalAllowance() {
        return weekly(personalAllowance);
    }

    public int getWeeklyTotalTaxDeductions() {
        return weekly(totalTaxDeductions);
    }

    public int getWeeklyNiContributions() {
        return weekly(nationalInsuranceContributions);
    }

    public int getWeeklyTotalDeductions() {
        return weekly(totalDeductions);
    }

    public int getWeeklyNetIncome() {
        return weekly(netIncome);
    }


}
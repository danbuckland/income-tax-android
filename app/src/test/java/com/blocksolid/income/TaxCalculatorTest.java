package com.blocksolid.income;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    TaxCalculator taxCalculator = new TaxCalculator();

    // Tests for calculatePersonalAllowance method

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf0() {
        // The personal allowance for a salary of £0 is the maximum £11,850
        taxCalculator.setGrossIncome(0);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1185000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf100001() {
        // The personal allowance for a salary of £100,001 is the maximum £11,850
        taxCalculator.setGrossIncome(10000100);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1185000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf100002() {
        // £1 is removed from the personal allowance for every £2 over £100,000
        // The personal allowance for a salary of £100,002 should be £11,849
        taxCalculator.setGrossIncome(10000200);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1184900, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf123699() {
        // The personal allowance for a salary of £123,699 should be £1
        taxCalculator.setGrossIncome(12369900);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(100, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf123700() {
        // There is no personal allowance for a salary of £123,700 or over
        taxCalculator.setGrossIncome(12370000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf1000000() {
        // There is no personal allowance for a salary of £1,000,000
        // This checks that it's not possible to have a negative personal allowance
        taxCalculator.setGrossIncome(100000000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf9999999() {
        // There is no personal allowance for a salary of £9,999,999
        // This checks that it's not possible to have a negative personal allowance
        taxCalculator.setGrossIncome(999999900);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForNegativeGrossAnnualIncome() {
        // Checking that a negative annual income returns the maximum personal allowance
        taxCalculator.setGrossIncome(-1186000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1185000, result);
    }


    // Tests for calculateTotalTaxDeductions method

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf11850() {
        // A salary of £11,850 should pay no tax
        taxCalculator.setGrossIncome(1185000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf10601() throws Exception {
        // A salary of £10,601 should pay tax of 20p
        taxCalculator.setGrossIncome(1060100);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(20, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf40000() throws Exception {
        // A salary of £40,000 should pay tax of £5,880
        taxCalculator.setGrossIncome(4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(588000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf42385() throws Exception {
        // A salary of £42,385 should pay tax of £6,357
        taxCalculator.setGrossIncome(4238500);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(635700, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf42386() throws Exception {
        // A salary of £42,386 should pay tax of £6,357.40
        // £6,357 at the Basic rate 20%
        //    40p at the Higher rate 40%
        taxCalculator.setGrossIncome(4238600);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(635740, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf100000() throws Exception {
        // A salary of £100,000 should pay tax of £29,403
        //  £6,357 at the Basic rate 20%
        // £23,046 at the Higher rate 40%
        taxCalculator.setGrossIncome(10000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(2940300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf150000() throws Exception {
        // A salary of £150,000 should pay tax of £53,643
        //  £6,357 at the Basic rate 20%
        // £47,286 at the Higher rate 40%
        taxCalculator.setGrossIncome(15000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5364300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf150001() throws Exception {
        // A salary of £150,001 should pay tax of £53,643.45
        //  £6,357 at the Basic rate 20%
        // £47,286 at the Higher rate 40%
        //   £0.45 at the Additional rate 45%
        taxCalculator.setGrossIncome(15000100);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5364345, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf160000() throws Exception {
        // A salary of £160,000 should pay tax of £58,143
        //  £6,357 at the Basic rate 20%
        // £47,286 at the Higher rate 40%
        //  £4,500 at the Additional rate 45%
        taxCalculator.setGrossIncome(16000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5814300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf1000000() throws Exception {
        // A salary of £1,000,000 should pay tax of £436,143
        //   £6,357 at the Basic rate 20%
        //  £47,286 at the Higher rate 40%
        // £382,500 at the Additional rate 45%
        taxCalculator.setGrossIncome(100000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(43614300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf9999999() throws Exception {
        // A salary of £9,999,999 should pay tax of £4,486,142.55
        //     £6,357.00 at the Basic rate 20%
        //    £47,286.00 at the Higher rate 40%
        // £4,432,499.55 at the Additional rate 45%
        taxCalculator.setGrossIncome(999999900);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(448614255, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf0() throws Exception {
        // A salary of £0 should pay no tax
        taxCalculator.setGrossIncome(0);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }

    @Test
    public void testTaxDeductionCalculationForNegativeGrossAnnualIncome() throws Exception {
        // Checking that a negative annual income returns 0 deductions
        taxCalculator.setGrossIncome(-4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }


    // Tests for calculateNationalInsuranceContributions method

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf0() throws Exception {
        // A salary of £0 should pay no National Insurance
        taxCalculator.setGrossIncome(0);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf5823() throws Exception {
        // An annual income of £5,823 should pay no National Insurance
        taxCalculator.setGrossIncome(582300);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf5824() throws Exception {
        // An annual income of £5,824 should pay no National Insurance
        taxCalculator.setGrossIncome(582400);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf5825() throws Exception {
        // An annual income of £5,825 should pay no National Insurance
        taxCalculator.setGrossIncome(582500);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8059() throws Exception {
        // An annual income of £8,059 should pay no National Insurance
        taxCalculator.setGrossIncome(805900);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8060() throws Exception {
        // An annual income of £8,060 should pay no National Insurance
        taxCalculator.setGrossIncome(806000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8061() throws Exception {
        // An annual income of £8,061 should contribute £0.12 towards National Insurance
        taxCalculator.setGrossIncome(806100);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(12, result);
    }

    @Test
     public void testNationalInsuranceCalculationForGrossAnnualIncomeOf40000() throws Exception {
        // An annual income of £40,000 should contribute £3,832.80 towards National Insurance
        // £3,832.80 at the Top rate 12%
        taxCalculator.setGrossIncome(4000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(383280, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf40040() throws Exception {
        // An annual income of £40,040 should contribute £3,837.60 towards National Insurance
        // £3,837.60 at the Top rate 12%
        taxCalculator.setGrossIncome(4004000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(383760, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf40041() throws Exception {
        // An annual income of £40,040 should contribute £3,387.72 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //     £0.12 at the Top rate 12%
        taxCalculator.setGrossIncome(4004100);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(383772, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf41697() throws Exception {
        // An annual income of £41,697 should contribute £4,036.44 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //   £198.84 at the Top rate 12%
        taxCalculator.setGrossIncome(4169700);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(403644, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf42384() throws Exception {
        // An annual income of £42,384 should contribute £4,118.88 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //   £281.28 at the Top rate 12%
        //     £0.00 at the Lower rate 2%
        taxCalculator.setGrossIncome(4238400);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(411888, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf42385() throws Exception {
        // An annual income of £42,385 should contribute £4,119.00 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //   £281.40 at the Top rate 12%
        //     £0.00 at the Lower rate 2%
        taxCalculator.setGrossIncome(4238500);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(411900, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf42386() throws Exception {
        // An annual income of £42,386 should contribute £4,119.02 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //   £281.40 at the Top rate 12%
        //     £0.02 at the Lower rate 2%
        taxCalculator.setGrossIncome(4238600);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(411902, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf100000() throws Exception {
        // An annual income of £100,000 should contribute £5,271.30 towards National Insurance
        // £3,837.60 at the Top rate 12%
        //   £281.40 at the Top rate 12%
        // £1,152.30 at the Lower rate 2%
        taxCalculator.setGrossIncome(10000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(527130, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf2000000() throws Exception {
        // An annual income of £2,000,000 should contribute £43,271.30 towards National Insurance
        //  £3,837.60 at the Top rate 12%
        //    £281.40 at the Top rate 12%
        // £39,152.30 at the Lower rate 2%
        taxCalculator.setGrossIncome(200000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(4327130, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf9999999() throws Exception {
        // An annual income of £9,999,999 should contribute £203,271.28 towards National Insurance
        //   £3,837.60 at the Top rate 12%
        //     £281.40 at the Top rate 12%
        // £199,152.28 at the Lower rate 2%
        taxCalculator.setGrossIncome(999999900);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(20327128, result);
    }

    @Test
    public void testNationalInsuranceCalculationForNegativeGrossAnnualIncome() throws Exception {
        // Checking that a negative annual income returns 0 deductions
        taxCalculator.setGrossIncome(-4005000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }


    // Tests for monthly divider method

    @Test
    public void testMonthlyDivideWithNoRemainders() throws Exception {
        int result = taxCalculator.monthly(1200);
        assertEquals(100, result);
    }

    @Test
    public void testMonthlyDivideRoundingUp() throws Exception {
        int result = taxCalculator.monthly(1206);
        assertEquals(101, result);
    }

    @Test
    public void testMonthlyDivideRoundingDown() throws Exception {
        int result = taxCalculator.monthly(1205);
        assertEquals(100, result);
    }

    @Test
    public void testMonthlyDivide0() throws Exception {
        int result = taxCalculator.monthly(0);
        assertEquals(0, result);
    }

    @Test
    public void testMonthlyDivideNegativeNumber() throws Exception {
        int result = taxCalculator.monthly(-1206);
        assertEquals(-100, result);
    }


    // Tests for weekly divider method

    @Test
    public void testWeeklyDivideWithNoRemainders() throws Exception {
        int result = taxCalculator.weekly(5200);
        assertEquals(100, result);
    }

    @Test
    public void testWeeklyDivideRoundingUp() throws Exception {
        int result = taxCalculator.weekly(5226);
        assertEquals(101, result);
    }

    @Test
    public void testWeeklyDivideRoundingDown() throws Exception {
        int result = taxCalculator.weekly(5225);
        assertEquals(100, result);
    }

    @Test
    public void testWeeklyDivide0() throws Exception {
        int result = taxCalculator.weekly(0);
        assertEquals(0, result);
    }

    @Test
    public void testWeeklyDivideNegativeNumber() throws Exception {
        int result = taxCalculator.weekly(-5226);
        assertEquals(-100, result);
    }
}
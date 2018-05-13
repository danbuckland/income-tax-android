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
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf11851() {
        // A salary of £11,851 should pay tax of 20p
        taxCalculator.setGrossIncome(1185100);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(20, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf40000() {
        // A salary of £40,000 should pay tax of £5,630
        taxCalculator.setGrossIncome(4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(563000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf46350() {
        // A salary of £46,350 should pay tax of £6,900
        taxCalculator.setGrossIncome(4635000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(690000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf46351() {
        // A salary of £46,351 should pay tax of £6,900.40
        // £6,900 at the Basic rate 20%
        //    40p at the Higher rate 40%
        taxCalculator.setGrossIncome(4635100);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(690040, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf100000() {
        // A salary of £100,000 should pay tax of £28,360
        //  £6,900 at the Basic rate 20%
        // £21,460 at the Higher rate 40%
        taxCalculator.setGrossIncome(10000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(2836000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf150000() {
        // A salary of £150,000 should pay tax of £53,100
        //  £6,900 at the Basic rate 20%
        // £46,200 at the Higher rate 40%
        taxCalculator.setGrossIncome(15000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5310000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf150001() {
        // A salary of £150,001 should pay tax of £53,100.45
        //  £6,900 at the Basic rate 20%
        // £46,200 at the Higher rate 40%
        //   £0.45 at the Additional rate 45%
        taxCalculator.setGrossIncome(15000100);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5310045, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf160000() {
        // A salary of £160,000 should pay tax of £57,600
        //  £6,900 at the Basic rate 20%
        // £46,200 at the Higher rate 40%
        //  £4,500 at the Additional rate 45%
        taxCalculator.setGrossIncome(16000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5760000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf1000000() {
        // A salary of £1,000,000 should pay tax of £435600
        //   £6,900 at the Basic rate 20%
        //  £46,200 at the Higher rate 40%
        // £382,500 at the Additional rate 45%
        taxCalculator.setGrossIncome(100000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(43560000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf9999999() {
        // A salary of £9,999,999 should pay tax of £4,485,599.55
        //     £6,900.00 at the Basic rate 20%
        //    £46,200.00 at the Higher rate 40%
        // £4,432,499.55 at the Additional rate 45%
        taxCalculator.setGrossIncome(999999900);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(448559955, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf0() {
        // A salary of £0 should pay no tax
        taxCalculator.setGrossIncome(0);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }

    @Test
    public void testTaxDeductionCalculationForNegativeGrossAnnualIncome() {
        // Checking that a negative annual income returns 0 deductions
        taxCalculator.setGrossIncome(-4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }


    // Tests for calculateNationalInsuranceContributions method

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf0() {
        // A salary of £0 should pay no National Insurance
        taxCalculator.setGrossIncome(0);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf6035() {
        // An annual income of £6,035 should pay no National Insurance
        taxCalculator.setGrossIncome(603500);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf6036() {
        // An annual income of £6,036 should pay no National Insurance
        taxCalculator.setGrossIncome(603600);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf603700() {
        // An annual income of £6,037 should pay no National Insurance
        taxCalculator.setGrossIncome(603700);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8423() {
        // An annual income of £8,423 should pay no National Insurance
        taxCalculator.setGrossIncome(842300);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8424() {
        // An annual income of £8,424 should pay no National Insurance
        taxCalculator.setGrossIncome(842400);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf8425() {
        // An annual income of £8,425 should contribute £0.12 towards National Insurance
        taxCalculator.setGrossIncome(842500);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(12, result);
    }

    @Test
     public void testNationalInsuranceCalculationForGrossAnnualIncomeOf40000() {
        // An annual income of £40,000 should contribute £3,789.12 towards National Insurance
        // £3,789.12 at the Top rate 12%
        taxCalculator.setGrossIncome(4000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(378912, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf46356() {
        // An annual income of £46,356 should contribute £4,551.84 towards National Insurance
        // £4,551.84 at the Top rate 12%
        taxCalculator.setGrossIncome(4635600);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(455184, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf46357() {
        // An annual income of £46,357 should contribute £4,551.86 towards National Insurance
        // £4,551.84 at the Top rate 12%
        //     £0.02 at the Lower rate 2%
        taxCalculator.setGrossIncome(4635700);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(455186, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf100000() {
        // An annual income of £100,000 should contribute £5,624.72 towards National Insurance
        // £4,551.84 at the Top rate 12%
        // £1,072.88 at the Lower rate 2%
        taxCalculator.setGrossIncome(10000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(562472, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf2000000() {
        // An annual income of £2,000,000 should contribute £43,624.72 towards National Insurance
        //  £4,551.84 at the Top rate 12%
        // £39,072.88 at the Lower rate 2%
        taxCalculator.setGrossIncome(200000000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(4362472, result);
    }

    @Test
    public void testNationalInsuranceCalculationForGrossAnnualIncomeOf9999999() {
        // An annual income of £9,999,999 should contribute £203,624.70 towards National Insurance
        //   £4,551.84 at the Top rate 12%
        // £199,072.86 at the Lower rate 2%
        taxCalculator.setGrossIncome(999999900);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(20362470, result);
    }

    @Test
    public void testNationalInsuranceCalculationForNegativeGrossAnnualIncome() {
        // Checking that a negative annual income returns 0 deductions
        taxCalculator.setGrossIncome(-4005000);
        int result = taxCalculator.calculateNationalInsuranceContributions();
        assertEquals(0, result);
    }


    // Tests for monthly divider method

    @Test
    public void testMonthlyDivideWithNoRemainders() {
        int result = taxCalculator.monthly(1200);
        assertEquals(100, result);
    }

    @Test
    public void testMonthlyDivideRoundingUp() {
        int result = taxCalculator.monthly(1206);
        assertEquals(101, result);
    }

    @Test
    public void testMonthlyDivideRoundingDown() {
        int result = taxCalculator.monthly(1205);
        assertEquals(100, result);
    }

    @Test
    public void testMonthlyDivide0() {
        int result = taxCalculator.monthly(0);
        assertEquals(0, result);
    }

    @Test
    public void testMonthlyDivideNegativeNumber() {
        int result = taxCalculator.monthly(-1206);
        assertEquals(-100, result);
    }


    // Tests for weekly divider method

    @Test
    public void testWeeklyDivideWithNoRemainders() {
        int result = taxCalculator.weekly(5200);
        assertEquals(100, result);
    }

    @Test
    public void testWeeklyDivideRoundingUp() {
        int result = taxCalculator.weekly(5226);
        assertEquals(101, result);
    }

    @Test
    public void testWeeklyDivideRoundingDown() {
        int result = taxCalculator.weekly(5225);
        assertEquals(100, result);
    }

    @Test
    public void testWeeklyDivide0() {
        int result = taxCalculator.weekly(0);
        assertEquals(0, result);
    }

    @Test
    public void testWeeklyDivideNegativeNumber() {
        int result = taxCalculator.weekly(-5226);
        assertEquals(-100, result);
    }
}
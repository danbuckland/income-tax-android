package com.blocksolid.income;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    TaxCalculator taxCalculator = new TaxCalculator();

    // Tests for calculatePersonalAllowance method

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf0() throws Exception {
        // The personal allowance for a salary of £0 is the maximum £10,600
        int result = taxCalculator.calculatePersonalAllowance(0);
        assertEquals(1060000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf100001() throws Exception {
        // The personal allowance for a salary of £100,001 is the maximum £10,600
        int result = taxCalculator.calculatePersonalAllowance(10000100);
        assertEquals(1060000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf100002() throws Exception {
        // £1 is removed from the personal allowance for every £2 over £100,000
        // The personal allowance for a salary of £100,002 should be £10,599
        int result = taxCalculator.calculatePersonalAllowance(10000200);
        assertEquals(1059900, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf121199() throws Exception {
        // The personal allowance for a salary of £121,199 should be £1
        int result = taxCalculator.calculatePersonalAllowance(12119900);
        assertEquals(100, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf121200() throws Exception {
        // There is no personal allowance for a salary of £121,200 or over
        int result = taxCalculator.calculatePersonalAllowance(12120000);
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForAnnualIncomeOf1000000() throws Exception {
        // There is no personal allowance for a salary of £1,000,000
        // This checks that it's not possible to have a negative personal allowance
        int result = taxCalculator.calculatePersonalAllowance(100000000);
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForNegativeAnnualIncome() throws Exception {
        // Checking that a negative annual income returns the maximum personal allowance
        int result = taxCalculator.calculatePersonalAllowance(-10500000);
        assertEquals(1060000, result);
    }

    // Tests for calculateTotalTaxDeductions method

    @Test
    public void testTaxDeductionCalculationForAnnualIncomeOf40000() throws Exception {
        // A salary of £40,000 should pay tax of £5,880
        int result = taxCalculator.calculateTotalTaxDeductions(4000000);
        assertEquals(588000, result);
    }

    @Test
    public void testTaxDeductionCalculationForAnnualIncomeOf100000() throws Exception {
        // A salary of £100,000 should pay tax of £29,403
        //  £6,357 at the Basic rate 20%
        // £23,046 at the Higher rate 40%
        int result = taxCalculator.calculateTotalTaxDeductions(10000000);
        assertEquals(2940300, result);
    }

    @Test
    public void testTaxDeductionCalculationForAnnualIncomeOf160000() throws Exception {
        // A salary of £160,000 should pay tax of £58,143
        //  £6,357 at the Basic rate 20%
        // £47,286 at the Higher rate 40%
        //  £4,500 at the Additional rate 45%
        int result = taxCalculator.calculateTotalTaxDeductions(16000000);
        assertEquals(5814300, result);
    }

    @Test
    public void testTaxDeductionCalculationForAnnualIncomeOf1000000() throws Exception {
        // A salary of £1,000,000 should pay tax of £436,143
        //   £6,357 at the Basic rate 20%
        //  £47,286 at the Higher rate 40%
        // £382,500 at the Additional rate 45%
        int result = taxCalculator.calculateTotalTaxDeductions(100000000);
        assertEquals(43614300, result);
    }

    @Test
    public void testTaxDeductionCalculationForAnnualIncomeOf0() throws Exception {
        // A salary of £0 should pay no tax
        int result = taxCalculator.calculateTotalTaxDeductions(0);
        assertEquals(0, result);
    }

    @Test
    public void testTaxDeductionCalculationForNegativeAnnualIncome() throws Exception {
        // Checking that a negative annual income returns 0 deductions
        int result = taxCalculator.calculateTotalTaxDeductions(-4000000);
        assertEquals(0, result);
    }

}
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
        // Checking what happens when the annual income is a negative number
        int result = taxCalculator.calculatePersonalAllowance(-10500000);
        assertEquals(1060000, result);
    }


}
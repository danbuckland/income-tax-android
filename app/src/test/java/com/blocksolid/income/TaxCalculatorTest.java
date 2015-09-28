package com.blocksolid.income;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    TaxCalculator taxCalculator = new TaxCalculator();

    // Tests for calculatePersonalAllowance method

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf0() throws Exception {
        // The personal allowance for a salary of £0 is the maximum £10,600
        taxCalculator.setGrossAnnualIncome(0);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1060000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf100001() throws Exception {
        // The personal allowance for a salary of £100,001 is the maximum £10,600
        taxCalculator.setGrossAnnualIncome(10000100);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1060000, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf100002() throws Exception {
        // £1 is removed from the personal allowance for every £2 over £100,000
        // The personal allowance for a salary of £100,002 should be £10,599
        taxCalculator.setGrossAnnualIncome(10000200);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1059900, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf121199() throws Exception {
        // The personal allowance for a salary of £121,199 should be £1
        taxCalculator.setGrossAnnualIncome(12119900);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(100, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf121200() throws Exception {
        // There is no personal allowance for a salary of £121,200 or over
        taxCalculator.setGrossAnnualIncome(12120000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForGrossAnnualIncomeOf1000000() throws Exception {
        // There is no personal allowance for a salary of £1,000,000
        // This checks that it's not possible to have a negative personal allowance
        taxCalculator.setGrossAnnualIncome(100000000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(0, result);
    }

    @Test
    public void testPersonalAllowanceCalculationForNegativeGrossAnnualIncome() throws Exception {
        // Checking that a negative annual income returns the maximum personal allowance
        taxCalculator.setGrossAnnualIncome(-10500000);
        int result = taxCalculator.calculatePersonalAllowance();
        assertEquals(1060000, result);
    }

    // Tests for calculateTotalTaxDeductions method

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf40000() throws Exception {
        // A salary of £40,000 should pay tax of £5,880
        taxCalculator.setGrossAnnualIncome(4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(588000, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf100000() throws Exception {
        // A salary of £100,000 should pay tax of £29,403
        //  £6,357 at the Basic rate 20%
        // £23,046 at the Higher rate 40%
        taxCalculator.setGrossAnnualIncome(10000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(2940300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf160000() throws Exception {
        // A salary of £160,000 should pay tax of £58,143
        //  £6,357 at the Basic rate 20%
        // £47,286 at the Higher rate 40%
        //  £4,500 at the Additional rate 45%
        taxCalculator.setGrossAnnualIncome(16000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(5814300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf1000000() throws Exception {
        // A salary of £1,000,000 should pay tax of £436,143
        //   £6,357 at the Basic rate 20%
        //  £47,286 at the Higher rate 40%
        // £382,500 at the Additional rate 45%
        taxCalculator.setGrossAnnualIncome(100000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(43614300, result);
    }

    @Test
    public void testTaxDeductionCalculationForGrossAnnualIncomeOf0() throws Exception {
        // A salary of £0 should pay no tax
        taxCalculator.setGrossAnnualIncome(0);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }

    @Test
    public void testTaxDeductionCalculationForNegativeGrossAnnualIncome() throws Exception {
        // Checking that a negative annual income returns 0 deductions
        taxCalculator.setGrossAnnualIncome(-4000000);
        int result = taxCalculator.calculateTotalTaxDeductions();
        assertEquals(0, result);
    }

}
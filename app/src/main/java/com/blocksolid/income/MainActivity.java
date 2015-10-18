package com.blocksolid.income;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView grossIncomeValueText;
    TextView personalAllowanceValueText;
    TextView totalTaxDeductionsValueText;
    TextView nationalInsuranceValueText;
    TextView totalDeductionsValueText;
    TextView netIncomeValueText;

    TextView grossIncomeText;
    TextView netIncomeText;

    TaxCalculator taxCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxCalculator = new TaxCalculator();
        grossIncomeValueText = (TextView)findViewById(R.id.main_txt_gross_value);
        personalAllowanceValueText = (TextView)findViewById(R.id.main_txt_allowance_value);
        totalTaxDeductionsValueText = (TextView)findViewById(R.id.main_txt_tax_deductions_value);
        nationalInsuranceValueText = (TextView)findViewById(R.id.main_txt_national_insurance_value);
        totalDeductionsValueText = (TextView)findViewById(R.id.main_txt_total_deductions_value);
        netIncomeValueText = (TextView)findViewById(R.id.main_txt_net_income_value);

        grossIncomeText = (TextView)findViewById(R.id.main_txt_gross);
        netIncomeText = (TextView)findViewById(R.id.main_txt_net_income);
    }

    public void displayAnnualDeductions(View view) {
        // Display breakdown of all deductions
        EditText editText = (EditText) findViewById(R.id.main_edit_salary);
        if ((editText.getText()).length() > 0) {
            Integer enteredValue = Integer.valueOf(editText.getText().toString())*100;
            taxCalculator.setGrossIncome(enteredValue);
            setAnnualTextValues();
        }
        else {
            clearTextValues();
        }
    }

    public void displayMonthlyDeductions(View view) {
        // Display breakdown of all deductions monthly
        // TODO refactor this and the above method
        EditText editText = (EditText) findViewById(R.id.main_edit_salary);
        if ((editText.getText()).length() > 0) {
            Integer enteredValue = Integer.valueOf(editText.getText().toString())*100;
            taxCalculator.setGrossIncome(enteredValue);
            setMonthlyTextValues();
        }
        else {
            clearTextValues();
        }
    }

    public void setAnnualTextValues() {
        grossIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualGrossIncome()));
        personalAllowanceValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualPersonalAllowance()));
        totalTaxDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualTotalTaxDeductions()));
        nationalInsuranceValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualNiContributions()));
        totalDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualTotalDeductions()));
        netIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getAnnualNetIncome()));

        grossIncomeText.setText(R.string.main_gross_annual_income);
        netIncomeText.setText(R.string.main_net_annual_income);
    }

    public void setMonthlyTextValues() {
        grossIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyGrossIncome()));
        personalAllowanceValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyPersonalAllowance()));
        totalTaxDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyTotalTaxDeductions()));
        nationalInsuranceValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyNiContributions()));
        totalDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyTotalDeductions()));
        netIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getMonthlyNetIncome()));

        grossIncomeText.setText(R.string.main_gross_monthly_income);
        netIncomeText.setText(R.string.main_net_monthly_income);
    }

    public void clearTextValues() {
        grossIncomeValueText.setText("");
        personalAllowanceValueText.setText("");
        totalTaxDeductionsValueText.setText("");
        nationalInsuranceValueText.setText("");
        totalDeductionsValueText.setText("");
        netIncomeValueText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String displayAsPoundsAndPence(int value) {
        DecimalFormat currency = new DecimalFormat("£#,###,##0.00");
        Double valueDouble = value/100.0;
        return currency.format(valueDouble);
    }
}

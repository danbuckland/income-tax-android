package com.blocksolid.income;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    TableLayout breakdownTable;

    TextView grossIncomeValueText;
    TextView personalAllowanceValueText;
    TextView totalTaxDeductionsValueText;
    TextView nationalInsuranceValueText;
    TextView totalDeductionsValueText;
    TextView netIncomeValueText;

    EditText salaryEditText;

    TextView grossIncomeText;
    TextView netIncomeText;

    TaxCalculator taxCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchValues();
            }
        });

        salaryEditText = (EditText) findViewById(R.id.main_edit_salary);
        salaryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    displayOnGo();
                    handled = true;
                }
                return handled;
            }
        });

        breakdownTable = (TableLayout)findViewById(R.id.main_tbl_breakdown);

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

    public void displayOnGoBtn(View view) {
        displayOnGo();
    }

    public void displayOnGo() {
        if (salaryEditText.getText().length() > 0) {
            Integer enteredValue = Integer.valueOf(salaryEditText.getText().toString())*100;
            taxCalculator.setGrossIncome(enteredValue);
            switchValues();
            showValues();
        } else {
            hideValues();
        }
    }

    public void switchValues() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if(checkedId == R.id.main_radio_btn_yearly) {
            setAnnualTextValues();
        } else if (checkedId == R.id.main_radio_btn_monthly) {
            setMonthlyTextValues();
        } else {
            setWeeklyTextValues();
        }
    }

    // TODO refactor the below three methods into one that takes the period as a param
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

    public void setWeeklyTextValues() {
        grossIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyGrossIncome()));
        personalAllowanceValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyPersonalAllowance()));
        totalTaxDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyTotalTaxDeductions()));
        nationalInsuranceValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyNiContributions()));
        totalDeductionsValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyTotalDeductions()));
        netIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getWeeklyNetIncome()));

        grossIncomeText.setText(R.string.main_gross_weekly_income);
        netIncomeText.setText(R.string.main_net_weekly_income);
    }

    public void hideValues() {
        radioGroup.setVisibility(View.GONE);
        breakdownTable.setVisibility(View.GONE);
    }

    public void showValues() {
        radioGroup.setVisibility(View.VISIBLE);
        breakdownTable.setVisibility(View.VISIBLE);
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

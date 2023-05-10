package com.example.budgettracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText mIncomeEditText;
    private EditText mExpenseEditText;
    private Button mAddButton;
    private TextView mBalanceTextView;
    private double mBalance = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIncomeEditText = findViewById(R.id.incomeEditText);
        mIncomeEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mExpenseEditText = findViewById(R.id.expenseEditText);
        mExpenseEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mAddButton = findViewById(R.id.addButton);
        mBalanceTextView = findViewById(R.id.balanceTextView);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
    }

    private void addTransaction() {
        String incomeStr = mIncomeEditText.getText().toString();
        String expenseStr = mExpenseEditText.getText().toString();

        if (incomeStr.isEmpty() && expenseStr.isEmpty()) {
            Toast.makeText(this, "Please enter income or expense", Toast.LENGTH_SHORT).show();
            return;
        }

        double income = incomeStr.isEmpty() ? 0.0 : Double.parseDouble(incomeStr);
        double expense = expenseStr.isEmpty() ? 0.0 : Double.parseDouble(expenseStr);

        mBalance += income - expense;
        mBalanceTextView.setText(String.format(Locale.getDefault(), "%.2f", mBalance));
        mIncomeEditText.setText("");
        mExpenseEditText.setText("");
    }
}

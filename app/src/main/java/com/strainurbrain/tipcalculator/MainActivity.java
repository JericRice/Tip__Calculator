package com.strainurbrain.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.View.OnClickListener;

// not importing

import android.widget.TextView.OnEditorActionListener; // manual type

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener, OnClickListener {

/* Declare var for billAmountEdit */

    private EditText billAmountEditText;
    private TextView tipTextView;
    private TextView totalTextView;

// Declare variable calculateAndDisplay Method
    private TextView percentTextView;

// Declare variable for Button Obj
    private Button percentUpButton;
    private Button percentDownButton;

// def shared preferences
    private SharedPreferences savedValues;

// define variables needed for more that one method global
    private String billAmountString = "";
    private float tipPercent = .15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* If the app was run before its previous state */

        super.onCreate(savedInstanceState);

        /*Use findViewById */
        setContentView(R.layout.activity_main);

        billAmountEditText = (EditText)findViewById(R.id.billAmountEditText);
        percentTextView = (TextView)findViewById(R.id.percentTextView);
        tipTextView = (TextView)findViewById(R.id.tipTextView);
        totalTextView = (TextView)findViewById(R.id.totalTextView);

        // Get reference to buttons
        percentUpButton = (Button)findViewById(R.id.percentUpButton);
        percentDownButton = (Button)findViewById(R.id.percentDownButton);

        billAmountEditText.setOnEditorActionListener(this);

        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);


        // Define shared preferences object




    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {


        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
        {
            //tipTextView.setText("$10.00");
            //totalTextView.setText("$110.00");
            calculateAndDisplay();
        }
        return false;
    }

    public void calculateAndDisplay(){

        //get bill amount

        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        float tipAmount = (billAmount * tipPercent);
        float totalAmount = billAmount + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;

        }

    }
}

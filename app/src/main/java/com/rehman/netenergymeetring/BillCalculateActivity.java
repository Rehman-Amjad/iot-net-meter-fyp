package com.rehman.netenergymeetring;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BillCalculateActivity extends AppCompatActivity {

    private EditText inputUnits, protectedRateInput, rate201to300Input, rateAbove300Input, gstInput;
    private Button calculateButton,backButton;
    private TextView totalBillText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_calculate);

        // Initialize views
        inputUnits = findViewById(R.id.inputUnits);
        protectedRateInput = findViewById(R.id.protectedRateInput);
        rate201to300Input = findViewById(R.id.rate201to300Input);
        rateAbove300Input = findViewById(R.id.rateAbove300Input);
        gstInput = findViewById(R.id.gstInput);
        calculateButton = findViewById(R.id.calculateButton);
        totalBillText = findViewById(R.id.totalBillText);
        backButton = findViewById(R.id.backButton);

        // Set button onClick listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        backButton.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

    }

    private void calculateBill() {
        try {
            int consumedUnits = Integer.parseInt(inputUnits.getText().toString().trim());
            double protectedRate = Double.parseDouble(protectedRateInput.getText().toString().trim());
            double rate201to300 = Double.parseDouble(rate201to300Input.getText().toString().trim());
            double rateAbove300 = Double.parseDouble(rateAbove300Input.getText().toString().trim());
            double gstPercentage = Double.parseDouble(gstInput.getText().toString().trim());

            double totalBill = 0;
            double bill = 0;


            if(consumedUnits >300){
                bill =  consumedUnits * rateAbove300;
            }else if(consumedUnits > 200){
                bill = consumedUnits * rate201to300;
            }else{
                bill = consumedUnits * protectedRate;
            }

            totalBill = bill * gstPercentage / 100;

            totalBill  = totalBill + bill;

//            // Calculate based on units consumed
//            if (consumedUnits <= 200) {
//                totalBill = consumedUnits * protectedRate;
//            } else if (consumedUnits <= 300) {
//                totalBill = 200 * protectedRate + (consumedUnits - 200) * rate201to300;
//            } else {
//                totalBill = 200 * protectedRate + 100 * rate201to300 + (consumedUnits - 300) * rateAbove300;
//            }
//
//            // Add GST
//            totalBill += totalBill * (gstPercentage / 100);

            // Display total bill
            totalBillText.setText("Total Bill amount: Rs." + totalBill);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoltageActivity extends AppCompatActivity {

    ImageView back_image;
    TextView tv_text,tv_DateTime;
    String value,date;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltage);

        initViews();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentInfo")
                .child("1000");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    value = snapshot.child("Vlotage").getValue(String.class);
                    date = snapshot.child("DateTime").getValue(String.class);

                   tv_text.setText("Total voltage is use: " + value +"V");
                    tv_DateTime.setText(date);
                    progressDialog.dismiss();
                }else{
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void initViews()
    {
        back_image = findViewById(R.id.back_image);
        tv_text = findViewById(R.id.tv_text);
        tv_DateTime = findViewById(R.id.tv_DateTime);
    }
}
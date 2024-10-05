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

public class WapdaActivity extends AppCompatActivity {

    ImageView back_image;
    TextView tv_text,tv_text2,tv_DateTime;
    String value,date;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wapda);

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
                    value = snapshot.child("MeterInfos").getValue(String.class);
                    date = snapshot.child("DateTime").getValue(String.class);

                    if (value.equals("1"))
                    {
                        tv_text2.setText("Meter 2 is ON");
                        tv_text.setText("Meter 1 is OFF");
                        progressDialog.dismiss();

                    }else if (value.equals("0"))
                    {
                        tv_text2.setText("Meter 2 is OFF");
                        tv_text.setText("Meter 1 is ON");
                        progressDialog.dismiss();
                    }else if(value.equals("2"))
                    {
                        tv_text.setText("Meter 1 is OFF");
                        tv_text2.setText("Meter 2 is OFF");
                        progressDialog.dismiss();
                    }

                    tv_DateTime.setText(date);
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
        tv_text2 = findViewById(R.id.tv_text2);
    }
}
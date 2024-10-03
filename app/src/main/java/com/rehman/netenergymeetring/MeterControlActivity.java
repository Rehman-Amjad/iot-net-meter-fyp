package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeterControlActivity extends AppCompatActivity {

    ImageView back_image,image1,image2;
    TextView tv_text,tv_text2;
    Button btn_meter1_on,btn_meter1_off,btn_meter2_on,btn_meter2_off;
    String value,value2;
    String status,status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_control);

        initViews();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_meter1_on.setOnClickListener(v -> {
            btn_meter1_off.setEnabled(true);
            btn_meter1_on.setEnabled(false);
            btn_meter2_off.setEnabled(false);
            btn_meter2_on.setEnabled(true);
            status = "1";
            status2 = "0";
            tv_text2.setText("Meter 2 is OFF");
            tv_text.setText("Meter 1 is ON");
            meter1ValueUpdate(status);
        });

        btn_meter1_off.setOnClickListener(v -> {
            btn_meter1_off.setEnabled(false);
            btn_meter1_on.setEnabled(true);
            btn_meter2_off.setEnabled(true);
            btn_meter2_on.setEnabled(false);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    reference.child("MeterNosinfo1").setValue("0");
                    tv_text.setText("Meter 1 is OFF");
                    Toast.makeText(MeterControlActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//            status = "1";
//            status2 = "0";
//            tv_text2.setText("Meter 2 is ON");
//            tv_text.setText("Meter 1 is OFF");
//            meter1ValueUpdate(status);
        });

        btn_meter2_on.setOnClickListener(v -> {
            btn_meter1_off.setEnabled(false);
            btn_meter1_on.setEnabled(true);
            btn_meter2_off.setEnabled(true);
            btn_meter2_on.setEnabled(false);
            status = "0";
            status2 = "1";
            tv_text2.setText("Meter 2 is ON");
            tv_text.setText("Meter 1 is OFF");
            meter2ValueUpdate(status);
        });

        btn_meter2_off.setOnClickListener(v -> {
            btn_meter1_off.setEnabled(true);
            btn_meter1_on.setEnabled(false);
            btn_meter2_off.setEnabled(false);
            btn_meter2_on.setEnabled(true);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    reference.child("MeterNosinfo2").setValue("0");
                    tv_text2.setText("Meter 2 is OFF");
                    Toast.makeText(MeterControlActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//            status = "1";
//            status2 = "0";
//            tv_text2.setText("Meter 2 is ON");
//            tv_text.setText("Meter 1 is OFF");
//            meter2ValueUpdate(status);
        });


        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value  = snapshot.child("MeterNosinfo1").getValue(String.class);
                value2 = snapshot.child("MeterNosinfo2").getValue(String.class);


                if (value.equals("0"))
                {
                    btn_meter1_off.setEnabled(false);
                    btn_meter1_on.setEnabled(true);
                    btn_meter2_off.setEnabled(true);
                    btn_meter2_on.setEnabled(false);
                    tv_text.setText("Meter 1 in OFF");
                }else if (value.equals("1"))
                {
                    btn_meter1_off.setEnabled(true);
                    btn_meter1_on.setEnabled(false);
                    btn_meter2_off.setEnabled(false);
                    btn_meter2_on.setEnabled(true);
                    tv_text.setText("Meter 1 in ON");
                }

                if (value2.equals("0"))
                {
                    btn_meter1_off.setEnabled(true);
                    btn_meter1_on.setEnabled(false);
                    btn_meter2_off.setEnabled(false);
                    btn_meter2_on.setEnabled(true);
                    tv_text2.setText("Meter 2 in OFF");
                }else if (value2.equals("1"))
                {
                    btn_meter1_off.setEnabled(false);
                    btn_meter1_on.setEnabled(true);
                    btn_meter2_off.setEnabled(true);
                    btn_meter2_on.setEnabled(false);
                    tv_text2.setText("Meter 2 in ON");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void meter2ValueUpdate(String status)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child("MeterNosinfo1").setValue(status);
                reference.child("MeterNosinfo2").setValue(status2);
                Toast.makeText(MeterControlActivity.this, "updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void meter1ValueUpdate(String status)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child("MeterNosinfo1").setValue(status);
                reference.child("MeterNosinfo2").setValue(status2);
                Toast.makeText(MeterControlActivity.this, "updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initViews()
    {
        back_image = findViewById(R.id.back_image);
        image1 = findViewById(R.id.image1);
        tv_text = findViewById(R.id.tv_text);
        btn_meter1_on = findViewById(R.id.btn_meter1_on);
        btn_meter1_off = findViewById(R.id.btn_meter1_off);
        image2 = findViewById(R.id.image2);
        tv_text2 = findViewById(R.id.tv_text2);
        btn_meter2_on = findViewById(R.id.btn_meter2_on);
        btn_meter2_off = findViewById(R.id.btn_meter2_off);
    }
}
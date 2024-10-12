package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class LightControlActivity extends AppCompatActivity {

    ImageView back_image,image1,image2;
    TextView tv_text,tv_text2;
    Button btn_light1_on,btn_light1_off,btn_light2_on,btn_light2_off;
    String value,value2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);

        initViews();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    value = snapshot.child("LightNosInfo1").getValue(String.class);
                    value2 = snapshot.child("LightNosInfo2").getValue(String.class);

                    if (value.equals("0"))
                    {
                        image1.setImageResource(R.drawable.bulb_off);
                        tv_text.setText("Light#1 Is off");

                    }else if (value.equals("1"))
                    {
                        image1.setImageResource(R.drawable.bulb_on);
                        tv_text.setText("Light#1 Is on");
                    }

                    assert value2 != null;
                    if (value2.equals("0"))
                    {
                        image2.setImageResource(R.drawable.bulb_off);
                        tv_text2.setText("Light#2 Is off");

                    }else if (value2.equals("1"))
                    {
                        image2.setImageResource(R.drawable.bulb_on);
                        tv_text2.setText("Light#2 Is on");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_light1_on.setOnClickListener(v -> {
            String status = "1";
            tv_text.setText("Light 1 is ON");
            image1.setImageResource(R.drawable.bulb_on);
            updateLight1Value(status);
        });

        btn_light1_off.setOnClickListener(v -> {
            String status = "0";
            tv_text.setText("Light 1 is OFF");
            image1.setImageResource(R.drawable.bulb_off);
            updateLight1Value(status);
        });

        btn_light2_on.setOnClickListener(v -> {
            String status = "1";
            tv_text2.setText("Light 2 is ON");
            image2.setImageResource(R.drawable.bulb_on);
            updateLight2Value(status);
        });

        btn_light2_off.setOnClickListener(v -> {
            String status = "0";
            tv_text2.setText("Light 2 is OFF");
            image2.setImageResource(R.drawable.bulb_off);
            updateLight2Value(status);
        });


    }

    private void updateLight2Value(String status)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child("LightNosInfo2").setValue(status);
                if (status.equals("0"))
                {
                    Toast.makeText(LightControlActivity.this, "Light 2 is OFF", Toast.LENGTH_SHORT).show();
                }else if (status.equals("1"))
                {
                    Toast.makeText(LightControlActivity.this, "Light 2 is ON", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateLight1Value(String status)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child("LightNosInfo1").setValue(status);

                if (status.equals("0"))
                {
                    Toast.makeText(LightControlActivity.this, "Light 1 is OFF", Toast.LENGTH_SHORT).show();
                }else if (status.equals("1"))
                {
                    Toast.makeText(LightControlActivity.this, "Light 1 is On", Toast.LENGTH_SHORT).show();
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
        image1 = findViewById(R.id.image1);
        tv_text = findViewById(R.id.tv_text);
        btn_light1_on = findViewById(R.id.btn_light1_on);
        btn_light1_off = findViewById(R.id.btn_light1_off);
        image2 = findViewById(R.id.image2);
        tv_text2 = findViewById(R.id.tv_text2);
        btn_light2_on = findViewById(R.id.btn_light2_on);
        btn_light2_off = findViewById(R.id.btn_light2_off);
    }
}
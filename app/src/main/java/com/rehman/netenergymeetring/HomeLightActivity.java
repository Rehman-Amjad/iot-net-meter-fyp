package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeLightActivity extends AppCompatActivity {

    ImageView back_image,image1,image2;
    TextView tv_text,tv_text2,tv_DateTime;
    String value,value2,date;
    ProgressDialog progressDialog;
    LinearLayout data_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_light);

        initViews();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        data_layout.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentInfo")
                .child("1000");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    data_layout.setVisibility(View.VISIBLE);
                    value = snapshot.child("Light1").getValue(String.class);
                    value2 = snapshot.child("Light2").getValue(String.class);
                    date = snapshot.child("DateTime").getValue(String.class);

                   if (value.equals("0"))
                   {
                       image1.setImageResource(R.drawable.bulb_off);
                       tv_text.setText("Light#1 Is off");
                       tv_DateTime.setText(date);
                   }if (value.equals("1"))
                   {
                       image1.setImageResource(R.drawable.bulb_on);
                       tv_text.setText("Light#1 Is on");
                       tv_DateTime.setText(date);
                   }


                   if (value2.equals("0"))
                   {
                       image2.setImageResource(R.drawable.bulb_off);
                       tv_text2.setText("Light 2 Is off");
                       tv_DateTime.setText(date);
                   }if (value2.equals("1"))
                   {
                       image2.setImageResource(R.drawable.bulb_on);
                       tv_text2.setText("Light2 Is on");
                       tv_DateTime.setText(date);
                   }
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
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        tv_text = findViewById(R.id.tv_text);
        tv_text2 = findViewById(R.id.tv_text2);
        tv_DateTime = findViewById(R.id.tv_DateTime);
        data_layout = findViewById(R.id.data_layout);
    }
}
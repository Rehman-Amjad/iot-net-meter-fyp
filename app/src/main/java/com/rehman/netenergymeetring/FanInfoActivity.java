package com.rehman.netenergymeetring;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FanInfoActivity extends AppCompatActivity {

    ImageView back_image,image1,image2;
    TextView tv_text,tv_text2,tv_DateTime;
    String value,value2,date;
    ProgressDialog progressDialog;
    LinearLayout data_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fan_info);

        initViews();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        data_layout.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentInfo")
                .child("1000");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    data_layout.setVisibility(View.VISIBLE);
                    value = snapshot.child("Fan1").getValue(String.class);
                    value2 = snapshot.child("Fan2").getValue(String.class);
                    date = snapshot.child("DateTime").getValue(String.class);

                    if (value.equals("0"))
                    {
                        image1.setImageResource(R.drawable.fan_off);
                        tv_text.setText("Fan 1 Is off");
                        tv_DateTime.setText(date);
                    }if (value.equals("1"))
                {
                    Glide.with(FanInfoActivity.this)
                            .asGif()
                            .load(R.drawable.fan_on) // Replace 'my_gif' with your actual GIF file name
                            .into(image1);
                    tv_text.setText("Fan 1 Is on");
                    tv_DateTime.setText(date);
                }


                    if (value2.equals("0"))
                    {
                        image2.setImageResource(R.drawable.fan_off);
                        tv_text2.setText("Fan 2 Is off");
                        tv_DateTime.setText(date);
                    }if (value2.equals("1"))
                {
                    Glide.with(FanInfoActivity.this)
                            .asGif()
                            .load(R.drawable.fan_on) // Replace 'my_gif' with your actual GIF file name
                            .into(image2);
                    tv_text2.setText("Fan Is on");
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
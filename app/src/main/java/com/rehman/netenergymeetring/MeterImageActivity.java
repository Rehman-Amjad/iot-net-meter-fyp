package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class MeterImageActivity extends AppCompatActivity {

    ImageView back_image,image;
    TextView tv_DateTime;
    ProgressDialog progressDialog;
    String value,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_image);

        back_image = findViewById(R.id.back_image);
        image = findViewById(R.id.image);
        tv_DateTime = findViewById(R.id.tv_DateTime);

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        image.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentInfo")
                .child("1000");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    image.setVisibility(View.VISIBLE);

                    value = snapshot.child("img").getValue(String.class);
                    date = snapshot.child("DateTime").getValue(String.class);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] imageBytes = baos.toByteArray();
                    imageBytes = Base64.decode(value, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    image.setImageBitmap(decodedImage);
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
}
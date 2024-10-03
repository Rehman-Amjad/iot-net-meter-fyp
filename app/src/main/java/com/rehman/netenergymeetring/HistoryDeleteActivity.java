package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rehman.netenergymeetring.Model.HistoryModel;

import java.util.ArrayList;

public class HistoryDeleteActivity extends AppCompatActivity {

    ImageView back_image;
    TextView tv_text;
    Button btn_clear;
    ArrayList<HistoryModel> mDataList = new ArrayList<>();
    ArrayList<String> mArrayLst  =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_delete);

        back_image = findViewById(R.id.back_image);
        tv_text = findViewById(R.id.tv_text);
        btn_clear = findViewById(R.id.btn_clear);

        btn_clear.setEnabled(false);
        tv_text.setText("Total Number of records: 0");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllInformation");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists())
                {
                    btn_clear.setEnabled(true);

                    HistoryModel model = snapshot.getValue(HistoryModel.class);
                    mDataList.add(model);

                    mArrayLst.add(mDataList.toString());
                    tv_text.setText(String.valueOf("Total Number of Records: "+mDataList.size()));
                }else
                {
                    btn_clear.setEnabled(false);
                    tv_text.setText("Total Number of records: 0");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });


        btn_clear.setOnClickListener(v -> {
            alreadyAccountDialog();
        });


//        for (int i=0;i<mArrayLst.size();i++)
//        {
//            tv_text.setText("Total Number of Field: " + String.valueOf(i));
//        }


    }
    private void alreadyAccountDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View layout_dialog = LayoutInflater.from(this).inflate(R.layout.delete_list_layout, null);
        builder.setView(layout_dialog);

        //Show Dialog
        Button btn_Yes = layout_dialog.findViewById(R.id.btn_Yes);
        Button btn_NO = layout_dialog.findViewById(R.id.btn_No);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(R.style.alertDialogAnimation);


        btn_Yes.setOnClickListener(v -> {
            deleteHistory(dialog);
        });

        btn_NO.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }

    private void deleteHistory(AlertDialog dialog)
    {
        FirebaseDatabase.getInstance().getReference("AllInformation").removeValue();
        dialog.dismiss();
        Toast.makeText(this, "History Clear", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(HistoryDeleteActivity.this,HistoryDeleteActivity.class));

    }
}
package com.rehman.netenergymeetring.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rehman.netenergymeetring.Model.HistoryModel;
import com.rehman.netenergymeetring.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>
{
    private Context context;
    private List<HistoryModel> mDatalist;

    public HistoryAdapter(Context context, List<HistoryModel> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(myview);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HistoryModel user=mDatalist.get(position);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(user.getImg(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        holder.imageView.setImageBitmap(decodedImage);

        holder.tvId.setText("Record No: " + user.getId());
       if (user.getLight1().equals("1"))
       {
           holder.tv_temp.setText("Light #1 is off");
       }else
       {
           holder.tv_temp.setText("Light #1 is on");
       }

       if (user.getLight2().equals("1"))
       {
           holder.tv_humidity.setText("Light #2 is Off");
       }else
       {
           holder.tv_humidity.setText("Light #2 is On");
       }

       if (user.getMeterInfos().equals("0"))
       {
           holder.tv_ultra.setText("Meter Info: All Meters are off");
       }else if (user.getMeterInfos().equals("1"))
       {
           holder.tv_ultra.setText("Meter Info: Meters #1 are on");
       }else if (user.getMeterInfos().equals("2"))
       {
           holder.tv_ultra.setText("Meter Info: Meters #2 are on");
       }
       holder.tv_foodTray.setText("Voltage info: "+user.getVlotage() + " V");

        holder.tv_DateTime.setText("Date & Time: " + user.getDateTime());
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tv_temp,tv_humidity,tv_DateTime,tv_ultra,tv_foodTray;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.card_image);
            tvId=itemView.findViewById(R.id.tvId);
            tv_temp=itemView.findViewById(R.id.tv_temp);
            tv_humidity=itemView.findViewById(R.id.tv_humidity);
            tv_DateTime=itemView.findViewById(R.id.tv_DateTime);
            tv_ultra=itemView.findViewById(R.id.tv_ultra);
            tv_foodTray=itemView.findViewById(R.id.tv_foodTray);

        }
    }
}

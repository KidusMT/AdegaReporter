package com.gmail.kidusmt.adegareporter.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.kidusmt.adegareporter.R;
import com.gmail.kidusmt.adegareporter.data.Accident;

import java.io.File;
import java.util.List;

/**
 * Created by KidusMT on 12/30/2017.
 */

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Accident> accidentList;

    public AccidentAdapter(Context mContext, List<Accident> accidentList) {
        this.mContext = mContext;
        this.accidentList = accidentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Accident movie = accidentList.get(position);

        holder.accidentLocation.setText(movie.getLocation());
        holder.accidentDate.setText(movie.getDate());

        File imgFile = new File(movie.getImgPath());

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.accidentImg.setImageBitmap(myBitmap);
        }


        holder.accidentImg.setOnClickListener(
                v -> Toast.makeText(mContext, "don't have time", Toast.LENGTH_SHORT).show()
        );

    }

    @Override
    public int getItemCount() {
        return (accidentList != null) ? accidentList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView accidentLocation, accidentDate;//,accidentPlateNumber,accidentDescription;
        public ImageView accidentImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            accidentImg = itemView.findViewById(R.id.iv_movie_card_poster);
            accidentLocation = itemView.findViewById(R.id.tv_movie_card_title);
            accidentDate = itemView.findViewById(R.id.tv_movie_card_rating);

        }
    }
}

package com.ab4application.mihai.stackoverflowinformation;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mihai on 22/03/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DevViewHolder>{

    private List<Developer> devList;
    private List<Bitmap> devPhoto;

    RVAdapter(List<Developer> devList){
        this.devList = devList;
        this.devPhoto = null;
    }

    RVAdapter(List<Developer> devList, List<Bitmap> devPhoto){
        this.devList = devList;
        this.devPhoto = devPhoto;
    }

    @Override
    public int getItemCount() {
        return devList.size();
    }

    @Override
    public DevViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_card_view,
                viewGroup, false);
        DevViewHolder pvh = new DevViewHolder(v);
        return pvh;
    }

    public void onBindViewHolder(DevViewHolder personViewHolder, int i) {
        personViewHolder.personId = i;
        String nameText = String.valueOf(i + 1) + ". " + devList.get(i).name;
        personViewHolder.personName.setText(nameText);
        if(devPhoto != null)
            personViewHolder.personPhoto.setImageBitmap(devPhoto.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DevViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        ImageView personPhoto;
        int personId;

        DevViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            personName = (TextView)itemView.findViewById(R.id.devName);
            personPhoto = (ImageView)itemView.findViewById(R.id.devImage);
            // on click listener for the card view
            cv.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DeveloperActivity.class);
                    intent.putExtra("dev", personId);
                    context.startActivity(intent);
                }
            });
        }
    }

}

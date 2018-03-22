package com.ab4application.mihai.stackoverflowinformation;

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

    List<Developer> devList;

    RVAdapter(List<Developer> devList){
        this.devList = devList;
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
        personViewHolder.personName.setText(devList.get(i).name);
        // personViewHolder.personPhoto.setImageResource(devList.get(i).photoLocation);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DevViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        ImageView personPhoto;

        DevViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            personName = (TextView)itemView.findViewById(R.id.devName);
            personPhoto = (ImageView)itemView.findViewById(R.id.devImage);
        }
    }

}

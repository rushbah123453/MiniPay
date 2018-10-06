package com.ubschallenge.upay.Passbook;
import com.ubschallenge.upay.R;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView toPhone;
        TextView timeStamp;
        TextView amount;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.toPhone = (TextView) itemView.findViewById(R.id.toPhone);
            this.timeStamp = (TextView) itemView.findViewById(R.id.timeStamp);
            this.amount = (TextView) itemView.findViewById(R.id.amount);
        }
    }

    public Adapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);



        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView toPhone = holder.toPhone;
        TextView amount = holder.amount;
        TextView timeStamp = holder.timeStamp;
        String amt = dataSet.get(listPosition).getAmt();
        toPhone.setText(dataSet.get(listPosition).getToPhone());
        amount.setText(amt);
        if(amt.startsWith("+"))
        {
            amount.setTextColor(Color.parseColor("#006400"));
        }
        else
        {
            amount.setTextColor(Color.parseColor("#FF0000"));
        }
        timeStamp.setText(dataSet.get(listPosition).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
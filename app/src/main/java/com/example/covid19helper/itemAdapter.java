package com.example.covid19helper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19helper.data_sets.item_set;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemViewHolder> {

    private Context mCtx;
    private List<item_set> itemList;
    private OnItemHoldListener mOnItemHoldListener;

    public itemAdapter(Context mCtx, List<item_set> itemList, OnItemHoldListener onItemHoldListener) {
        this.mCtx = mCtx;
        this.itemList = itemList;
        this.mOnItemHoldListener = onItemHoldListener;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item, parent, false);
        return new itemViewHolder(view, mOnItemHoldListener);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        item_set itemSet = itemList.get(position);
        holder.textViewAddress.setText(itemSet.getAddress());
        holder.textViewCity.setText(itemSet.getCity());
        holder.textViewContact.setText("(+91) " + itemSet.getContact());
        holder.textViewDistance.setText(itemSet.getDistance().substring(0,4) + " km");
        holder.textViewLeadType.setText(itemSet.getLead_type());
        if(itemSet.getFeedback_value() >= 3){
            holder.textViewIsVerified.setText("Verified");
            holder.textViewIsVerified.setTextColor(Color.parseColor("#2F1D1A"));
            holder.textViewIsVerified.setBackgroundColor(Color.parseColor("#8EF361"));
        } else if (itemSet.getFeedback_value() < 3 && itemSet.getFeedback_value() > -3){
            holder.textViewIsVerified.setText("Not Verified");
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView textViewAddress, textViewCity, textViewContact, textViewDistance, textViewLeadType, textViewIsVerified;
        OnItemHoldListener onItemHoldListener;

        public itemViewHolder(@NonNull View itemView, OnItemHoldListener onItemHoldListener) {
            super(itemView);

            textViewAddress = itemView.findViewById(R.id.address_item_tv);
            textViewCity = itemView.findViewById(R.id.city_item_tv);
            textViewContact = itemView.findViewById(R.id.contact_item_tv);
            textViewDistance = itemView.findViewById(R.id.distance_item_tv);
            textViewLeadType = itemView.findViewById(R.id.lead_item_tv);
            textViewIsVerified = itemView.findViewById(R.id.is_verified_item_tv);
            this.onItemHoldListener = onItemHoldListener;

            itemView.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View v) {
            onItemHoldListener.onItemHold(getAbsoluteAdapterPosition());
            return true;
        }
    }

    public interface OnItemHoldListener {
        void onItemHold(int position);
    }
}

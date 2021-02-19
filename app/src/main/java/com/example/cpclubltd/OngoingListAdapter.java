package com.example.cpclubltd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class OngoingListAdapter extends RecyclerView.Adapter<OngoingListAdapter.MyViewHolder> {
    private List<OngoingInfo> ongoingInfo;
    private OnItemClick onItemClick;


    public OngoingListAdapter(List<OngoingInfo> ongoingInfo) {
        this.ongoingInfo = ongoingInfo;
    }

    public void getOngoingList(List<OngoingInfo> ongoingInfo){
        this.ongoingInfo=ongoingInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ongoing,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OngoingInfo model = ongoingInfo.get(position);
        holder.tvName.setText("Event Name :  "+model.getName());
        holder.tvDate.setText("Event closing date :  "+model.getDate());
        holder.tvWing.setText("Organized  by :  "+model.getWing()+" Wing");
    }

    @Override
    public int getItemCount() {
        return ongoingInfo.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvWing,tvDate;

        public MyViewHolder(View inflate) {
            super(inflate);
            tvName = inflate.findViewById(R.id.single_event_name);
            tvWing = inflate.findViewById(R.id.single_wing);
            tvDate = inflate.findViewById(R.id.single_date);

            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   int position = getAdapterPosition();
                   if(onItemClick!= null && position !=RecyclerView.NO_POSITION){
                       onItemClick.onItemClick(position);
                   }
                }
            });

            inflate.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position= getAdapterPosition();
                    if(onItemClick != null && position != RecyclerView.NO_POSITION){
                        onItemClick.onLongItemClick(position);

                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClick{
        void onItemClick(int position);
        void onLongItemClick(int position);
    }
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
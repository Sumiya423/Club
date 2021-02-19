package com.example.cpclubltd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UpcomingListAdapter extends RecyclerView.Adapter<UpcomingListAdapter.MyViewHolder> {
    private List<UpcomingInfo> upcomingInfo;
    private OnItemClick onItemClick;


    public UpcomingListAdapter(List<UpcomingInfo> upcomingInfo) {
        this.upcomingInfo = upcomingInfo;
    }

    public void getUpComingList(List<UpcomingInfo> upcomingInfo){
        this.upcomingInfo=upcomingInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_upcoming,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UpcomingInfo up_model = upcomingInfo.get(position);
        holder.tvUpName.setText("Event Name :  "+up_model.getUp_name());
        holder.tvUpDate.setText("Registration closing date :  "+up_model.getUp_date());
        holder.tvUpWing.setText("Organized  by :  "+up_model.getUp_wing()+" Wing");
    }

    @Override
    public int getItemCount() {
        return upcomingInfo.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUpName,tvUpWing,tvUpDate;

        public MyViewHolder(View inflate) {
            super(inflate);
            tvUpName = inflate.findViewById(R.id.single_up_event_name);
            tvUpWing = inflate.findViewById(R.id.single_up_wing);
            tvUpDate = inflate.findViewById(R.id.single_up_date);

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
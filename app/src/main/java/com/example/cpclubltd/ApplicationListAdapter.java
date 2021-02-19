package com.example.cpclubltd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.ApplViewHolder> {

    private List<ApplInfo> applicationInfo;
    private OnItemClick onItemClick;




    @NonNull
    @Override
    public ApplViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_application,parent,false));
    }

    public ApplicationListAdapter(List<ApplInfo> applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public void getApplicationList(List<ApplInfo> applicationInfo){
        this.applicationInfo=applicationInfo;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplViewHolder holder, int position) {

        ApplInfo modelApp = applicationInfo.get(position);
        holder.sNameTV.setText("Student Name :  "+modelApp.getsName());
        holder.sIdTV.setText("Student ID: "+modelApp.getsId());
        holder.evIdTV.setText("Event ID: "+modelApp.getEvID());
    }

    @Override
    public int getItemCount() {
        return applicationInfo.size();
    }

    public class ApplViewHolder extends RecyclerView.ViewHolder{
        TextView sNameTV,sIdTV,evIdTV;

        public ApplViewHolder(@NonNull View itemView) {
            super(itemView);
            sNameTV=itemView.findViewById(R.id.single_stu_name);
            sIdTV=itemView.findViewById(R.id.single_stu_id);
            evIdTV=itemView.findViewById(R.id.single_ev_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(onItemClick!= null && position !=RecyclerView.NO_POSITION){
                        onItemClick.onItemClick(position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
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
    public void setOnItemClick(ApplicationListAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}

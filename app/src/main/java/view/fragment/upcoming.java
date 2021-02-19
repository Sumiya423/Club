package view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cpclubltd.DbHelper;
import com.example.cpclubltd.EventInsertActivity;
import com.example.cpclubltd.OngoingInfo;
import com.example.cpclubltd.OngoingListAdapter;
import com.example.cpclubltd.R;
import com.example.cpclubltd.UpEventInsertActivity;
import com.example.cpclubltd.UpcomingInfo;
import com.example.cpclubltd.UpcomingListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class upcoming extends Fragment {

    private RecyclerView up_recyclerView;
    FloatingActionButton up_insertBtn;
    private List<UpcomingInfo> upcomingList = new ArrayList<>();
    private UpcomingListAdapter upcomingListAdapter;
    DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_upcoming, container, false);

        up_recyclerView = view.findViewById(R.id.upcoming_recylerview);
        up_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        up_recyclerView.setHasFixedSize(true);

        up_insertBtn = view.findViewById(R.id.up_insertBtnId);

        dbHelper = new DbHelper(getContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        upcomingList= dbHelper.getUpcomingList();

        upcomingListAdapter = new UpcomingListAdapter(upcomingList);
        up_recyclerView.setAdapter(upcomingListAdapter);
        upcomingListAdapter.notifyDataSetChanged();

        up_insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), UpEventInsertActivity.class));
            }
        });

        up_loadData();

        upcomingListAdapter.setOnItemClick(new UpcomingListAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLongItemClick(final int position) {

                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setTitle("Delete").setMessage("Do you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteUpData(position);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
            }


        });
        return view;
    }

    private  void deleteUpData(int positiion){
        String id = upcomingList.get(positiion).getUp_id();

        int value= dbHelper.deleteUpcoming(id);
        if(value>0){
            Toast.makeText(getContext(), "Deleted Successfully !", LENGTH_SHORT).show();

            upcomingList.remove(positiion);
            upcomingListAdapter.notifyItemRemoved(positiion);
        }
        else{
            Toast.makeText(getContext(), "Delete Failed!", Toast.LENGTH_SHORT).show();

        }
    }

    private void up_loadData(){
        upcomingList = new ArrayList<>();
        Cursor cursor= dbHelper.showUp();

        if(cursor.getCount()==0){
            Toast.makeText(getContext(), "No Data Found", LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                String up_id =String.valueOf(cursor.getString(0));
                String up_name =cursor.getString(1);
                String up_wing = cursor.getString(2);
                String up_date = String.valueOf(cursor.getString(3));
                String up_details = cursor.getString(4);

                UpcomingInfo upcomingInfo = new UpcomingInfo(up_id,up_name,up_wing,up_date,up_details);
                upcomingList.add(upcomingInfo);

            }
        }
        upcomingListAdapter.getUpComingList(upcomingList);
        upcomingListAdapter.notifyDataSetChanged();
        up_recyclerView.setAdapter(upcomingListAdapter);


    }
}
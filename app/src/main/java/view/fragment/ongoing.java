package view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class ongoing extends Fragment {
    private RecyclerView recyclerView;
    FloatingActionButton insertBtn;
    private List<OngoingInfo> ongoingList = new ArrayList<>();
    private OngoingListAdapter ongoingListAdapter;
    DbHelper dbHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_ongoing, container, false);



       recyclerView = view.findViewById(R.id.ongoing_recylerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       recyclerView.setHasFixedSize(true);
       insertBtn = view.findViewById(R.id.insertBtnId);

       dbHelper = new DbHelper(getContext());
       SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
       ongoingList=dbHelper.getOngoingList();

       ongoingListAdapter = new OngoingListAdapter(ongoingList);
       recyclerView.setAdapter(ongoingListAdapter);
       ongoingListAdapter.notifyDataSetChanged();

       insertBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(getActivity(),EventInsertActivity.class));
           }
       });


       loadData();

       ongoingListAdapter.setOnItemClick(new OngoingListAdapter.OnItemClick() {
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
                       deleteData(position);
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

    private  void deleteData(int positiion){
        String id = ongoingList.get(positiion).getId();

        int value= dbHelper.deleteOngoing(id);
        if(value>0){
            Toast.makeText(getContext(), "Deleted Successfully !", LENGTH_SHORT).show();

            ongoingList.remove(positiion);
            ongoingListAdapter.notifyItemRemoved(positiion);
        }
        else{
            Toast.makeText(getContext(), "Delete Failed!", Toast.LENGTH_SHORT).show();

        }
    }


    private void loadData(){
        ongoingList = new ArrayList<>();
        Cursor cursor= dbHelper.show();

        if(cursor.getCount()==0){
            Toast.makeText(getContext(), "No Data Found", LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                String id =String.valueOf(cursor.getString(0));
                String name =cursor.getString(1);
                String wing = cursor.getString(2);
                String date = String.valueOf(cursor.getString(3));
                String details = cursor.getString(4);

                OngoingInfo ongoingInfo = new OngoingInfo(id,name,wing,date,details);
                ongoingList.add(ongoingInfo);

            }
        }
        ongoingListAdapter.getOngoingList(ongoingList);
        ongoingListAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(ongoingListAdapter);


    }

}
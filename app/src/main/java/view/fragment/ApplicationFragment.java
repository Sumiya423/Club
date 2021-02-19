package view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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

import com.example.cpclubltd.ApplInfo;
import com.example.cpclubltd.ApplicationDetails;
import com.example.cpclubltd.ApplicationListAdapter;
import com.example.cpclubltd.DbHelper;
import com.example.cpclubltd.R;
import com.example.cpclubltd.UpcomingDetailsActivity;
import com.example.cpclubltd.UpcomingInfo;
import com.example.cpclubltd.UpcomingListAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class ApplicationFragment extends Fragment {

    private RecyclerView application_recyclerView;
    private List<ApplInfo> appInfo= new ArrayList<>();
    ApplicationListAdapter appListAdapter;
    DbHelper dbHelper;


    public ApplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application, container, false);

        application_recyclerView=view.findViewById(R.id.application_recylerview);
        application_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        application_recyclerView.setHasFixedSize(true);

        dbHelper = new DbHelper(getContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        appInfo=dbHelper.getApplicationList();

        appListAdapter = new ApplicationListAdapter(appInfo);
        application_recyclerView.setAdapter(appListAdapter);
        appListAdapter.notifyDataSetChanged();

        loadData();

        appListAdapter.setOnItemClick(new ApplicationListAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                ApplInfo appDetails = appInfo.get(position);

                Intent intent= new Intent(getContext(), ApplicationDetails.class);
                intent.putExtra("appDetails",appDetails);
                startActivity(intent);

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

        return  view;
    }

    private  void deleteData(int positiion){
        String id = appInfo.get(positiion).getsId();

        int value= dbHelper.deleteApplication(id);
        if(value>0){
            Toast.makeText(getContext(), "Deleted Successfully !", LENGTH_SHORT).show();

            appInfo.remove(positiion);
            appListAdapter.notifyItemRemoved(positiion);
        }
        else{
            Toast.makeText(getContext(), "Delete Failed!", Toast.LENGTH_SHORT).show();

        }
    }

    private void loadData() {
        appInfo = new ArrayList<>();
        Cursor user_cursor = dbHelper.showApp();

        if (user_cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Data Found", LENGTH_SHORT).show();
        } else {
            while (user_cursor.moveToNext()) {
                String s_id = user_cursor.getString(0);
                String s_name = user_cursor.getString(1);
                String s_email = user_cursor.getString(2);
                String s_details = user_cursor.getString(3);
                String e_id = user_cursor.getString(4);

                ApplInfo applInfo= new ApplInfo(s_id,s_name,e_id,s_email,s_details);
                appInfo.add(applInfo);

            }
        }
        appListAdapter.getApplicationList(appInfo);
        appListAdapter.notifyDataSetChanged();
        application_recyclerView.setAdapter(appListAdapter);

    }
}
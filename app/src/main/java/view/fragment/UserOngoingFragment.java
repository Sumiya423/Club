package view.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cpclubltd.DbHelper;
import com.example.cpclubltd.OngoingInfo;
import com.example.cpclubltd.OngoingListAdapter;
import com.example.cpclubltd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class UserOngoingFragment extends Fragment {

    private RecyclerView user_recyclerView;
    private List<OngoingInfo> user_ongoingList = new ArrayList<>();
    private OngoingListAdapter user_ongoingListAdapter;
    DbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_ongoing, container, false);

        user_recyclerView = view.findViewById(R.id.user_ongoing_recylerview);
        user_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        user_recyclerView.setHasFixedSize(true);

        dbHelper = new DbHelper(getContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        user_ongoingList=dbHelper.getOngoingList();

        user_ongoingListAdapter = new OngoingListAdapter(user_ongoingList);
        user_recyclerView.setAdapter(user_ongoingListAdapter);
        user_ongoingListAdapter.notifyDataSetChanged();

        loadData();

        return view;
    }

    private void loadData(){
        user_ongoingList = new ArrayList<>();
        Cursor user_cursor= dbHelper.show();

        if(user_cursor.getCount()==0){
            Toast.makeText(getContext(), "No Data Found", LENGTH_SHORT).show();
        }
        else{
            while(user_cursor.moveToNext()){
                String u_id =String.valueOf(user_cursor.getString(0));
                String u_name =user_cursor.getString(1);
                String u_wing = user_cursor.getString(2);
                String u_date = String.valueOf(user_cursor.getString(3));
                String u_details = user_cursor.getString(4);

                OngoingInfo user_ongoingInfo = new OngoingInfo(u_id,u_name,u_wing,u_date,u_details);
                user_ongoingList.add(user_ongoingInfo);

            }
        }
        user_ongoingListAdapter.getOngoingList(user_ongoingList);
        user_ongoingListAdapter.notifyDataSetChanged();
        user_recyclerView.setAdapter(user_ongoingListAdapter);


    }
}
package view.fragment;

import android.content.Intent;
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
import com.example.cpclubltd.UpcomingDetailsActivity;
import com.example.cpclubltd.UpcomingInfo;
import com.example.cpclubltd.UpcomingListAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class UserUpcomingFragment extends Fragment {

    private RecyclerView user_up_recyclerView;
    private List<UpcomingInfo> user_upcomingList = new ArrayList<>();
    private UpcomingListAdapter user_upcomingListAdapter;
    DbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user_upcoming, container, false);

        user_up_recyclerView=view.findViewById(R.id.user_upcoming_recylerview);
        user_up_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        user_up_recyclerView.setHasFixedSize(true);

        dbHelper = new DbHelper(getContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        user_upcomingList= dbHelper.getUpcomingList();

        user_upcomingListAdapter = new UpcomingListAdapter(user_upcomingList);
        user_up_recyclerView.setAdapter(user_upcomingListAdapter);
        user_upcomingListAdapter.notifyDataSetChanged();


        user_upcomingListAdapter.setOnItemClick(new UpcomingListAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                UpcomingInfo details = user_upcomingList.get(position);

                Intent intent= new Intent(getContext(), UpcomingDetailsActivity.class);
                intent.putExtra("details",details);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {
            }
        });

        loadData();
        return view;
    }

    private void loadData() {
        user_upcomingList = new ArrayList<>();
        Cursor user_cursor = dbHelper.showUp();

        if (user_cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Data Found", LENGTH_SHORT).show();
        } else {
            while (user_cursor.moveToNext()) {
                String u_up_id = String.valueOf(user_cursor.getString(0));
                String u_up_name = user_cursor.getString(1);
                String u_up_wing = user_cursor.getString(2);
                String u_up_date = String.valueOf(user_cursor.getString(3));
                String u_up_details = user_cursor.getString(4);

                UpcomingInfo user_upcomingInfo = new UpcomingInfo(u_up_id, u_up_name, u_up_wing, u_up_date, u_up_details);
                user_upcomingList.add(user_upcomingInfo);

            }
        }
        user_upcomingListAdapter.getUpComingList(user_upcomingList);
        user_upcomingListAdapter.notifyDataSetChanged();
        user_up_recyclerView.setAdapter(user_upcomingListAdapter);

    }
}
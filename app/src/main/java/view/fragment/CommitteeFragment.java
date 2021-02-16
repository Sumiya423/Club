package view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cpclubltd.AdvisorActivity;
import com.example.cpclubltd.EventInsertActivity;
import com.example.cpclubltd.R;
import com.example.cpclubltd.TeamActivity;

public class CommitteeFragment extends Fragment {
    private CardView advisorCV,teamCV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_committee, container, false);

        advisorCV=view.findViewById(R.id.advisorCardView);
        teamCV=view.findViewById(R.id.teamCardView);

        advisorCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), AdvisorActivity.class));
            }
        });
        teamCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), TeamActivity.class));
            }
        });

        return view;

    }
}
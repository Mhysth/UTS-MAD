package com.example.myfirebaseapps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirebaseapps.Model.Course;
import com.example.myfirebaseapps.adapter.ScheduleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Frag1 extends Fragment {

    RecyclerView rv;
    DatabaseReference mDatabase;
    ArrayList<Course> listCourse = new ArrayList<>();

    public Frag1() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.f1, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("student").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("course");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCourse.clear();
                rv.setAdapter(null);
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    Course course = childSnapshot.getValue(Course.class);
                    listCourse.add(course);
                }
                showData(listCourse);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        rv = view.findViewById(R.id.rv_schedule);
        return view;
    }
    private void showData(ArrayList<Course> listCourse){
        rv.setLayoutManager(new LinearLayoutManager(Frag1.this.getActivity()));
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(Frag1.this.getActivity());
        scheduleAdapter.setListCourse(listCourse);
        rv.setAdapter(scheduleAdapter);
    }
}
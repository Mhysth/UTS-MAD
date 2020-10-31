package com.example.myfirebaseapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myfirebaseapps.Model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Frag3 extends Fragment {

    TextView iname, inim, iemail, igender, iage, iaddress;
    Button btn;
    DatabaseReference mDatabase;
    FirebaseUser mUser;

    public Frag3() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.f3, container, false);

        iname = view.findViewById(R.id.std_name);
        inim = view.findViewById(R.id.std_nim);
        iemail = view.findViewById(R.id.std_email);
        igender = view.findViewById(R.id.std_gender);
        iage = view.findViewById(R.id.std_age);
        iaddress = view.findViewById(R.id.std_address);
        btn = view.findViewById(R.id.buttonlogout);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("student").child(mUser.getUid());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                iname.setText(student.getName());
                inim.setText("NIM : " + student.getNim());
                iemail.setText("Email : " + student.getEmail());
                igender.setText(student.getGender());
                iage.setText("Age : " + student.getAge());
                iaddress.setText("Address : " + student.getAddress());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}
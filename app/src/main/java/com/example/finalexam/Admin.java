package com.example.finalexam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Admin extends Fragment {

    ShowDetailsDialog dialog;
    View v;
    private TaskAdapter adapter;
    private List<Data> taskList = new ArrayList<>();
    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private TextView noUserView;

    DatabaseReference reference;
    private DatabaseReference databaseReference;



    public Admin() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_admin, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
//        noUserView = v.findViewById(R.id.empty_notes_view);
//        db = new DatabaseHelper(getActivity());
//        userList.addAll(db.getAllUsers());

        adapter = new TaskAdapter(getActivity(), taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
        toggleEmptyUsers();


        databaseReference= FirebaseDatabase.getInstance().getReference("Customer");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Data u = postSnapshot.getValue(Data.class);
                    taskList.add(u);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Data u=taskList.get(position);
                showActionsDialog(u);
            }

            @Override
            public void onLongClick(View view, int position) {
                Data u=taskList.get(position);
                showActionsDialog(u);
            }
        }));

        return v;
    }


    private void showActionsDialog(Data u) {
        dialog=new ShowDetailsDialog(u);
        dialog.show(getActivity().getSupportFragmentManager(),"User Dialog");// use getChild... see from a link
        dialog.setCancelable(false);     // dialog should not close on touches outside the dialog (and using this another case by default on back press dialog not closes)
        //  dialog.setCanceledOnTouchOutside(false); not work in fragment
        // dialog.getDialog().getButton(AlertDialog.BUTTON_POSITIVE)
    }


    private void toggleEmptyUsers() {
        // you can check notesList.size() > 0

//        if (db.getUsersCount() > 0) {
//            noUserView.setVisibility(View.GONE);
//        } else {
//            noUserView.setVisibility(View.VISIBLE);
//        }
    }
}

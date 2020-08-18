package com.example.finalexam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login_Fragment extends Fragment {


    View view;
    EditText email,password;
    Button signin,register;

    String EMAIL;
    String PASS;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login_, container, false);

        email=view.findViewById(R.id.signin_email);
        password=view.findViewById(R.id.signin_password);
        signin=view.findViewById(R.id.signin__login_button);
        register=view.findViewById(R.id.signin_register);




        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EMAIL = email.getText().toString();
                PASS = password.getText().toString();

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Users_fragment()).commit();
//


                if (!validatePassword() | !validateEmail()) {
                    Toast.makeText(getContext(), "Fill both fields with no error", Toast.LENGTH_LONG).show();
                    return;
                }


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            User u = postSnapshot.getValue(User.class);

                            if(u.getEmail().equals(EMAIL)&&u.getPass().equals(PASS)){
                                Toast.makeText(getContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Customer()).commit();
                                break;
                            }
                        };
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

                //Toast.makeText(getContext(), "Wrong Email id or Password", Toast.LENGTH_LONG).show();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Register_Fragment()).commit();

            }
        });

        return view;
    }








    private boolean validateEmail(){
        String val=email.getText().toString();
        final String emailchecker="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            email.requestFocus();
            return false;
        }
        else if(!val.matches(emailchecker))
        {
            email.requestFocus();
            return false;
        }
        return true;

    }

    private boolean validatePassword(){
        String val=password.getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            password.requestFocus();
            return false;
        }


        return true;

    }

}
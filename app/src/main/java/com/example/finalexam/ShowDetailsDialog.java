package com.example.finalexam;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ShowDetailsDialog extends AppCompatDialogFragment {

    private Data user;

    private ConstraintLayout constraintLayout;

    EditText productType, productQuantity, DiscountPercentage, TotalPrice, coordinates,name ;
    private ImageView imageView;
    private Button fab;
    private EditText fullname,email,password,dob,age;
    private RadioGroup radioGroup;
    private RadioButton male;
    private RadioButton female;

    private AutoCompleteTextView hobbies;
    private Button save;


    private AlertDialog.Builder builder=null;
    private AlertDialog mAlertDialog=null;


    public ShowDetailsDialog(Data user) {
        this.user = user;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.fragment_customer,null);

        imageView = view.findViewById(R.id.profile_image);
        fab = view.findViewById(R.id.fab);

        productType = view.findViewById(R.id.productType);
        productQuantity= view.findViewById(R.id.quantity);
        name= view.findViewById(R.id.Name);

        DiscountPercentage = view.findViewById(R.id.discount);
        TotalPrice = view.findViewById(R.id.price);

        coordinates = view.findViewById(R.id.co_loc);

        save = view.findViewById(R.id.save);
        constraintLayout=view.findViewById(R.id.register_user_Layout);


        save.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        productType.setText(user.getType());
        productQuantity.setText(user.getQuantity());
        name.setText(user.getName());
        DiscountPercentage.setText(user.getDiscount());
        TotalPrice.setText(user.getPrice());
        coordinates.setText(user.getLocation());

        StorageReference reference= FirebaseStorage.getInstance().getReference(user.getImage());

        Glide.with(this)
                .load(reference)
                .into(imageView);


        constraintLayout.setPadding(50,0,50,0);



        disableEdit();


        builder.setView(view)
                .setTitle("Customer Item Details")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



        mAlertDialog= builder.create();
        //mAlertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

         mAlertDialog.show();

        mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if( !validatePhone() | !validateName()){
//                    return;
//                }
                return;
//
//                String name=contact_name.getEditText().getText().toString();
//                String phoneNo=ccp.getSelectedCountryCodeWithPlus().toString()+phone.getEditText().getText().toString();
//
//                if(phoneNo.charAt(3)=='0' && phoneNo.substring(0, 3).equals("+92"))
//                {
//                    String temp=phoneNo.substring(0,3)+phoneNo.substring(4, phoneNo.length());
//                    phoneNo=temp;
//                }
//
//                contact=new Contact(name,phoneNo);
//                mAlertDialog.dismiss();
            }
        });
        return mAlertDialog;

    }


//    private boolean validateName(){
//        String val=contact_name.getText().toString();
//        final String name_checker="^[a-zA-Z. ]+((['. ][a-zA-Z ])?[a-zA-Z. ]*)*$";
//
//        if(val.isEmpty()){
//            contact_name.setError("Field cannot be empty");
//            contact_name.requestFocus();
//            return false;
//        }
//        else if(!val.matches(name_checker))
//        {
//            contact_name.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validatePhone(){
//        String val=phone.getText().toString();
//        if(val.isEmpty()){
//            phone.setError("Field cannot be empty");
//            phone.requestFocus();
//            return false;
//        }
//        else if(val.length()<7)
//        {
//            phone.requestFocus();
//            return false;
//        }
//        return true;
//    }

    void disableEdit()
    {

        productType.setEnabled(false);
        productQuantity.setEnabled(false);
        DiscountPercentage.setEnabled(false);
        TotalPrice.setEnabled(false);
        coordinates.setEnabled(false);
        name.setEnabled(false);
        imageView.setEnabled(false);
        fab.setEnabled(false);
        save.setEnabled(false);

    }

    void enableEdit()
    {
        fullname.setEnabled(true);
        hobbies.setEnabled(true);
        dob.setEnabled(true);
        age.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        imageView.setEnabled(true);
        male.setEnabled(true);
        female.setEnabled(true);

    }

}

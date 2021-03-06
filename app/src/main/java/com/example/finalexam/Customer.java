package com.example.finalexam;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;


public class Customer extends Fragment {

    Uri imageUri;
    View view;
    ImageView imageView;
    Button fab;
    EditText productType, productQuantity, DiscountPercentage, TotalPrice, coordinates,name ;
    RadioGroup radioGroup;
    RadioButton radioButton;

    Button save;

    private StorageReference mStorageRef;
    private DatabaseReference databaseReference;
    public Customer() {
        // Required empty public constructor
    }

    private String id;
    private String n;
    private String type;
    private String quantity;
    private String discount;
    private String price;
    private String location;
    private Bitmap photo;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_PICTURE = 1999;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_GALLERY_PERMISSION_CODE = 200;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_customer, container, false);

        imageView = view.findViewById(R.id.profile_image);
        fab = view.findViewById(R.id.fab);

        productType = view.findViewById(R.id.productType);
        productQuantity= view.findViewById(R.id.quantity);
        name= view.findViewById(R.id.Name);

        DiscountPercentage = view.findViewById(R.id.discount);
        TotalPrice = view.findViewById(R.id.price);

        coordinates = view.findViewById(R.id.co_loc);

        save = view.findViewById(R.id.save);

        //radioButton=view.findViewById(gender_id);

//        radioButton.getText();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_PICTURE);
                startDialog();
            }
        });


//        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("SELECT A DATE");
//        final MaterialDatePicker mdp = builder.build();
//        dob.setKeyListener(null);
//        mdp.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
//            @Override
//            public void onPositiveButtonClick(Object selection) {
//                dob.setText(mdp.getHeaderText());
//            }
//        });
//        dob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mdp.show(getActivity().getSupportFragmentManager(), "DOB_PICKER");
//            }
//        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Customer()).commit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!validatePassword() | !validateEmail() | !validateHobbies() | !validateAge() | !validateDob() | !validateFullname() | !validateImage()) {
//                    Toast.makeText(getContext(), "Fill all Fields with no errors", Toast.LENGTH_LONG).show();
//                    return;
//                }
                addData();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Customer()).commit();
                Toast.makeText(getContext(), "Saved Successful", Toast.LENGTH_LONG).show();


            }
        });


        return view;
    }


    private void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getContext());
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to upload your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                    getActivity(),
                                    PERMISSIONS_STORAGE,
                                    MY_GALLERY_PERMISSION_CODE
                            );
                        } else {

                            Intent intent = new Intent();
                            intent.setType("image/*");
                            // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_PICTURE);


                        }

                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            }
                        }

                    }
                });
        myAlertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == MY_GALLERY_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "gallery read permission granted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setType("image/*");
                //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_PICTURE);
            } else {
                Toast.makeText(getContext(), "gallery read permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");

            imageView.setImageBitmap(photo);
        } else if (requestCode == GALLERY_PICTURE && resultCode == Activity.RESULT_OK) {


            // The following code snipet is used when Intent for single image selection is set

            imageUri = data.getData();
            //Log.d(TAG,"file uri: "+imageUri.toString());
            try {
                photo = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(photo);


        } else if (resultCode == Activity.RESULT_CANCELED) {
            //Log.d(TAG, "PHOTO is null");
            getActivity().finish();
        }
    }


    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight, true);
    }


    public void addData() {
        n = name.getText().toString();
        type = productType.getText().toString();
        quantity =productQuantity.getText().toString();
        price = TotalPrice.getText().toString();
        discount = DiscountPercentage.getText().toString();
        location = coordinates.getText().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("profile_images");


        id = databaseReference.push().getKey();

        String image = "profile_images/" + id;

        Data newData = new Data(id,n, type, quantity, price, discount, location, image);

        mStorageRef.child(id).putFile(imageUri);

        databaseReference.child(id).setValue(newData);


//        final StorageReference ref = mStorageRef.child("images/mountains.jpg");
//        UploadTask uploadTask = ref.putFile(imageUri);
//
//                User newUser=new User(id,mail,pass,name,gen,AGE,hoby,date,ref.getDownloadUrl().toString());
//
//                databaseReference.child(id).setValue(newUser);


    }


    // validation functions

//    private boolean validateFullname() {
//        String val = productType.getText().toString();
//
//        if (val.isEmpty()) {
//            productType.setError("Field cannot be empty");
//            productType.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validateImage() {
//        if (imageView.getDrawable() == null) {
//            Toast.makeText(getContext(), "Please Select an Image first", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validateEmail() {
//        String val = email.getText().toString();
//        final String emailchecker = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
//
//        if (val.isEmpty()) {
//            email.setError("Field cannot be empty");
//            email.requestFocus();
//            return false;
//        } else if (!val.matches(emailchecker)) {
//            email.requestFocus();
//            return false;
//        }
//        return true;
//
//    }
//
//    private boolean validateHobbies() {
//        String val = marketingsector.getText().toString();
//        if (val.isEmpty()) {
//            marketingsector.setError("Field cannot be empty");
//            marketingsector.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validateDob() {
//        String val = date.getText().toString();
//        if (val.isEmpty()) {
//            date.setError("Select date");
//            date.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validateAge() {
//        String val = username.getText().toString();
//        if (val.isEmpty()) {
//            username.setError("Field cannot be empty");
//            username.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validatePassword() {
//        String val = password.getText().toString();
//
//        if (val.isEmpty()) {
//            password.setError("Field cannot be empty");
//            password.requestFocus();
//            return false;
//        } else if (val.length() < 5) {
//            password.setError("Pass length must be greater than 5");
//            password.requestFocus();
//            return false;
//        }
//        return true;
//
//    }
}

package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private LocationListener mListener;
    LocationManager locationManager;
    String locationProvider;
    private String TAG = "MapsActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new Login_Fragment()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new Register_Fragment()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new Customer()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new Admin()).commit();


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    //txtMessage.setText(message);
                }
            }
        };


    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Toast.makeText(this,"Firebase Reg Id: " + regId,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Firebase Reg Id is not received yet!",Toast.LENGTH_SHORT).show();
    }
}

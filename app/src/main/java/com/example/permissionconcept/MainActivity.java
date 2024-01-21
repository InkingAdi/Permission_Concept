package com.example.permissionconcept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //initialize permission value
    Integer CAMERA_PERMISSION_REQ = 100;    //We are using this variable for comparison whether permission is given or not.
    Integer SMS_R_REQ = 101;
    Integer CONTACT_PERMISSION_REQ = 102;
    Integer STORAGE_R_REQ = 103;
    Integer STORAGE_W_REQ = 104;
    Integer FINE_LOCATION_REQ = 105;
    Integer SMS_S_REQ = 106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        When we click on camera button then it will check whether permission is already given or not.
        if YES then toast the message.
        ELSE request for permission.
         */

        findViewById(R.id.btn_camera).setOnClickListener(view -> {
            checkCameraPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_REQ);
        });

        findViewById(R.id.btn_contact).setOnClickListener(view -> {
            //Read Contact
            checkCameraPermission(Manifest.permission.READ_CONTACTS, CONTACT_PERMISSION_REQ);

        });

        findViewById(R.id.btn_read_storage).setOnClickListener(view -> {
            checkCameraPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_R_REQ);
        });

        findViewById(R.id.btn_write_storage).setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_W_REQ);
            }
        });

        findViewById(R.id.btn_location).setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQ);
            }
        });

        findViewById(R.id.btn_read_sms).setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_SMS}, SMS_R_REQ);
            }
        });

        findViewById(R.id.btn_send_sms).setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},SMS_S_REQ);
            }
        });
    }

    private void checkCameraPermission(String permi, Integer request_code) {

        //Check If Permission is granted or not
        //IF PERMISSION IS DENIED
        if(ContextCompat.checkSelfPermission(MainActivity.this, permi) == PackageManager.PERMISSION_DENIED){
            //This will display dialog box to user for granting permission
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{permi},request_code);
        }else {
            Toast.makeText(this,"User Permission Granted !",Toast.LENGTH_LONG).show();
        }

    }

    //This function will be called when user accept or decline the permission
    //Request code is used to check which permission is called this function.
    //This request code is provided when user is prompt for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_PERMISSION_REQ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera Permission Granted !", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Camera Permission Denied !", Toast.LENGTH_LONG).show();
            }
        }

        //If Requested permission is contact permission
        if(requestCode == CONTACT_PERMISSION_REQ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Read permission Granted for Contacts",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Read permission for Contacts Not Granted !",Toast.LENGTH_LONG).show();
            }
        }

        //If Requested permission is STORAGE READ permission
        if(requestCode == STORAGE_R_REQ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Read permission Granted for STORAGE",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Read permission for STORAGE Not Granted !",Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == STORAGE_W_REQ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "WRITE permission Granted for STORAGE",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"WRITE permission for STORAGE Not Granted !",Toast.LENGTH_LONG).show();
            }
        }

        //If Requested permission is FINE LOCATION
        if(requestCode == FINE_LOCATION_REQ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Location permission Granted !", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Location permission Denied !", Toast.LENGTH_SHORT).show();
            }
        }

        //READ SMS PERMISSION
        if(requestCode == SMS_R_REQ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "READ SMS permission Granted !", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "READ SMS permission Denied !", Toast.LENGTH_SHORT).show();
            }
        }

        //SEND SMS PERMISSION
        if(requestCode == SMS_S_REQ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "SEND SMS permission Granted !", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "SEND SMS permission Denied !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
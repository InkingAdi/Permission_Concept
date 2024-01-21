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
    Integer CAMERA_PERMISSION = 100;    //We are using this variable for comparison whether permission is given or not.
    Integer SMS_PERMISSION = 101;

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
            checkCameraPermission(CAMERA_PERMISSION);
        });

        findViewById(R.id.btn_contact).setOnClickListener(view -> {

        });

        findViewById(R.id.btn_read_storage).setOnClickListener(view -> {

        });

        findViewById(R.id.btn_write_storage).setOnClickListener(view -> {

        });

        findViewById(R.id.btn_location).setOnClickListener(view -> {

        });

        findViewById(R.id.btn_read_sms).setOnClickListener(view -> {

        });

        findViewById(R.id.btn_send_sms).setOnClickListener(view -> {

        });
    }

    private void checkCameraPermission(Integer request_code) {

        //Check If Permission is granted or not
        int perm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);

        //IF PERMISSION IS DENIED
        if(perm == PackageManager.PERMISSION_DENIED){
            //This will display dialog box to user for granting permission
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},request_code);
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

        if(requestCode == CAMERA_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera Permission Granted !", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Camera Permission Denied !", Toast.LENGTH_LONG).show();
            }
        }
    }
}
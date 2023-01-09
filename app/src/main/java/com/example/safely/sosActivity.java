package com.example.safely;

import static android.Manifest.permission_group.PHONE;

import static com.google.android.gms.auth.api.phone.SmsCodeAutofillClient.PermissionState.GRANTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

public class sosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
    }

    @Override
    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL PHONE) != PackageManager.PERMISSION GRANTED)
                    ActivityCompat.requestPermissions( GuardianActivity.this, new String[]{Manifest.permission.CALL PHONE},
                return;
            }
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+ txtPhone.getText().toString()));
            startActivity(callIntent);

        }
        else	{
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+ txtPhone.getText().toString()));
    }
    }
        catch (Exception ex)
    {
        ex.printStackTrace();
    }
}
}
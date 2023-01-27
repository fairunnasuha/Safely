package com.example.safely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallorMessage extends AppCompatActivity {

    Button close;
    private FirebaseUser fUser;
    static int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callor_message);

        Button call;

        call = findViewById(R.id.call);

        if (ContextCompat.checkSelfPermission(CallorMessage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(CallorMessage.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);

        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get current user
                fUser = FirebaseAuth.getInstance().getCurrentUser();
                //retrieve data

                FirebaseDatabase.getInstance().getReference().child(fUser.getUid()).child("GuardianInfo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        GuardianConstructor Guser = snapshot.getValue(GuardianConstructor.class);
                        if (Guser != null) {
                            //String phoneno = phoneNo.getText().toString();
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + Guser.getGuardianPhone()));
                            startActivity(i);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                close = findViewById(R.id.close);
                close.setOnClickListener(view -> {
                    startActivity(new Intent(CallorMessage.this, sosActivity.class));
                });

            }
        });
    }
}
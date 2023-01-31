package com.example.safely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sosActivity extends AppCompatActivity {
    Button sosbtn;
    private FirebaseUser fUser;
    static int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        if (ContextCompat.checkSelfPermission(sosActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(sosActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);

        }

        sosbtn = findViewById(R.id.sosbtn);
        sosbtn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Long press", Toast.LENGTH_SHORT).show();
                    super.onLongPress(e);
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
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Double Tap press", Toast.LENGTH_SHORT).show();
                    super.onDoubleTap(e);
                    startActivity(new Intent(sosActivity.this, MapsActivity.class));
                    return false;
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
    }
}
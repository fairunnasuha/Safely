package com.example.safely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;
    FirebaseAuth mAuth;
    Button guardian;
    Button test;
    Button aboutus;
    Button emergency;
    Button profile;
    Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();
        guardian=findViewById(R.id.guardian);
        guardian.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,Guardian.class));
        });
        test=findViewById(R.id.acctest);
        test.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,DetectVibration.class));
        });
        aboutus=findViewById(R.id.aboutus);
        aboutus.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,AboutUs.class));
        });
        emergency=findViewById(R.id.emergency);
        emergency.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,sosActivity.class));
        });
        profile=findViewById(R.id.profile);
        profile.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,Profile.class));
        });
        location=findViewById(R.id.location);
        location.setOnClickListener(view -> {
            startActivity(new Intent(SecondActivity.this,MapsActivity.class));
        });


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(SecondActivity.this, MainActivity.class));
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.chat_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(SecondActivity.this,MainActivity.class));
        }
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }

}
package com.example.safely;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Guardian extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    private TextView txtname;
    private TextView txtphone;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);

        txtname = (TextView) findViewById(R.id.txtname);
        txtphone = (TextView) findViewById(R.id.txtphone);
        button = (Button) findViewById(R.id.addbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }
    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    @Override
    public void applyTexts(String name, String phone) {
        txtname.setText(name);
        txtphone.setText(phone);
    }
}
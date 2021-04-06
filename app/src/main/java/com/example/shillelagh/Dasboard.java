package com.example.shillelagh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Dasboard extends AppCompatActivity {

    private static final  int REQUEST_CODE_SPEECH_INPUT = 1000;
    private Button navibtn;
    private EditText editt1 , editt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        navibtn = findViewById(R.id.navibtn);
        editt1 = findViewById(R.id.editt1);
        editt2 = findViewById(R.id.editt2);
        navibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dest = editt2.getText().toString().trim();
              Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+dest+"&mode=w"));

                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

}
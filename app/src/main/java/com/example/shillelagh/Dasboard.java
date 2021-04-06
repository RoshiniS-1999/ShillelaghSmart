package com.example.shillelagh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class Dasboard extends AppCompatActivity {
ImageButton navigation;
TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        navigation = (ImageButton)findViewById(R.id.navigation);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    if(status == TextToSpeech.SUCCESS){
                        int Lang = textToSpeech.setLanguage(Locale.ENGLISH);
                    }
            }
        });

         navigation.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String inputdest = "please give destination";

                 int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                 Intent i = new Intent(getApplicationContext(),Navigation.class);
                 startActivity(i);

             }
         });


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
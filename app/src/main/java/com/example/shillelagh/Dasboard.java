package com.example.shillelagh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    }
}
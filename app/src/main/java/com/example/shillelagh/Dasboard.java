package com.example.shillelagh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.MotionEvent;
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

        navigation.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gesturedetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    String inputdest = " You've chosen Navigation, please double tap to confirm your selection ";
                    int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                    return super.onSingleTapUp(e);
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    String inputdest = "please give destination";

                    int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                    Intent i = new Intent(getApplicationContext(),Navigation.class);
                    startActivity(i);
                    return super.onDoubleTap(e);
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesturedetector.onTouchEvent(event);
                return false;
            }
        });

       /* navigation.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String inputdest = "please give destination";

                 int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                 Intent i = new Intent(getApplicationContext(),Navigation.class);
                 startActivity(i);

             }
         });*/
    }
}
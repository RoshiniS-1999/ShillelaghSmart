package com.example.shillelagh;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
import java.util.Locale;

public class Adapter extends PagerAdapter {

    private List<model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    TextToSpeech textToSpeech;

    public Adapter(List<model> models, Context context) {
        this.models = models;
        this.context = context;
    }







    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       layoutInflater = LayoutInflater.from(context);
      final View view = layoutInflater.inflate(R.layout.item,container,false);
        ImageView imageView;
        TextView title;
     
         imageView = view.findViewById(R.id.cardimage);
         title = view.findViewById(R.id.cardtext);
         imageView.setImageResource(models.get(position).getImage());
         title.setText(models.get(position).getTitle());


         container.addView(view,0);



        textToSpeech = new TextToSpeech(view.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int Lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });



       if(position==0){



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
           view.setOnTouchListener(new View.OnTouchListener() {


               GestureDetector gesturedetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener(){

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
                       Intent i = new Intent(view.getContext(),Navigation.class);
                       context.startActivity(i);
                       return super.onDoubleTap(e);
                   }
               });
               @Override
               public boolean onTouch(View v, MotionEvent event) {
                   gesturedetector.onTouchEvent(event);
                   return false;
               }


               


           });



       }


         return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}

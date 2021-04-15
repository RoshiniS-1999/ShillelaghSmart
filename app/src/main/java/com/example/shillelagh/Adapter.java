package com.example.shillelagh;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class Adapter extends PagerAdapter {

    private List<model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    TextToSpeech textToSpeech;
    private FirebaseUser user;
    private String userId;
    String phone;
    private DatabaseReference reference;
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();
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

       if(position == 2){view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
           view.setOnTouchListener(new View.OnTouchListener() {


               GestureDetector gesturedetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener(){

                   @Override
                   public boolean onSingleTapUp(MotionEvent e) {
                       String inputdest = " You've chosen Emergency, please double tap to confirm your selection ";
                       int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                       return super.onSingleTapUp(e);
                   }

                   @Override
                   public boolean onDoubleTap(MotionEvent e) {
                       String inputdest = "Calling your emergency contact!!!";

                       int speech = textToSpeech.speak(inputdest,TextToSpeech.QUEUE_FLUSH,null);
                       reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() { @Override public void onDataChange(@NonNull DataSnapshot snapshot) { User userProfile = snapshot.getValue(User.class); if(userProfile!= null){ phone = userProfile.emerg_contact; callnumber(); } } @Override public void onCancelled(@NonNull DatabaseError error) { Toast.makeText(view.getContext(),"Something wrong happened",Toast.LENGTH_LONG).show(); } });
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

    private void  callnumber(){
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone));
                context.startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone));
                context.startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

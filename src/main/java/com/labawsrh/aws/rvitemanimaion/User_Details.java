package com.labawsrh.aws.rvitemanimaion;

import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

public class User_Details {
    public  static  String user_name  = "";
    public   static   boolean connected = true ;
    public   static  int tier  = 0 ;
    public   static LinearLayout linearLayout ;

    public static  void  set_lottie (LottieAnimationView lottieAnimationView ){

         if (connected){
             lottieAnimationView.setVisibility(View.GONE);
             lottieAnimationView.cancelAnimation();

         }
         else {

             lottieAnimationView.setVisibility(View.VISIBLE);
             lottieAnimationView.playAnimation();
         }
    }


}

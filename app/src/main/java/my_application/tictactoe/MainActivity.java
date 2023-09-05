package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView FLine,SLine,TLine,FoLine,FBox,SBox,TBox,WinLine;
    TextView StartAnimAgain;
    TextView Tap;
    TextView Settings;
    long pressedTime;
    Toast toast;
    Button Proceed,highScore;
    ImageButton Next;
//    RadioGroup XORadioGroup;
//    RadioButton X,O,Automatic,Manual;
    public static int Act_start=0;
    public static int onMain=0;
    public static int player1Score =0;
    public static int player2Score =0;
    public static String highScore_pName="-";
    public static int highScore_pScore=0;
    public static int start=0;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferencesData;

    @SuppressLint({"MissingInflatedId", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if(Act_start==0){
            Intent intent_act4=new Intent(MainActivity.this,MainActivity4.class);
            startActivity(intent_act4);
        }

        FLine=findViewById(R.id.FLine);
        SLine=findViewById(R.id.SLine);
        TLine=findViewById(R.id.TLine);
        FoLine=findViewById(R.id.FoLine);
        FBox=findViewById(R.id.FBox);
        SBox=findViewById(R.id.SBox);
        TBox=findViewById(R.id.TBox);
        WinLine=findViewById(R.id.WinLine);
        Tap=findViewById(R.id.Tap);
        Next=findViewById(R.id.Next);
        Settings=findViewById(R.id.Settings);

        Proceed=findViewById(R.id.Proceed);
        highScore=findViewById(R.id.highScore);
        StartAnimAgain=findViewById(R.id.StartAnimAgain);

        Tap.setVisibility(View.INVISIBLE);
        BounceProceed();

        StartAnimAgain.setOnClickListener(v -> startAnimation());

        Proceed.setOnClickListener(v -> GotoActivity2());

        startAnimation();

        Next.setOnClickListener(v -> GotoActivity2());

        Settings.setOnClickListener(v -> {
            onMain=0;
            Intent intent=new Intent(MainActivity.this,MainActivity4.class);
            startActivity(intent);
        });

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act8_intent=new Intent(MainActivity.this, MainActivity8.class);
                startActivity(act8_intent);
            }
        });

        sharedPreferencesData=getSharedPreferences("game_data",MODE_PRIVATE);
        highScore_pScore=sharedPreferencesData.getInt("HighScore",0);
        highScore_pName=sharedPreferencesData.getString("Name","-");

//        editor=sharedPreferencesData.edit();
//        editor.putInt("HighScore",highScore_pScore);
//        editor.putString("Name",highScore_pName);
//        editor.commit();
    }

    private void startAnimation() {
        Tap.setVisibility(View.INVISIBLE);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.anim1);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.anim2);
        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.anim3);
        Animation animation4 = AnimationUtils.loadAnimation(this,R.anim.anim4);
        FLine.startAnimation(animation1);
        SLine.startAnimation(animation2);
        TLine.startAnimation(animation3);
        FoLine.startAnimation(animation4);

        Animation animation5 = AnimationUtils.loadAnimation(this,R.anim.anim5);
        WinLine.startAnimation(animation5);
        animation5.setRepeatCount(1);
        Tap.setVisibility(View.VISIBLE);
        TapAnim();
    }

    private void GotoActivity2(){
        onMain=1;
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
            toast.cancel();
        } else {
            toast=Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT);
            toast.show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public void BounceProceed(){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.bounce);
        Proceed.startAnimation(anim);
    }

    public void TapAnim() {
        Animation anim = new AlphaAnimation(0.01f, 1.0f);
        anim.setDuration(1200);
        anim.setStartOffset(70);
        anim.setRepeatCount(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        Tap.startAnimation(anim);
    }
}

//        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
//        Proceed.startAnimation(animation);

//    Animation anim=new AlphaAnimation(0.01f,1.0f);
//        anim.setDuration(1000);
//                anim.setStartOffset(10);
//                anim.setRepeatCount(Animation.REVERSE);
//                anim.setRepeatCount(Animation.INFINITE);
//                Proceed.startAnimation(anim);



//new CountDownTimer(5000, 1000) {
//@SuppressLint("SetTextI18n")
//@Override
//public void onTick(long millisUntilFinished) {
//        dialog.show();
//        countdown.setText(""+millisUntilFinished/1000);
//        flag=0;
//        }
//
//@Override
//public void onFinish() {
//        dialog.dismiss();
//        }
//        }.start();


//dialog=new Dialog(MainActivity.this);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.setCancelable(false);
//        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;





//    final Animation in = new AlphaAnimation(0.0f, 1.0f);
//        in.setDuration(1200);
//final Animation out = new AlphaAnimation(0.0f, 1.0f);
//        out.setDuration(1200);
//
//        out.setAnimationListener(new Animation.AnimationListener() {
//@Override
//public void onAnimationStart(Animation animation) {
//
//        }
//
//@Override
//public void onAnimationEnd(Animation animation) {
//        Continue.setVisibility(View.VISIBLE);
//        Continue.startAnimation(in);
//        }
//
//@Override
//public void onAnimationRepeat(Animation animation) {
//
//        }
//        });
//
//        Continue.startAnimation(out);
//        out.setRepeatCount(Animation.INFINITE);

//<TextView
//        android:id="@+id/Continue"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:hint="Click on the PROCEED button to continue..."
//                android:layout_alignParentBottom="true"
//                android:layout_marginBottom="150dp"
//                android:layout_centerHorizontal="true"
//                android:textSize="17sp"/>
package my_application.tictactoe;

import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity2 extends MainActivity {

    TextView darkBackground;
    TextView VSDark,NameHeadingDark,infoScreenDark;
    TextView VS,NameHeading,P1BoxBorder,P2BoxBorder,infoScreen;
    CountDownTimer countdown;
    Button StartGame,StartGameDark;
    ImageButton BackBtn,BackBtnDark;
    ImageButton Next2,Next2Dark;
    EditText player1Name;
    EditText player2Name;
    TextView TickP1,TickP2;
    TextView CrossP1,CrossP2;
    TextView P1NameBox,P2NameBox;
    TextView MaxChar,MaxCharDark;
    TextView EnterNames,EnterNamesDark;
    TextView ProceedMessage,ProceedMessageDark;
    public static String P1name,P2name;
    Dialog alert;
    int flag=0;
    SharedPreferences sharedPreferencesNames;
    SharedPreferences.Editor editor;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        MainActivity.onMain=1;
        MainActivity.player1Score =0;
        MainActivity.player2Score =0;

        StartGame=findViewById(R.id.StartGame);
        player1Name=findViewById(R.id.player1Name);
        player2Name=findViewById(R.id.player2Name);
        TickP1=findViewById(R.id.TickP1);
        TickP2=findViewById(R.id.TickP2);
        CrossP1=findViewById(R.id.CrossP1);
        CrossP2=findViewById(R.id.CrossP2);
        P1NameBox=findViewById(R.id.P1NameBox);
        P2NameBox=findViewById(R.id.P2NameBox);
        MaxChar=findViewById(R.id.MaxChar);
        EnterNames=findViewById(R.id.EnterNames);
        ProceedMessage=findViewById(R.id.ProceedMessage);
        BackBtn=findViewById(R.id.BackBtn);
        Next2=findViewById(R.id.Next2);
        VS=findViewById(R.id.VS);
        NameHeading=findViewById(R.id.NameHeading);
        P1BoxBorder=findViewById(R.id.P1BoxBorder);
        P2BoxBorder=findViewById(R.id.P2BoxBorder);
        infoScreen=findViewById(R.id.InfoScreen);

        StartGameDark=findViewById(R.id.StartGameDark);
        MaxCharDark=findViewById(R.id.MaxCharDark);
        EnterNamesDark=findViewById(R.id.EnterNamesDark);
        ProceedMessageDark=findViewById(R.id.ProceedMessageDark);
        BackBtnDark=findViewById(R.id.BackBtnDark);
        Next2Dark=findViewById(R.id.Next2Dark);
        VSDark=findViewById(R.id.VSDark);
        NameHeadingDark=findViewById(R.id.NameHeadingDark);
        infoScreenDark=findViewById(R.id.InfoScreenDark);
        darkBackground=findViewById(R.id.DarkBackground);



        if(MainActivity4.getMode==0){

            VS.setVisibility(View.VISIBLE);
            NameHeading.setVisibility(View.VISIBLE);
            StartGame.setVisibility(View.VISIBLE);
            infoScreen.setVisibility(View.VISIBLE);
            BackBtn.setVisibility(View.VISIBLE);
            Next2.setVisibility(View.VISIBLE);


            if(MainActivity4.X_or_O==1){
                player1Name.setHint("Player 1 Name (X)");
                player2Name.setHint("Player 2 Name (O)");
            }else if(MainActivity4.X_or_O==0){
                player1Name.setHint("Player 1 Name (O)");
                player2Name.setHint("Player 2 Name (X)");
            }

            BackBtn.setOnClickListener(v -> {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent1);
            });

            Next2.setOnClickListener(v -> {
                if(!(player1Name.getText().toString().trim().length() == 0) && !(player2Name.getText().toString().trim().length() == 0)) {
                    flag=0;
                    alert.show();
                }else{
                    MaxChar.setVisibility(View.INVISIBLE);
                    ProceedMessage.setVisibility(View.INVISIBLE);
                    EnterNames.setVisibility(View.VISIBLE);
                }
            });

            Bounce(1);

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!(player1Name.getText().toString().trim().length() == 0)){
                        TickP1.setVisibility(View.VISIBLE);
                        CrossP1.setVisibility(View.INVISIBLE);
                    }else{
                        CrossP1.setVisibility(View.VISIBLE);
                        TickP1.setVisibility(View.INVISIBLE);
                    }
                    if(!(player2Name.getText().toString().trim().length() == 0)){
                        TickP2.setVisibility(View.VISIBLE);
                        CrossP2.setVisibility(View.INVISIBLE);
                    }else{
                        CrossP2.setVisibility(View.VISIBLE);
                        TickP2.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!(player1Name.getText().toString().trim().length() == 0)) {
                        player1Name.setCursorVisible(false);
                    }else{
                        player1Name.setCursorVisible(true);
                    }

                    if(!(player2Name.getText().toString().trim().length() == 0)) {
                        player2Name.setCursorVisible(false);
                    }else{
                        player2Name.setCursorVisible(true);
                    }
                }
            };

            TextWatcher textWatcher1 = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TickP1.getVisibility()==View.VISIBLE) {
                        MaxChar.setVisibility(View.INVISIBLE);
                    }else {
                        if(EnterNames.getVisibility()==View.VISIBLE){
                            EnterNames.setVisibility(View.INVISIBLE);
                        }
                        MaxChar.setVisibility(View.VISIBLE);
                    }

                    if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                        MaxChar.setVisibility(View.INVISIBLE);
                        EnterNames.setVisibility(View.INVISIBLE);
                        ProceedMessage.setVisibility(View.VISIBLE);
                    }else{
                        ProceedMessage.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            TextWatcher textWatcher2 = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TickP2.getVisibility()==View.VISIBLE) {
                        MaxChar.setVisibility(View.INVISIBLE);
                    }else {
                        if(EnterNames.getVisibility()==View.VISIBLE){
                            EnterNames.setVisibility(View.INVISIBLE);
                        }
                        MaxChar.setVisibility(View.VISIBLE);
                    }

                    if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                        MaxChar.setVisibility(View.INVISIBLE);
                        EnterNames.setVisibility(View.INVISIBLE);
                        ProceedMessage.setVisibility(View.VISIBLE);
                    }else{
                        ProceedMessage.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            player1Name.addTextChangedListener(textWatcher);
            player2Name.addTextChangedListener(textWatcher);

            alert = new Dialog(this);
            alert.setContentView(R.layout.alert);
            Button cancelButton=alert.findViewById(R.id.cancelButton);
            Button continueButton=alert.findViewById(R.id.continueButton);

            alert.setOnShowListener(dialog -> {


                cancelButton.setOnClickListener(v -> {
                    countdown.cancel();
                    alert.dismiss();
                });

                countdown = new CountDownTimer(5000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(flag==0) {
                            continueButton.setText("Continue (" + ((millisUntilFinished / 1000) + 1) + ")");
                        }else if(flag==1){
                            countdown.cancel();
                            alert.dismiss();
                        }
                    }

                    @Override
                    public void onFinish() {
                        countdown.cancel();
                        alert.dismiss();
                        if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                            GotoActivity3();
                        }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                            GotoActivity5();
                        }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                            GotoActivity6();
                        }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                            GotoActivity7();
                        }
                    }
                }.start();

                continueButton.setOnClickListener(v -> {
                    countdown.cancel();
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                        GotoActivity7();
                    }
                    alert.dismiss();
                    flag=1;
                });
            });

            alert.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

            alert.setOnKeyListener((dialog, keyCode, event) -> {
                if(keyCode== KeyEvent.KEYCODE_BACK){
                    //countdown.cancel();
                    //alert.dismiss();
                }
                return true;
            });

            StartGame.setOnClickListener(v -> {

                if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                    MaxChar.setVisibility(View.INVISIBLE);
                    EnterNames.setVisibility(View.INVISIBLE);
                    ProceedMessage.setVisibility(View.VISIBLE);
                }else{
                    ProceedMessage.setVisibility(View.INVISIBLE);
                }

                if ((player1Name.getText().toString().trim().length() == 0 && player2Name.getText().toString().trim().length() == 0) || ((player1Name.getText().toString().trim().length() == 0) && !(player2Name.getText().toString().trim().length() == 0)) || ((player2Name.getText().toString().trim().length() == 0) && !(player1Name.getText().toString().trim().length() == 0))) {
                    if(MaxChar.getVisibility()==View.VISIBLE){
                        MaxChar.setVisibility(View.INVISIBLE);
                    }
                    EnterNames.setVisibility(View.VISIBLE);
                } else{
                    flag=0;
                    alert.show();
                }
            });

            player1Name.setOnTouchListener((v, event) -> {
                if(TickP1.getVisibility()==View.VISIBLE) {
                    MaxChar.setVisibility(View.INVISIBLE);
                }else{
                    if(EnterNames.getVisibility()==View.VISIBLE){
                        EnterNames.setVisibility(View.INVISIBLE);
                    }
                    MaxChar.setVisibility(View.VISIBLE);
                }
                player1Name.setCursorVisible(true);
                return false;
            });

            player1Name.addTextChangedListener(textWatcher1);

            player2Name.setOnTouchListener((v, event) -> {
                if(TickP2.getVisibility()==View.VISIBLE) {
                    MaxChar.setVisibility(View.INVISIBLE);
                }else{
                    if(EnterNames.getVisibility()==View.VISIBLE){
                        EnterNames.setVisibility(View.INVISIBLE);
                    }
                    MaxChar.setVisibility(View.VISIBLE);
                }
                player2Name.setCursorVisible(true);
                return false;
            });

            player2Name.addTextChangedListener(textWatcher2);


        }else{

            darkBackground.setVisibility(View.VISIBLE);
            VSDark.setVisibility(View.VISIBLE);
            NameHeadingDark.setVisibility(View.VISIBLE);
            StartGameDark.setVisibility(View.VISIBLE);
            infoScreenDark.setVisibility(View.VISIBLE);
            BackBtnDark.setVisibility(View.VISIBLE);
            Next2Dark.setVisibility(View.VISIBLE);


            if(MainActivity4.X_or_O==1){
                player1Name.setHint("Player 1 Name (X)");
                player2Name.setHint("Player 2 Name (O)");
            }else if(MainActivity4.X_or_O==0){
                player1Name.setHint("Player 1 Name (O)");
                player2Name.setHint("Player 2 Name (X)");
            }

            BackBtnDark.setOnClickListener(v -> {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent1);
            });

            Next2Dark.setOnClickListener(v -> {
                if(!(player1Name.getText().toString().trim().length() == 0) && !(player2Name.getText().toString().trim().length() == 0)) {
                    flag=0;
                    alert.show();
                }else{
                    MaxCharDark.setVisibility(View.INVISIBLE);
                    ProceedMessageDark.setVisibility(View.INVISIBLE);
                    EnterNamesDark.setVisibility(View.VISIBLE);
                }
            });

            Bounce(0);

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!(player1Name.getText().toString().trim().length() == 0)){
                        TickP1.setVisibility(View.VISIBLE);
                        CrossP1.setVisibility(View.INVISIBLE);
                    }else{
                        CrossP1.setVisibility(View.VISIBLE);
                        TickP1.setVisibility(View.INVISIBLE);
                    }
                    if(!(player2Name.getText().toString().trim().length() == 0)){
                        TickP2.setVisibility(View.VISIBLE);
                        CrossP2.setVisibility(View.INVISIBLE);
                    }else{
                        CrossP2.setVisibility(View.VISIBLE);
                        TickP2.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!(player1Name.getText().toString().trim().length() == 0)) {
                        player1Name.setCursorVisible(false);
                    }else{
                        player1Name.setCursorVisible(true);
                    }

                    if(!(player2Name.getText().toString().trim().length() == 0)) {
                        player2Name.setCursorVisible(false);
                    }else{
                        player2Name.setCursorVisible(true);
                    }
                }
            };

            TextWatcher textWatcher1 = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TickP1.getVisibility()==View.VISIBLE) {
                        MaxCharDark.setVisibility(View.INVISIBLE);
                    }else {
                        if(EnterNamesDark.getVisibility()==View.VISIBLE){
                            EnterNamesDark.setVisibility(View.INVISIBLE);
                        }
                        MaxCharDark.setVisibility(View.VISIBLE);
                    }

                    if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                        MaxCharDark.setVisibility(View.INVISIBLE);
                        EnterNamesDark.setVisibility(View.INVISIBLE);
                        ProceedMessageDark.setVisibility(View.VISIBLE);
                    }else{
                        ProceedMessageDark.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            TextWatcher textWatcher2 = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TickP2.getVisibility()==View.VISIBLE) {
                        MaxCharDark.setVisibility(View.INVISIBLE);
                    }else {
                        if(EnterNamesDark.getVisibility()==View.VISIBLE){
                            EnterNamesDark.setVisibility(View.INVISIBLE);
                        }
                        MaxCharDark.setVisibility(View.VISIBLE);
                    }

                    if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                        MaxCharDark.setVisibility(View.INVISIBLE);
                        EnterNamesDark.setVisibility(View.INVISIBLE);
                        ProceedMessageDark.setVisibility(View.VISIBLE);
                    }else{
                        ProceedMessageDark.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            player1Name.addTextChangedListener(textWatcher);
            player2Name.addTextChangedListener(textWatcher);

            alert = new Dialog(this);
            alert.setContentView(R.layout.alert);
            Button cancelButton=alert.findViewById(R.id.cancelButton);
            Button continueButton=alert.findViewById(R.id.continueButton);

            alert.setOnShowListener(dialog -> {


                cancelButton.setOnClickListener(v -> {
                    countdown.cancel();
                    alert.dismiss();
                });

                countdown = new CountDownTimer(5000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(flag==0) {
                            continueButton.setText("Continue (" + ((millisUntilFinished / 1000) + 1) + ")");
                        }else if(flag==1){
                            countdown.cancel();
                            alert.dismiss();
                        }
                    }

                    @Override
                    public void onFinish() {
                        countdown.cancel();
                        alert.dismiss();
                        if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                            GotoActivity3Dark();
                        }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                            GotoActivity5Dark();
                        }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                            GotoActivity6Dark();
                        }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                            GotoActivity7Dark();
                        }
                    }
                }.start();

                continueButton.setOnClickListener(v -> {
                    countdown.cancel();
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                        GotoActivity3Dark();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                        GotoActivity5Dark();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                        GotoActivity6Dark();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                        GotoActivity7Dark();
                    }
                    alert.dismiss();
                    flag=1;
                });
            });

            alert.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

            alert.setOnKeyListener((dialog, keyCode, event) -> {
                if(keyCode== KeyEvent.KEYCODE_BACK){
                    //countdown.cancel();
                    //alert.dismiss();
                }
                return true;
            });

            StartGameDark.setOnClickListener(v -> {

                if(TickP1.getVisibility()==View.VISIBLE && TickP2.getVisibility()==View.VISIBLE){
                    MaxCharDark.setVisibility(View.INVISIBLE);
                    EnterNamesDark.setVisibility(View.INVISIBLE);
                    ProceedMessageDark.setVisibility(View.VISIBLE);
                }else{
                    ProceedMessageDark.setVisibility(View.INVISIBLE);
                }

                if ((player1Name.getText().toString().trim().length() == 0 && player2Name.getText().toString().trim().length() == 0) || ((player1Name.getText().toString().trim().length() == 0) && !(player2Name.getText().toString().trim().length() == 0)) || ((player2Name.getText().toString().trim().length() == 0) && !(player1Name.getText().toString().trim().length() == 0))) {
                    if(MaxCharDark.getVisibility()==View.VISIBLE){
                        MaxCharDark.setVisibility(View.INVISIBLE);
                    }
                    EnterNamesDark.setVisibility(View.VISIBLE);
                } else{
                    flag=0;
                    alert.show();
                }
            });

            player1Name.setOnTouchListener((v, event) -> {
                if(TickP1.getVisibility()==View.VISIBLE) {
                    MaxCharDark.setVisibility(View.INVISIBLE);
                }else{
                    if(EnterNamesDark.getVisibility()==View.VISIBLE){
                        EnterNamesDark.setVisibility(View.INVISIBLE);
                    }
                    MaxCharDark.setVisibility(View.VISIBLE);
                }
                player1Name.setCursorVisible(true);
                return false;
            });

            player1Name.addTextChangedListener(textWatcher1);

            player2Name.setOnTouchListener((v, event) -> {
                if(TickP2.getVisibility()==View.VISIBLE) {
                    MaxCharDark.setVisibility(View.INVISIBLE);
                }else{
                    if(EnterNamesDark.getVisibility()==View.VISIBLE){
                        EnterNamesDark.setVisibility(View.INVISIBLE);
                    }
                    MaxCharDark.setVisibility(View.VISIBLE);
                }
                player2Name.setCursorVisible(true);
                return false;
            });

            player2Name.addTextChangedListener(textWatcher2);


        }


    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent1);
        //Disabled back button
    }

    private void GotoActivity3(){
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(MainActivity3.P1Name,P1name);
        intent.putExtra(MainActivity3.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity5(){
        Intent intent = new Intent(MainActivity2.this, MainActivity5.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(MainActivity5.P1Name,P1name);
        intent.putExtra(MainActivity5.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity6(){
        Intent intent = new Intent(MainActivity2.this, MainActivity6.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(MainActivity6.P1Name,P1name);
        intent.putExtra(MainActivity6.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity7(){
        Intent intent = new Intent(MainActivity2.this, MainActivity7.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(MainActivity7.P1Name,P1name);
        intent.putExtra(MainActivity7.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity3Dark(){
        Intent intent = new Intent(MainActivity2.this, Darkmode1Activity.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(Darkmode1Activity.P1Name,P1name);
        intent.putExtra(Darkmode1Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity5Dark(){
        Intent intent = new Intent(MainActivity2.this, Darkmode2Activity.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(Darkmode2Activity.P1Name,P1name);
        intent.putExtra(Darkmode2Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity6Dark(){
        Intent intent = new Intent(MainActivity2.this, Darkmode3Activity.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(Darkmode3Activity.P1Name,P1name);
        intent.putExtra(Darkmode3Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity7Dark(){
        Intent intent = new Intent(MainActivity2.this, Darkmode4Activity.class);

        P1name=player1Name.getText().toString().trim();
        P2name=player2Name.getText().toString().trim();

        intent.putExtra(Darkmode4Activity.P1Name,P1name);
        intent.putExtra(Darkmode4Activity.P2Name,P2name);

        startActivity(intent);
    }

    public void Bounce(int i){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.bounce);
        if(i==1) {
            StartGame.startAnimation(anim);
        }else{
            StartGameDark.startAnimation(anim);
        }
    }
}

//<ImageButton
//        android:id="@+id/BackBtn"
//                android:layout_width="70dp"
//                android:layout_height="60dp"
//                android:src="@drawable/backbutton"
//                android:layout_marginStart="15dp"
//                android:layout_alignParentBottom="true"
//                android:layout_marginBottom="10dp"/>

//#AA506F

//        BackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
//                startActivity(intent1);
//            }
//        });
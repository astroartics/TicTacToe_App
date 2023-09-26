package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Darkmode4Activity extends AppCompatActivity {

    int gameStarted=0;
    TextView autorestartMessage;
    Animation animation;
    TextView GameOver,GameOverBack;
    TextView Settings,SettingsBorder;
    CountDownTimer countdown,countdown2;
    Dialog alert;
    private int win=0;
    private TextView b1,b2,b3,b4,b5,b6,b7,b8,b9;
    private TextView B1,B2,B3,B4,B5,B6,B7,B8,B9;
    private TextView box1,box2,box3,box4,box5,box6,box7,box8,box9;
    private TextView line1,line2,line3,line4,line5,line6,line7,line8;
    private TextView playerScores1,playerScores2;
    private TextView winnerDeclaration;
    private TextView P1,P2;
    private TextView P1arrow,P2arrow;
    private TextView P1ScoreArrow,P2ScoreArrow;
    private int turn=0;
    private int count=0;
    //    private int player1Score=0;
//    private int player2Score=0;
    Button resetScoresBtn;
    ImageButton Back;
    public static final String P1Name="NAME1";
    public static final String P2Name="NAME2";
    private String name1,name2;
    public static int toSettings_act7=0;


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n", "UseCompatLoadingForDrawables", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darkmode4);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        MainActivity.onMain=1;

        Intent receiverIntent = getIntent();
        name1=receiverIntent.getStringExtra(P1Name);
        name2=receiverIntent.getStringExtra(P2Name);

        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);
        box9 = findViewById(R.id.box9);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);

        playerScores1=findViewById(R.id.playerScores1);
        playerScores2=findViewById(R.id.playerScores2);
        resetScoresBtn=findViewById(R.id.resetScoresBtn);
        winnerDeclaration=findViewById(R.id.winnerDeclaration);
        P1=findViewById(R.id.P1);
        P2=findViewById(R.id.P2);
        P1arrow=findViewById(R.id.P1arrow);
        P2arrow=findViewById(R.id.P2arrow);
        P1ScoreArrow=findViewById(R.id.P1ScoreArrow);
        P2ScoreArrow=findViewById(R.id.P2ScoreArrow);
        Back=findViewById(R.id.Back);
        Settings=findViewById(R.id.Settings_act7);
        SettingsBorder=findViewById(R.id.SettingsBorder);
        GameOver=findViewById(R.id.GameOver);
        GameOverBack=findViewById(R.id.GameOverBack);
        autorestartMessage=findViewById(R.id.autorestartMessage);




        Settings.setOnClickListener(v -> {
            toSettings_act7=1;
            Intent intent_darkSettings=new Intent(getApplicationContext(),DarkSettings.class);
            startActivity(intent_darkSettings);
        });









        BounceButtons(1);

        Back.setOnClickListener(v -> {
            if(gameStarted==1) {
                Dialog warning = new Dialog(this);
                warning.setContentView(R.layout.settings_alert);
                Button yes = warning.findViewById(R.id.yes);
                Button no = warning.findViewById(R.id.no);
                TextView text = warning.findViewById(R.id.text);
                text.setText("All your progress will be lost.\nDo you want to go back to\nthe previous screen?");

                yes.setOnClickListener(v1 -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                });

                no.setOnClickListener(v12 -> {
                    warning.cancel();
                });

                warning.setCanceledOnTouchOutside(false);
                warning.show();
            }else{
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);

        B1=findViewById(R.id.B1);
        B2=findViewById(R.id.B2);
        B3=findViewById(R.id.B3);
        B4=findViewById(R.id.B4);
        B5=findViewById(R.id.B5);
        B6=findViewById(R.id.B6);
        B7=findViewById(R.id.B7);
        B8=findViewById(R.id.B8);
        B9=findViewById(R.id.B9);

        turnDisplay(P1,P2);
        turnDisplayArrow(P1arrow,P2arrow);

        if(MainActivity.player1Score > MainActivity.player2Score){
            P1ScoreArrow.setText("▲");
            P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_green));
            P2ScoreArrow.setText("▼");
            P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.light_red));
        }else if(MainActivity.player2Score > MainActivity.player1Score){
            P2ScoreArrow.setText("▲");
            P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_green));
            P1ScoreArrow.setText("▼");
            P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.light_red));
        }else{
            P1ScoreArrow.setText("-");
            P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_grey));
            P2ScoreArrow.setText("-");
            P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_grey));
        }

        //playerScores.setText(name1+" -  0\n----------------------------\n"+name2+" -  0");
        playerScores1.setText(name1+" -  "+MainActivity.player1Score);
        playerScores2.setText(name2+" -  "+MainActivity.player2Score);

        P1arrow.setOnClickListener(v -> Toast.makeText(this, "Turn", Toast.LENGTH_SHORT).show());
        P2arrow.setOnClickListener(v -> Toast.makeText(this, "Turn", Toast.LENGTH_SHORT).show());

        P1ScoreArrow.setOnClickListener(v -> Toast.makeText(this, "This indicates who is winning/losing", Toast.LENGTH_SHORT).show());
        P2ScoreArrow.setOnClickListener(v -> Toast.makeText(this, "This indicates who is winning/losing", Toast.LENGTH_SHORT).show());





        resetScoresBtn.setOnClickListener(v -> {

            alert = new Dialog(this);
            alert.setContentView(R.layout.buttons_alert_dialog);
            Button yes= alert.findViewById(R.id.Yes);
            Button no= alert.findViewById(R.id.No);

            alert.setOnShowListener(dialog -> {

                alert.setCanceledOnTouchOutside(false);
                no.setOnClickListener(V -> {
                    alert.dismiss();
                });

                yes.setOnClickListener(V -> {
                    resetScore();
                    alert.dismiss();
                });
            });

            alert.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

            alert.setOnKeyListener((dialog, keyCode, event) -> {
                if(keyCode== KeyEvent.KEYCODE_BACK){

                }
                return true;
            });

            if(MainActivity.player1Score !=0 || MainActivity.player2Score !=0) {
                alert.show();
            }else{
                Toast.makeText(this, "Scores are already set to 0!", Toast.LENGTH_SHORT).show();
            }
        });






        box1.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B1);
            box1.setShowSoftInputOnFocus(false);
            box1.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box1.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box1.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box1.setEnabled(false);
            return false;
        });

        box2.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B2);
            box2.setShowSoftInputOnFocus(false);
            box2.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box2.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box2.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box2.setEnabled(false);
            return false;
        });

        box3.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B3);
            box3.setShowSoftInputOnFocus(false);
            box3.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box3.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box3.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box3.setEnabled(false);
            return false;
        });

        box4.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B4);
            box4.setShowSoftInputOnFocus(false);
            box4.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box4.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box4.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box4.setEnabled(false);
            return false;
        });

        box5.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B5);
            box5.setShowSoftInputOnFocus(false);
            box5.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box5.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box5.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box5.setEnabled(false);
            return false;
        });

        box6.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B6);
            box6.setShowSoftInputOnFocus(false);
            box6.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box6.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box6.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box6.setEnabled(false);
            return false;
        });

        box7.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B7);
            box7.setShowSoftInputOnFocus(false);
            box7.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box7.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box7.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box7.setEnabled(false);
            return false;
        });

        box8.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B8);
            box8.setShowSoftInputOnFocus(false);
            box8.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box8.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box8.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box8.setEnabled(false);
            return false;
        });

        box9.setOnTouchListener((v, event) -> {

            SettingsBorder.setVisibility(View.INVISIBLE);
            Settings.setVisibility(View.INVISIBLE);
            BounceButtons(0);
            gameStarted=1;

            BoxAnim(B9);
            box9.setShowSoftInputOnFocus(false);
            box9.setVisibility(View.VISIBLE);
            if (turn == 0) {
                turnDisplay(P2,P1);
                turnDisplayArrow(P2arrow,P1arrow);
                box9.setText("O");
                turn = 1;
            } else if (turn == 1) {
                turnDisplay(P1,P2);
                turnDisplayArrow(P1arrow,P2arrow);
                box9.setText("X");
                turn = 0;
            }
            count++;
            draw();
            winner();
            box9.setEnabled(false);
            return false;
        });


    }

    private void winner(){
        if(box1.getText().toString().equals("X") && box5.getText().toString().equals("X") && box9.getText().toString().equals("X")){
            line1.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }else if(box3.getText().toString().equals("X") && box5.getText().toString().equals("X") && box7.getText().toString().equals("X")){
            line2.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b3.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b7.setVisibility(View.VISIBLE);

        }else if(box1.getText().toString().equals("X") && box2.getText().toString().equals("X") && box3.getText().toString().equals("X")){
            line3.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);

        }else if(box4.getText().toString().equals("X") && box5.getText().toString().equals("X") && box6.getText().toString().equals("X")){
            line4.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b6.setVisibility(View.VISIBLE);

        }else if(box7.getText().toString().equals("X") && box8.getText().toString().equals("X") && box9.getText().toString().equals("X")){
            line5.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b7.setVisibility(View.VISIBLE);
            b8.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }else if(box1.getText().toString().equals("X") && box4.getText().toString().equals("X") && box7.getText().toString().equals("X")){
            line6.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);
            b7.setVisibility(View.VISIBLE);

        }else if(box2.getText().toString().equals("X") && box5.getText().toString().equals("X") && box8.getText().toString().equals("X")){
            line7.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b2.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b8.setVisibility(View.VISIBLE);

        }else if(box3.getText().toString().equals("X") && box6.getText().toString().equals("X") && box9.getText().toString().equals("X")){
            line8.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b3.setVisibility(View.VISIBLE);
            b6.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }else if(box1.getText().toString().equals("O") && box5.getText().toString().equals("O") && box9.getText().toString().equals("O")){
            line1.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }else if(box3.getText().toString().equals("O") && box5.getText().toString().equals("O") && box7.getText().toString().equals("O")){
            line2.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b3.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b7.setVisibility(View.VISIBLE);

        }else if(box1.getText().toString().equals("O") && box2.getText().toString().equals("O") && box3.getText().toString().equals("O")){
            line3.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);

        }else if(box4.getText().toString().equals("O") && box5.getText().toString().equals("O") && box6.getText().toString().equals("O")){
            line4.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b6.setVisibility(View.VISIBLE);

        }else if(box7.getText().toString().equals("O") && box8.getText().toString().equals("O") && box9.getText().toString().equals("O")){
            line5.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b7.setVisibility(View.VISIBLE);
            b8.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }else if(box1.getText().toString().equals("O") && box4.getText().toString().equals("O") && box7.getText().toString().equals("O")){
            line6.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b1.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);
            b7.setVisibility(View.VISIBLE);

        }else if(box2.getText().toString().equals("O") && box5.getText().toString().equals("O") && box8.getText().toString().equals("O")){
            line7.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b2.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b8.setVisibility(View.VISIBLE);

        }else if(box3.getText().toString().equals("O") && box6.getText().toString().equals("O") && box9.getText().toString().equals("O")){
            line8.setVisibility(View.VISIBLE);
            disable();
            win=1;
            winnerPlayer();
            b3.setVisibility(View.VISIBLE);
            b6.setVisibility(View.VISIBLE);
            b9.setVisibility(View.VISIBLE);

        }

        if(win==1){

            BounceButtons(1);
            SettingsBorder.setVisibility(View.VISIBLE);
            Settings.setVisibility(View.VISIBLE);
            gameStarted=0;

            P1arrow.setVisibility(View.VISIBLE);
            P2arrow.setVisibility(View.VISIBLE);
            P1arrow.setText("-");
            P2arrow.setText("-");
            P1.setVisibility(View.INVISIBLE);
            P2.setVisibility(View.INVISIBLE);

            if(MainActivity.player1Score > MainActivity.player2Score){
                P1ScoreArrow.setText("▲");
                P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_green));
                P2ScoreArrow.setText("▼");
                P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.light_red));
            }else if(MainActivity.player2Score > MainActivity.player1Score){
                P2ScoreArrow.setText("▲");
                P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_green));
                P1ScoreArrow.setText("▼");
                P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.light_red));
            }else{
                P1ScoreArrow.setText("-");
                P1ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_grey));
                P2ScoreArrow.setText("-");
                P2ScoreArrow.setTextColor(ContextCompat.getColor(this,R.color.dark_grey));
            }

            GameOverAnim();
        }
    }


    private void GameOverAnim() {
        GameOver.setVisibility(View.VISIBLE);
        GameOverBack.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this,R.anim.gameover);
        GameOver.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                resetScoresBtn.setEnabled(false);
                Settings.setEnabled(false);
                Back.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                GameOver.setVisibility(View.INVISIBLE);
                GameOverBack.setVisibility(View.VISIBLE);

                if(win==1){
                    restartGame();
                }else if(win==0){
                    restartGameDraw();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void disable(){
        box1.setEnabled(false);
        box2.setEnabled(false);
        box3.setEnabled(false);
        box4.setEnabled(false);
        box5.setEnabled(false);
        box6.setEnabled(false);
        box7.setEnabled(false);
        box8.setEnabled(false);
        box9.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    private void draw(){
        if (count == 9 && win==0) {

            winnerDeclaration.setText("DRAW");
            P1.setVisibility(View.INVISIBLE);
            P2.setVisibility(View.INVISIBLE);
            P1arrow.setVisibility(View.VISIBLE);
            P2arrow.setVisibility(View.VISIBLE);
            P1arrow.setText("-");
            P2arrow.setText("-");
            SettingsBorder.setVisibility(View.VISIBLE);
            Settings.setVisibility(View.VISIBLE);
            gameStarted=0;
            BounceButtons(1);

            resetScoresBtn.setEnabled(true);
            Settings.setEnabled(true);
            Back.setEnabled(true);

            GameOverAnim();

        }
    }

    @SuppressLint("SetTextI18n")
    private void winnerPlayer(){
        if(turn==1 && win==1){
            winnerDeclaration.setText(name1+" (O)");
            MainActivity.player1Score++;
            playerScores1.setText(name1+" -  "+MainActivity.player1Score);
            playerScores2.setText(name2+" -  "+MainActivity.player2Score);
            MainActivity.highScore_pName=name1;
            MainActivity.highScore_pScore=MainActivity.player1Score;

            MainActivity.editor=MainActivity.sharedPreferencesData.edit();
            MainActivity.editor.putInt("HighScore",MainActivity.player1Score);
            MainActivity.editor.putString("Name",name1);
            MainActivity.editor.commit();

        }else if(turn==0 && win==1){
            winnerDeclaration.setText(name2+" (X)");
            MainActivity.player2Score++;
            playerScores1.setText(name1+" -  "+MainActivity.player1Score);
            playerScores2.setText(name2+" -  "+MainActivity.player2Score);
            MainActivity.highScore_pName=name2;
            MainActivity.highScore_pScore=MainActivity.player2Score;

            MainActivity.editor=MainActivity.sharedPreferencesData.edit();
            MainActivity.editor.putInt("HighScore",MainActivity.player2Score);
            MainActivity.editor.putString("Name",name2);
            MainActivity.editor.commit();
        }
    }

    private void restartGame(){

        SettingsBorder.setVisibility(View.VISIBLE);
        Settings.setVisibility(View.VISIBLE);
        animation.cancel();

        if(win==1) {

//            alert3 = new Dialog((this));
//            alert3.setContentView(R.layout.restart_alert);
//            TextView restartButton=alert3.findViewById(R.id.restart_button);
//
//            alert3.setOnShowListener(dialog -> {
//
//                countdown = new CountDownTimer(5000, 1000) {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        restartButton.setText("Restart (" + ((millisUntilFinished / 1000) + 1) + ")");
//                        restartButton.setOnClickListener(v -> {
//                            countdown.cancel();
//                            alert3.cancel();
//                            alert3.dismiss();
//                            restartResetParameters();
//                        });
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        countdown.cancel();
//                        alert3.cancel();
//                        alert3.dismiss();
//                        restartResetParameters();
//                    }
//                }.start();
//
//                alert3.setCanceledOnTouchOutside(false);
//                restartButton.setOnClickListener(v -> {
//                    countdown.cancel();
//                    alert3.cancel();
//                    alert3.dismiss();
//                    restartResetParameters();
//                });
//            });
//
//            alert3.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.
//
//            alert3.setOnKeyListener((dialog, keyCode, event) -> {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                }
//                return true;
//            });
//
//            alert3.show();


            countdown2 = new CountDownTimer(300, 300) {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    countdown2.cancel();
                    autorestartMessage.setVisibility(View.VISIBLE);
                    Animation animationText=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_text);
                    autorestartMessage.startAnimation(animationText);

                    countdown = new CountDownTimer(3000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
                            autorestartMessage.setText("Auto-restarting the\ngame in\n"+ ((millisUntilFinished / 1000) + 1));
                        }

                        @Override
                        public void onFinish() {
                            countdown.cancel();
                            autorestartMessage.setVisibility(View.INVISIBLE);
                            GameOverBack.setVisibility(View.INVISIBLE);
                            restartResetParameters();
                        }
                    }.start();
                }
            }.start();

        }

    }




    private void restartGameDraw(){

        SettingsBorder.setVisibility(View.VISIBLE);
        Settings.setVisibility(View.VISIBLE);
        animation.cancel();

        if(win==0) {

            countdown2 = new CountDownTimer(300, 300) {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    countdown2.cancel();
                    autorestartMessage.setVisibility(View.VISIBLE);
                    Animation animationText=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_text);
                    autorestartMessage.startAnimation(animationText);

                    countdown = new CountDownTimer(3000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
                            autorestartMessage.setText("Auto-restarting the\ngame in\n"+ ((millisUntilFinished / 1000) + 1));
                        }

                        @Override
                        public void onFinish() {
                            countdown.cancel();
                            autorestartMessage.setVisibility(View.INVISIBLE);
                            GameOverBack.setVisibility(View.INVISIBLE);
                            restartResetParameters();
                        }
                    }.start();
                }
            }.start();
        }

    }



    public void restartResetParameters(){
        countdown.cancel();
        count=0;
        turn=0;
        win=0;
        gameStarted=0;

        resetScoresBtn.setEnabled(true);
        Settings.setEnabled(true);
        Back.setEnabled(true);

        P1arrow.setVisibility(View.INVISIBLE);
        P2arrow.setVisibility(View.INVISIBLE);
        P1arrow.setText("⬤");
        P2arrow.setText("✖");

        turnDisplay(P1,P2);
        turnDisplayArrow(P1arrow,P2arrow);

        winnerDeclaration.setText("-");

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        b5.setVisibility(View.INVISIBLE);
        b6.setVisibility(View.INVISIBLE);
        b7.setVisibility(View.INVISIBLE);
        b8.setVisibility(View.INVISIBLE);
        b9.setVisibility(View.INVISIBLE);

        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);
        line6.setVisibility(View.INVISIBLE);
        line7.setVisibility(View.INVISIBLE);
        line8.setVisibility(View.INVISIBLE);

        box1.setEnabled(true);
        box1.setText(null);

        box2.setEnabled(true);
        box2.setText(null);

        box3.setEnabled(true);
        box3.setText(null);

        box4.setEnabled(true);
        box4.setText(null);

        box5.setEnabled(true);
        box5.setText(null);

        box6.setEnabled(true);
        box6.setText(null);

        box7.setEnabled(true);
        box7.setText(null);

        box8.setEnabled(true);
        box8.setText(null);

        box9.setEnabled(true);
        box9.setText(null);
    }

    @SuppressLint("SetTextI18n")
    private void resetScore(){
        P1ScoreArrow.setText("-");
        P1ScoreArrow.setTextColor(Color.WHITE);
        P2ScoreArrow.setText("-");
        P2ScoreArrow.setTextColor(Color.WHITE);

        MainActivity.player1Score =0;
        MainActivity.player2Score =0;
        playerScores1.setText(name1+" -  0");
        playerScores2.setText(name2+" -  0");
    }

    private void turnDisplay(TextView p1,TextView p2){
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.INVISIBLE);
    }

    private void turnDisplayArrow(TextView p1arrow,TextView p2arrow){
        p1arrow.setVisibility(View.VISIBLE);
        p2arrow.setVisibility(View.INVISIBLE);
    }

    public void BounceButtons(int anim_StartStop){
        if(anim_StartStop==1) {
            resetScoresBtn.setVisibility(View.VISIBLE);
        }else if(anim_StartStop==0){
            resetScoresBtn.setVisibility(View.INVISIBLE);
        }
    }

    public void BoxAnim(TextView box){
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.bounce1);
        box.startAnimation(anim);
    }

    @Override
    public void onBackPressed() {
//        Intent intent1 = new Intent(MainActivity7.this,MainActivity2.class);
//        startActivity(intent1);
    }
}
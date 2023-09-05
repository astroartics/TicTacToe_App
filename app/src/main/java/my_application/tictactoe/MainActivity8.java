package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity8 extends AppCompatActivity {

    TextView darkBackground,NameHeading,NameHeadingDark;
    TextView playerName;
    TextView highScore;

    ImageButton home,homeDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        playerName=findViewById(R.id.PlayerName);
        highScore=findViewById(R.id.PlayerScore);
        darkBackground=findViewById(R.id.DarkBackground);
        NameHeading=findViewById(R.id.NameHeading);
        NameHeadingDark=findViewById(R.id.NameHeadingDark);
        homeDark=findViewById(R.id.BackToHomeDark);
        home=findViewById(R.id.BackToHome);



        if(MainActivity4.getMode==1){
            darkBackground.setVisibility(View.VISIBLE);
            NameHeadingDark.setVisibility(View.VISIBLE);
            homeDark.setVisibility(View.VISIBLE);
        }else{
            NameHeading.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
        }



        int score=MainActivity.highScore_pScore;
        String winnerName=MainActivity.highScore_pName;
        highScore.setText(score+"");
        playerName.setText(winnerName+"");

        SharedPreferences sharedPreferences=getSharedPreferences("Game_data",MODE_PRIVATE);
        int high_score=sharedPreferences.getInt("HighScore",0);
        String name=sharedPreferences.getString("Name","-");

        if(score>high_score){
            highScore.setText(score+"");

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("HighScore",score);
            editor.putString("Name",winnerName);
            editor.commit();
        }else {
            playerName.setText(name+"");
            highScore.setText(high_score+"");
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        homeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
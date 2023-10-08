package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity9 extends AppCompatActivity {

    TextView tip;
    int randomNum=0;
    ProgressBar horizontalPB;
    int count=0;
    public static int Act9_over=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tip=findViewById(R.id.Tip);
        horizontalPB=findViewById(R.id.progressBar);
        horizontalPB.setMax(100);
        Thread thread = new Thread(() -> {
            SystemClock.sleep(400);
            for(int i=0;i<10;i++) {
                horizontalPB.incrementProgressBy(10);
                SystemClock.sleep(350);
                count++;
                if(count==9){
                    SystemClock.sleep(200);
                    MainActivity.Act_start++;
                    Act9_over++;
                    if (MainActivity4.settingsDAA != 0) {
                        Intent intent = new Intent(MainActivity9.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        finish();
                    }
                }
            }
        });
        thread.start();

        SharedPreferences SPrandom=getSharedPreferences("SPrandom",MODE_PRIVATE);
        SharedPreferences.Editor editor=SPrandom.edit();
        randomNum=SPrandom.getInt("random",0);

        if(randomNum==0){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Fact : TicTacToe is just Chess with less pieces and choices.");
        }else if(randomNum==1){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Try to cover any 3 corner blocks in a 3X3 grid.");
        }else if(randomNum==2){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Whoever goes first, has a better chance of winning.");
        }else if(randomNum==3){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Don't rush, take your time.");
        }else if(randomNum==4){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Go to settings for exploring more features of the app.");
        }else if(randomNum==5){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Fact : TicTacToe's small grid can illustrate mathematical principles like probability.");
        }else if(randomNum==6){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Fact : TicTacToe helps in improving cognitive skills such as critical thinking.");
        }else if(randomNum==7){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Starting with the corners give you better chances of winning.");
        }else if(randomNum==8){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Fact : There are 255168 possible games of a 3X3 grid TicTacToe.");
        }else if(randomNum==9){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Want to increase the difficulty level? Switch to a 4X4 grid.");
        }else if(randomNum==10){
            randomNum++;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Fact : TicTacToe has different names all around the world.");
        }else if(randomNum==11){
            randomNum=0;
            editor.putInt("random",randomNum);
            editor.commit();

            tip.setText("Tip : Keep practicing, until you're able to predict your opponent's each move.");
        }

    }

    @Override
    public void onBackPressed() {
    }
}
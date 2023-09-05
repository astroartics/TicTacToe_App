package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;

public class MainActivity9 extends AppCompatActivity {

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
    }

    @Override
    public void onBackPressed() {
    }
}
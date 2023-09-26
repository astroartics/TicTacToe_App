package my_application.tictactoe;

import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity4 extends MainActivity {

    public static int getMode;
    ToggleButton ModeSet;
    CheckBox DAA,DAADummy;
    public static int default_pressed=0;
    TextView defaultSettings;
    ImageButton Home,HomeLeft;
    CountDownTimer countdown;
    public static Dialog alert;
    ImageButton BackToHome;
    RadioGroup XORadioGroup,RestartRadioGroup,GridRadioGroup;
    RadioButton X,O,Automatic,Manual,ThreeByThree,FourByFour;
    public static SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferencesMode,sharedPreferencesGrid;
    public static SharedPreferences.Editor editor,editor1,editorMode,editorGrid;
    public static int X_or_O=1;
    public static int XOSP;
    SharedPreferences sharedPreferences2;
    public static SharedPreferences.Editor editor2;
    public static int Auto_Manual=0;
    public static int XOSP2;
    public static int GridSP=1;
    int threeORfourGrid;

    String P1name,P2name;
    public static int settingsDAA=0;
    public static int hand_animation=0;
    TextView checkboxOutline;
    TextView totalOutline,totalOutlineBackBtn;
    ImageView hand;
    ImageView handBackBtn;
    TextView handAnimText,handAnimTextBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        checkboxOutline = findViewById(R.id.checkboxOutline);
        totalOutline = findViewById(R.id.totalOutline);
        hand = findViewById(R.id.hand);
        handAnimText = findViewById(R.id.handAnimText);
        defaultSettings = findViewById(R.id.defaultSettings);
        handBackBtn = findViewById(R.id.handBackBtn);
        handAnimTextBack = findViewById(R.id.handAnimTextBack);
        totalOutlineBackBtn = findViewById(R.id.totalOutlineBackBtn);
        ModeSet = findViewById(R.id.ModeSet);

        sharedPreferences1 = getSharedPreferences("DAA", MODE_PRIVATE);
        sharedPreferencesMode = getSharedPreferences("Mode", MODE_PRIVATE);

        getMode = sharedPreferencesMode.getInt("Mode", 0);

        ModeSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ModeSet.isChecked()) {
                    editorMode = sharedPreferencesMode.edit();
                    editorMode.putInt("Mode", 1);
                    editorMode.commit();
                    Intent intent_darkSettings=new Intent(getApplicationContext(),DarkSettings.class);
                    startActivity(intent_darkSettings);
                } else {
                    editorMode = sharedPreferencesMode.edit();
                    editorMode.putInt("Mode", 0);
                    editorMode.commit();
                }
            }
        });

        if(getMode==0) {

            if (MainActivity.start == 0) {
                MainActivity.start++;
                Intent intent_act9 = new Intent(MainActivity4.this, MainActivity9.class);
                startActivity(intent_act9);
            }

            ModeSet.setChecked(getMode == 1);

            DAA = findViewById(R.id.DAA);
            DAA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (DAA.isChecked()) {
                        editor1 = sharedPreferences1.edit();
                        editor1.putInt("DAA2", 1);
                        editor1.commit();
                    } else {
                        editor1 = sharedPreferences1.edit();
                        editor1.putInt("DAA2", 0);
                        editor1.commit();
                    }
                }
            });

            int getChecked = sharedPreferences1.getInt("DAA2", 0);
            if (getChecked == 1) {
                DAA.setChecked(true);
                settingsDAA++;
            } else {
                DAA.setChecked(false);
            }

            if (MainActivity9.Act9_over != 0 && settingsDAA == 0 && default_pressed == 0) {
                alert = new Dialog(this);
                alert.setContentView(R.layout.welcome_alert);

                alert.setOnShowListener(dialog -> {

                    countdown = new CountDownTimer(3000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
//                        cancelButton.setText("Skip (" + ((millisUntilFinished / 1000) + 1) + ")");
                        }

                        @Override
                        public void onFinish() {
                            countdown.cancel();
                            alert.dismiss();
                            Act_start = 2;
                            if (hand_animation == 0) {
                                handAnimFunc(0);
                            }
                        }
                    }.start();
                });

                alert.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

                alert.setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //countdown.cancel();
                        //alert.dismiss();
                        //Act_start = 2;
                    }
                    return true;
                });

                if (Act_start == 1) {
                    alert.show();
                }
            }


            BackToHome = findViewById(R.id.BackToHome);
            XORadioGroup = findViewById(R.id.XORadioGroup);
            RestartRadioGroup = findViewById(R.id.RestartRadioGroup);
            GridRadioGroup=findViewById(R.id.GridRadioGroup);
            X = findViewById(R.id.X);
            O = findViewById(R.id.O);
            Automatic = findViewById(R.id.Automatic);
            Manual = findViewById(R.id.Manual);
            ThreeByThree=findViewById(R.id.ThreeByThree);
            FourByFour=findViewById(R.id.FourByFour);
            Home = findViewById(R.id.Home);
            DAADummy = findViewById(R.id.DAADummy);
            HomeLeft = findViewById(R.id.HomeLeft);

            if (MainActivity.onMain == 1) {
                Home.setVisibility(View.VISIBLE);
                BackToHome.setVisibility(View.VISIBLE);
            } else if (MainActivity.onMain == 0) {
                Home.setVisibility(View.INVISIBLE);
                HomeLeft.setVisibility(View.VISIBLE);
            }

            Home.setOnClickListener(v -> {

                Dialog alert3 = new Dialog(this);
                alert3.setContentView(R.layout.settings_alert);
                TextView text = alert3.findViewById(R.id.text);
                text.setText("All your progress will be lost.\nDo you want to go back to\nthe Home Screen?");
                Button yes = alert3.findViewById(R.id.yes);
                Button no = alert3.findViewById(R.id.no);
                alert3.show();

                yes.setOnClickListener(v14 -> {

                    alert3.dismiss();
                    alert3.cancel();

                    MainActivity3.toSettings_act3 = 0;
                    MainActivity5.toSettings_act5 = 0;
                    MainActivity6.toSettings_act6 = 0;
                    MainActivity7.toSettings_act7 = 0;
                    Darkmode1Activity.toSettings_act3=0;
                    Darkmode2Activity.toSettings_act5=0;
                    Darkmode3Activity.toSettings_act6=0;
                    Darkmode4Activity.toSettings_act7=0;
                    FourGrid1.toSettings_act3_4grid=0;
                    FourGrid1_dark.toSettings_act3_4grid_dark=0;
                    FourGrid2.toSettings_act5_4grid=0;
                    FourGrid2_dark.toSettings_act5_4grid_dark=0;
                    FourGrid3.toSettings_act6_4grid=0;
                    FourGrid3_dark.toSettings_act6_4grid_dark=0;
                    FourGrid4.toSettings_act7_4grid=0;
                    FourGrid4_dark.toSettings_act7_4grid_dark=0;
                    Intent intent_home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent_home);
                });

                no.setOnClickListener(v13 -> {
                    alert3.dismiss();
                    alert3.cancel();
                });

                alert3.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

                alert3.setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //countdown.cancel();
                        //alert.dismiss();
                    }
                    return true;
                });
            });


            HomeLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ActMainIntent = new Intent(MainActivity4.this, MainActivity.class);
                    startActivity(ActMainIntent);
                }
            });


            BackToHome.setOnClickListener(v -> {

                if (MainActivity3.toSettings_act3 == 1 || Darkmode1Activity.toSettings_act3==1) {
                    MainActivity3.toSettings_act3 = 0;
                    Darkmode1Activity.toSettings_act3=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                } else if (MainActivity5.toSettings_act5 == 1 || Darkmode2Activity.toSettings_act5==1) {
                    MainActivity5.toSettings_act5 = 0;
                    Darkmode2Activity.toSettings_act5=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                } else if (MainActivity6.toSettings_act6 == 1 || Darkmode3Activity.toSettings_act6==1) {
                    MainActivity6.toSettings_act6 = 0;
                    Darkmode3Activity.toSettings_act6=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                } else if (MainActivity7.toSettings_act7 == 1 || Darkmode4Activity.toSettings_act7==1) {
                    MainActivity7.toSettings_act7 = 0;
                    Darkmode4Activity.toSettings_act7=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                }else if (FourGrid1.toSettings_act3_4grid == 1 || FourGrid1_dark.toSettings_act3_4grid_dark==1) {
                    FourGrid1.toSettings_act3_4grid = 0;
                    FourGrid1_dark.toSettings_act3_4grid_dark=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                }else if (FourGrid2.toSettings_act5_4grid == 1  || FourGrid2_dark.toSettings_act5_4grid_dark==1) {
                    FourGrid2.toSettings_act5_4grid = 0;
                    FourGrid2_dark.toSettings_act5_4grid_dark=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                }else if (FourGrid3.toSettings_act6_4grid == 1 || FourGrid3_dark.toSettings_act6_4grid_dark==1) {
                    FourGrid3.toSettings_act6_4grid = 0;
                    FourGrid3_dark.toSettings_act6_4grid_dark=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                }else if (FourGrid4.toSettings_act7_4grid == 1 || FourGrid4_dark.toSettings_act7_4grid_dark==1) {
                    FourGrid4.toSettings_act7_4grid = 0;
                    FourGrid4_dark.toSettings_act7_4grid_dark=0;
                    if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                        GotoActivity3();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                        GotoActivity5();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity6();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                        GotoActivity7();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity8();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                        GotoActivity9();
                    }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity10();
                    }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                        GotoActivity11();
                    }
                }else {
                    Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            sharedPreferences = getSharedPreferences("pref", 0);
            XOSP = sharedPreferences.getInt("XOSP", 3);
            editor = sharedPreferences.edit();

            if (XOSP == 1) {
                X_or_O = 1;
                X.setChecked(true);
            } else if (XOSP == 0) {
                X_or_O = 0;
                O.setChecked(true);
            }

            XORadioGroup.setOnCheckedChangeListener(XORadGroup);


            sharedPreferences2 = getSharedPreferences("pref2", 0);
            XOSP2 = sharedPreferences2.getInt("XOSP2", 3);
            editor2 = sharedPreferences2.edit();

            if (XOSP2 == 1) {
                Auto_Manual = 1;
                Automatic.setChecked(true);
            } else if (XOSP2 == 0) {
                Auto_Manual = 0;
                Manual.setChecked(true);
            }

            RestartRadioGroup.setOnCheckedChangeListener(ReRadGroup);




            sharedPreferencesGrid=getSharedPreferences("Grid",0);
            threeORfourGrid=sharedPreferencesGrid.getInt("GridSP",3);
            editorGrid=sharedPreferencesGrid.edit();

            if(threeORfourGrid==1){
                GridSP=1;
                ThreeByThree.setChecked(true);
            }else if(threeORfourGrid==0){
                GridSP=0;
                FourByFour.setChecked(true);
            }

            GridRadioGroup.setOnCheckedChangeListener(GridRadGroup);




            defaultSettings.setOnClickListener(v -> {

                Animation anim = AnimationUtils.loadAnimation(this, R.anim.bounce3);
                defaultSettings.startAnimation(anim);

                TextView defaultSettingsBG = findViewById(R.id.defaultSettingsBG);
                defaultSettingsBG.startAnimation(anim);

                default_pressed = 1;

                if (X.isChecked() && Manual.isChecked() && !DAA.isChecked() && MainActivity4.getMode==0 && ThreeByThree.isChecked()) {
                    Toast.makeText(this, "Settings are same as default", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog alert2 = new Dialog(this);
                    alert2.setContentView(R.layout.settings_alert);
                    TextView text = alert2.findViewById(R.id.text);
                    text.setText("All customized settings will\ngo back to default. Are you sure\nyou want to proceed?");
                    Button yes = alert2.findViewById(R.id.yes);
                    Button no = alert2.findViewById(R.id.no);
                    alert2.show();

                    yes.setOnClickListener(v1 -> {
                        Toast.makeText(this, "Default settings restored", Toast.LENGTH_SHORT).show();
                        Manual.setChecked(true);
                        X.setChecked(true);
                        DAA.setChecked(false);
                        ThreeByThree.setChecked(true);
                        hand_animation = 0;
                        settingsDAA = 0;
                        handAnimFunc(default_pressed);
                        alert2.cancel();
                    });

                    no.setOnClickListener(v12 -> alert2.cancel());

                    alert2.setCanceledOnTouchOutside(false); //prevents any activity tom take place if user touches the screen outside alert.

                    alert2.setOnKeyListener((dialog, keyCode, event) -> {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            //countdown.cancel();
                            //alert.dismiss();
                        }
                        return true;
                    });
                }

                ModeSet.setChecked(false);
                editorMode = sharedPreferencesMode.edit();
                editorMode.putInt("Mode", 0);
                editorMode.commit();
            });

        }else{
            Intent intent_darkSettings=new Intent(getApplicationContext(),DarkSettings.class);
            startActivity(intent_darkSettings);
        }
    }

    RadioGroup.OnCheckedChangeListener XORadGroup=new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.X:
                    editor.putInt("XOSP",1);
                    X_or_O=1;
                    break;

                case R.id.O:
                    editor.putInt("XOSP",0);
                    X_or_O=0;
                    break;

                default:
                    break;
            }
            editor.commit();
        }
    };



    RadioGroup.OnCheckedChangeListener ReRadGroup=new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group2, int checkedId2) {
            switch(checkedId2){
                case R.id.Automatic:
                    editor2.putInt("XOSP2",1);
                    Auto_Manual=1;
                    break;

                case R.id.Manual:
                    editor2.putInt("XOSP2",0);
                    Auto_Manual=0;
                    break;

                default:
                    break;
            }
            editor2.commit();
        }
    };



    RadioGroup.OnCheckedChangeListener GridRadGroup=new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId3) {
            switch(checkedId3){
                case R.id.ThreeByThree:
                    editorGrid.putInt("GridSP",1);
                    GridSP=1;
                    break;

                case R.id.FourByFour:
                    editorGrid.putInt("GridSP",0);
                    GridSP=0;
                    break;

                default:
                    break;
            }
            editorGrid.commit();
        }
    };



    @Override
    public void onBackPressed() {
        if (MainActivity3.toSettings_act3 == 1 || Darkmode1Activity.toSettings_act3==1) {
            MainActivity3.toSettings_act3 = 0;
            Darkmode1Activity.toSettings_act3=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        } else if (MainActivity5.toSettings_act5 == 1 || Darkmode2Activity.toSettings_act5==1) {
            MainActivity5.toSettings_act5 = 0;
            Darkmode2Activity.toSettings_act5=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        } else if (MainActivity6.toSettings_act6 == 1 || Darkmode3Activity.toSettings_act6==1) {
            MainActivity6.toSettings_act6 = 0;
            Darkmode3Activity.toSettings_act6=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        } else if (MainActivity7.toSettings_act7 == 1 || Darkmode4Activity.toSettings_act7==1) {
            MainActivity7.toSettings_act7 = 0;
            Darkmode4Activity.toSettings_act7=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        }else if (FourGrid1.toSettings_act3_4grid == 1 || FourGrid1_dark.toSettings_act3_4grid_dark==1) {
            FourGrid1.toSettings_act3_4grid = 0;
            FourGrid1_dark.toSettings_act3_4grid_dark=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        }else if (FourGrid2.toSettings_act5_4grid == 1  || FourGrid2_dark.toSettings_act5_4grid_dark==1) {
            FourGrid2.toSettings_act5_4grid = 0;
            FourGrid2_dark.toSettings_act5_4grid_dark=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        }else if (FourGrid3.toSettings_act6_4grid == 1 || FourGrid3_dark.toSettings_act6_4grid_dark==1) {
            FourGrid3.toSettings_act6_4grid = 0;
            FourGrid3_dark.toSettings_act6_4grid_dark=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        }else if (FourGrid4.toSettings_act7_4grid == 1  || FourGrid4_dark.toSettings_act7_4grid_dark==1) {
            FourGrid4.toSettings_act7_4grid = 0;
            FourGrid4_dark.toSettings_act7_4grid_dark=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==1) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0  && MainActivity4.GridSP==1){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1  && MainActivity4.GridSP==1){
                GotoActivity7();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity8();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0 && MainActivity4.GridSP==0) {
                GotoActivity9();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity10();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1 && MainActivity4.GridSP==0) {
                GotoActivity11();
            }
        } else {

        }
    }



    private void GotoActivity3(){
        Darkmode1Activity.toSettings_act3=0;
        Intent intent = new Intent(MainActivity4.this, MainActivity3.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(MainActivity3.P1Name,P1name);
        intent.putExtra(MainActivity3.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity5(){
        Darkmode2Activity.toSettings_act5=0;
        Intent intent = new Intent(MainActivity4.this, MainActivity5.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(MainActivity5.P1Name,P1name);
        intent.putExtra(MainActivity5.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity6(){
        Darkmode3Activity.toSettings_act6=0;
        Intent intent = new Intent(MainActivity4.this, MainActivity6.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(MainActivity6.P1Name,P1name);
        intent.putExtra(MainActivity6.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity7(){
        Darkmode4Activity.toSettings_act7=0;
        Intent intent = new Intent(MainActivity4.this, MainActivity7.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(MainActivity7.P1Name,P1name);
        intent.putExtra(MainActivity7.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity8(){
        FourGrid1_dark.toSettings_act3_4grid_dark=0;
        Intent intent = new Intent(MainActivity4.this, FourGrid1.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(MainActivity7.P1Name,P1name);
        intent.putExtra(MainActivity7.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity9(){
        FourGrid2_dark.toSettings_act5_4grid_dark=0;
        FourGrid2.toSettings_act5_4grid=0;
        Intent intent = new Intent(MainActivity4.this, FourGrid2.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(FourGrid2.P1Name,P1name);
        intent.putExtra(FourGrid2.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity10(){
        FourGrid3_dark.toSettings_act6_4grid_dark=0;
        FourGrid3.toSettings_act6_4grid=0;
        Intent intent = new Intent(MainActivity4.this, FourGrid3.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(FourGrid3.P1Name,P1name);
        intent.putExtra(FourGrid3.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity11(){
        FourGrid4_dark.toSettings_act7_4grid_dark=0;
        FourGrid4.toSettings_act7_4grid=0;
        Intent intent = new Intent(MainActivity4.this, FourGrid4.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(FourGrid4.P1Name,P1name);
        intent.putExtra(FourGrid4.P2Name,P2name);

        startActivity(intent);
    }

    public void hand_anim(int anim_StartStop) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hand_anim);
        hand.startAnimation(animation);
        defaultSettings.setEnabled(false);
        XORadioGroup.setEnabled(false);
        X.setEnabled(false);
        O.setEnabled(false);
        Automatic.setEnabled(false);
        Manual.setEnabled(false);
        RestartRadioGroup.setEnabled(false);
        HomeLeft.setEnabled(false);
        ModeSet.setEnabled(false);
        GridRadioGroup.setEnabled(false);
        ThreeByThree.setEnabled(false);
        FourByFour.setEnabled(false);
        if(anim_StartStop==1){
            hand.clearAnimation();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void handAnimFunc(int defaultPressed){
        SharedPreferences sharedPreferences3=getSharedPreferences("handAnim",MODE_PRIVATE);
        hand_animation=sharedPreferences3.getInt("hand_animation",0);

        SharedPreferences.Editor editor3=sharedPreferences3.edit();

        if(hand_animation==0 && settingsDAA==0 && defaultPressed==0){
            totalOutline.setVisibility(View.VISIBLE);
            checkboxOutline.setVisibility(View.VISIBLE);
            hand.setVisibility(View.VISIBLE);
            handAnimText.setVisibility(View.VISIBLE);
            hand_anim(0);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hand_anim_back);

//            CheckBox DAA=findViewById(R.id.DAA);
            DAA.setOnTouchListener((v, event) -> {

                handAnimText.setVisibility(View.INVISIBLE);

                if(hand_animation==0) {
                    totalOutline.setVisibility(View.INVISIBLE);
                    totalOutlineBackBtn.setVisibility(View.VISIBLE);
                    hand_anim(1);
                    hand.setVisibility(View.INVISIBLE);
                    handBackBtn.setVisibility(View.VISIBLE);
                    handAnimTextBack.setVisibility(View.VISIBLE);
                    checkboxOutline.setVisibility(View.INVISIBLE);
                    handBackBtn.startAnimation(animation);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation1) {
                            DAA.setVisibility(View.INVISIBLE);
                            DAADummy.setVisibility(View.VISIBLE);
                            DAADummy.setEnabled(false);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation1) {
                            ModeSet.setEnabled(true);
                            defaultSettings.setEnabled(true);
                            XORadioGroup.setEnabled(true);
                            X.setEnabled(true);
                            O.setEnabled(true);
                            Automatic.setEnabled(true);
                            Manual.setEnabled(true);
                            RestartRadioGroup.setEnabled(true);
                            DAA.setEnabled(true);
                            HomeLeft.setEnabled(true);
                            GridRadioGroup.setEnabled(true);
                            ThreeByThree.setEnabled(true);
                            FourByFour.setEnabled(true);
                            DAA.setVisibility(View.VISIBLE);
                            DAADummy.setVisibility(View.INVISIBLE);
                            handAnimTextBack.setVisibility(View.INVISIBLE);
                            handBackBtn.setVisibility(View.INVISIBLE);
                            totalOutlineBackBtn.setVisibility(View.INVISIBLE);

                            hand_animation++;

                            editor3.putInt("hand_animation", hand_animation);
                            editor3.commit();
                            handBackBtn.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation1) {

                        }
                    });
                }
                return false;
            });
        }else if(defaultPressed==1){
            editor3.putInt("hand_animation",0);
            editor3.commit();
        }
    }
}
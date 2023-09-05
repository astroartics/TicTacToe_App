package my_application.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DarkSettings extends MainActivity {

    public static Dialog alert;
    ToggleButton ModeSet;
    CheckBox DAA,DAADummy;
    TextView defaultSettings;
    ImageButton Home,HomeLeft;
    CountDownTimer countdown;
    ImageButton BackToHome;
    RadioGroup XORadioGroup;
    RadioGroup RestartRadioGroup;
    RadioButton X,O,Automatic,Manual;
    SharedPreferences sharedPreferences2;

    String P1name,P2name;
    TextView checkboxOutline;
    TextView totalOutline,totalOutlineBackBtn;
    ImageView hand;
    ImageView handBackBtn;
    TextView handAnimText,handAnimTextBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_settings);

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

        ModeSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ModeSet.isChecked()) {
                    MainActivity4.editorMode = MainActivity4.sharedPreferencesMode.edit();
                    MainActivity4.editorMode.putInt("Mode", 1);
                    MainActivity4.editorMode.commit();
                } else {
                    MainActivity4.editorMode = MainActivity4.sharedPreferencesMode.edit();
                    MainActivity4.editorMode.putInt("Mode", 0);
                    MainActivity4.editorMode.commit();
                    Intent intent_act4=new Intent(getApplicationContext(),MainActivity4.class);
                    startActivity(intent_act4);
                }
            }
        });

        if(MainActivity4.getMode==1) {

            if (MainActivity.start == 0) {
                MainActivity.start++;
                Intent intent_act9 = new Intent(getApplicationContext(), MainActivity9.class);
                startActivity(intent_act9);
            }

            ModeSet.setChecked(MainActivity4.getMode == 1);

            DAA = findViewById(R.id.DAA);
            DAA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (DAA.isChecked()) {
                        MainActivity4.editor1 = MainActivity4.sharedPreferences1.edit();
                        MainActivity4.editor1.putInt("DAA2", 1);
                        MainActivity4.editor1.commit();
                    } else {
                        MainActivity4.editor1 = MainActivity4.sharedPreferences1.edit();
                        MainActivity4.editor1.putInt("DAA2", 0);
                        MainActivity4.editor1.commit();
                    }
                }
            });

            int getChecked = MainActivity4.sharedPreferences1.getInt("DAA2", 0);
            if (getChecked == 1) {
                DAA.setChecked(true);
                MainActivity4.settingsDAA++;
            } else {
                DAA.setChecked(false);
            }

            if (MainActivity9.Act9_over != 0 && MainActivity4.settingsDAA == 0 && MainActivity4.default_pressed == 0) {
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
                            if (MainActivity4.hand_animation == 0) {
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
            X = findViewById(R.id.X);
            O = findViewById(R.id.O);
            Automatic = findViewById(R.id.Automatic);
            Manual = findViewById(R.id.Manual);
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
                    Intent ActMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(ActMainIntent);
                }
            });


            BackToHome.setOnClickListener(v -> {

                if (Darkmode1Activity.toSettings_act3 == 1 || MainActivity3.toSettings_act3==1) {
                    Darkmode1Activity.toSettings_act3 = 0;
                    if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity3();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity5();
                    } else if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity6();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity7();
                    }
                } else if (Darkmode2Activity.toSettings_act5 == 1 || MainActivity5.toSettings_act5 == 1) {
                    Darkmode2Activity.toSettings_act5 = 0;
                    if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity3();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity5();
                    } else if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity6();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity7();
                    }
                } else if (Darkmode3Activity.toSettings_act6 == 1 || MainActivity6.toSettings_act6 == 1) {
                    Darkmode3Activity.toSettings_act6 = 0;
                    if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity3();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity5();
                    } else if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity6();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity7();
                    }
                } else if (Darkmode4Activity.toSettings_act7==1 || MainActivity7.toSettings_act7 == 1) {
                    Darkmode4Activity.toSettings_act7 = 0;
                    if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity3();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 0) {
                        GotoActivity5();
                    } else if (MainActivity4.X_or_O == 1 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity6();
                    } else if (MainActivity4.X_or_O == 0 && MainActivity4.Auto_Manual == 1) {
                        GotoActivity7();
                    }
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

            MainActivity4.sharedPreferences = getSharedPreferences("pref", 0);
            MainActivity4.XOSP = MainActivity4.sharedPreferences.getInt("XOSP", 3);
            editor = MainActivity4.sharedPreferences.edit();

            if (MainActivity4.XOSP == 1) {
                MainActivity4.X_or_O = 1;
                X.setChecked(true);
            } else if (MainActivity4.XOSP == 0) {
                MainActivity4.X_or_O = 0;
                O.setChecked(true);
            }

            XORadioGroup.setOnCheckedChangeListener(XORadGroup);


            sharedPreferences2 = getSharedPreferences("pref2", 0);
            MainActivity4.XOSP2 = sharedPreferences2.getInt("XOSP2", 3);
            MainActivity4.editor2 = sharedPreferences2.edit();

            if (MainActivity4.XOSP2 == 1) {
                MainActivity4.Auto_Manual = 1;
                Automatic.setChecked(true);
            } else if (MainActivity4.XOSP2 == 0) {
                MainActivity4.Auto_Manual = 0;
                Manual.setChecked(true);
            }

            RestartRadioGroup.setOnCheckedChangeListener(ReRadGroup);


            defaultSettings.setOnClickListener(v -> {

                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce3);
                defaultSettings.startAnimation(anim);

                TextView defaultSettingsBG = findViewById(R.id.defaultSettingsBG);
                defaultSettingsBG.startAnimation(anim);

                MainActivity4.default_pressed = 1;

                if (X.isChecked() && Manual.isChecked() && !DAA.isChecked() && MainActivity4.getMode==0) {
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
                        MainActivity4.hand_animation = 0;
                        MainActivity4.settingsDAA = 0;
                        handAnimFunc(MainActivity4.default_pressed);
                        alert2.cancel();

                        ModeSet.setChecked(false);
                        MainActivity4.editorMode = MainActivity4.sharedPreferencesMode.edit();
                        MainActivity4.editorMode.putInt("Mode", 0);
                        MainActivity4.editorMode.commit();

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
            });

        }else{
            Intent intent_act4=new Intent(getApplicationContext(),MainActivity4.class);
            startActivity(intent_act4);
        }
    }

    RadioGroup.OnCheckedChangeListener XORadGroup=new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.X:
                    editor.putInt("XOSP",1);
                    MainActivity4.X_or_O=1;
                    break;

                case R.id.O:
                    editor.putInt("XOSP",0);
                    MainActivity4.X_or_O=0;
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
                    MainActivity4.editor2.putInt("XOSP2",1);
                    MainActivity4.Auto_Manual=1;
                    break;

                case R.id.Manual:
                    MainActivity4.editor2.putInt("XOSP2",0);
                    MainActivity4.Auto_Manual=0;
                    break;

                default:
                    break;
            }
            MainActivity4.editor2.commit();
        }
    };



    @Override
    public void onBackPressed() {
        if(Darkmode1Activity.toSettings_act3==1 || MainActivity3.toSettings_act3==1){
            Darkmode1Activity.toSettings_act3=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                GotoActivity7();
            }
        } else if (Darkmode2Activity.toSettings_act5==1 || MainActivity5.toSettings_act5==1) {
            Darkmode2Activity.toSettings_act5=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                GotoActivity7();
            }
        }else if (Darkmode3Activity.toSettings_act6==1 || MainActivity6.toSettings_act6==1) {
            Darkmode3Activity.toSettings_act6=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                GotoActivity7();
            }
        }else if (Darkmode4Activity.toSettings_act7==1 || MainActivity7.toSettings_act7==1) {
            Darkmode4Activity.toSettings_act7=0;
            if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==0) {
                GotoActivity3();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==0){
                GotoActivity5();
            }else if(MainActivity4.X_or_O==1 && MainActivity4.Auto_Manual==1){
                GotoActivity6();
            }else if(MainActivity4.X_or_O==0 && MainActivity4.Auto_Manual==1){
                GotoActivity7();
            }
        }else{
//            Intent intent=new Intent(MainActivity4.this,MainActivity.class);
//            startActivity(intent);
        }
    }



    private void GotoActivity3(){
        MainActivity3.toSettings_act3=0;
        Intent intent = new Intent(getApplicationContext(), Darkmode1Activity.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(Darkmode1Activity.P1Name,P1name);
        intent.putExtra(Darkmode1Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity5(){
        MainActivity5.toSettings_act5=0;
        Intent intent = new Intent(getApplicationContext(), Darkmode2Activity.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(Darkmode2Activity.P1Name,P1name);
        intent.putExtra(Darkmode2Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity6(){
        MainActivity6.toSettings_act6=0;
        Intent intent = new Intent(getApplicationContext(), Darkmode3Activity.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(Darkmode3Activity.P1Name,P1name);
        intent.putExtra(Darkmode3Activity.P2Name,P2name);

        startActivity(intent);
    }

    private void GotoActivity7(){
        MainActivity7.toSettings_act7=0;
        Intent intent = new Intent(getApplicationContext(), Darkmode4Activity.class);

        P1name=MainActivity2.P1name;
        P2name=MainActivity2.P2name;

        intent.putExtra(Darkmode4Activity.P1Name,P1name);
        intent.putExtra(Darkmode4Activity.P2Name,P2name);

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
        if(anim_StartStop==1){
            hand.clearAnimation();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void handAnimFunc(int defaultPressed){
        SharedPreferences sharedPreferences3=getSharedPreferences("handAnim",MODE_PRIVATE);
        MainActivity4.hand_animation=sharedPreferences3.getInt("hand_animation",0);

        SharedPreferences.Editor editor3=sharedPreferences3.edit();

        if(MainActivity4.hand_animation==0 && MainActivity4.settingsDAA==0 && defaultPressed==0){
            totalOutline.setVisibility(View.VISIBLE);
            checkboxOutline.setVisibility(View.VISIBLE);
            hand.setVisibility(View.VISIBLE);
            handAnimText.setVisibility(View.VISIBLE);
            hand_anim(0);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hand_anim_back);

//            CheckBox DAA=findViewById(R.id.DAA);
            DAA.setOnTouchListener((v, event) -> {

                handAnimText.setVisibility(View.INVISIBLE);

                if(MainActivity4.hand_animation==0) {
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
                            DAA.setVisibility(View.VISIBLE);
                            DAADummy.setVisibility(View.INVISIBLE);
                            handAnimTextBack.setVisibility(View.INVISIBLE);
                            handBackBtn.setVisibility(View.INVISIBLE);
                            totalOutlineBackBtn.setVisibility(View.INVISIBLE);

                            MainActivity4.hand_animation++;

                            editor3.putInt("hand_animation", MainActivity4.hand_animation);
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
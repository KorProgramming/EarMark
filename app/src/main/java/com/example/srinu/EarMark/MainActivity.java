package com.example.srinu.EarMark;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int[] timearr;
    private CountDownTimer ctimer;
    int hvalue;
    int mvalue;
    TimePicker alarmTime;
    //final Ringtone ring = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
    //Context context=this;
    private MediaPlayer mp;
    //final MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.sleep);
    /*private void stopPlaying(){
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
            mp.start();
        }
    }*/
    public static int[] findTime() {
        int[] fin = new int[3];
        Calendar date = Calendar.getInstance();
        Date Time = date.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String temp = dateFormat.format(Time);
        fin[0] = Integer.parseInt(temp.substring(0, 2));
        fin[1] = Integer.parseInt(temp.substring(3, 5));
        fin[2] = Integer.parseInt(temp.substring(6, 8));
        return fin;
    }

    private void start() {
        int hstat = timearr[0];
        int mstat = timearr[1];
        int sstat = timearr[2];
        if (!(hstat < hvalue || (hstat == hvalue && mstat <= mvalue))) {
            hvalue = hvalue + 12;
        }
        int hdif = hvalue - hstat;
        int mdif = mvalue - mstat - 1;
        int sdif = 60 - sstat;
        int totsec = hdif * 60 * 60 + mdif * 60 + sdif;

        ctimer = new CountDownTimer(totsec * 1000, 1000) {
            public void onTick(long millisLeft) {

            }

            public void onFinish() {
                Toast finToast = Toast.makeText(
                        getApplicationContext(),
                        "BEEP",
                        Toast.LENGTH_LONG);
                finToast.show();
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.sleep);
                mp.start();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50000);
                }
            }


                /*Timer t=new Timer();
                t.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        ring.play();
                    }
                },delay:0,period:1000);
            }
        };*/

            };
        ctimer.start();
        }


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                alarmTime = findViewById(R.id.timePicker);
                //final MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.sleep);
                Button stpb = (Button) findViewById(R.id.stpb);
                stpb.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        //MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.ringer);
                        Toast stopAl = Toast.makeText(
                                getApplicationContext(),
                                "Alarm Stopped",
                                Toast.LENGTH_LONG);
                        stopAl.show();
                        //mp=MediaPlayer.create(getApplicationContext(),R.raw.ringer);
                        //mp.;
                        /*try{
                            if(mp!=null && mp.isPlaying()){
                                mp.stop();
                            }
                            mp.release();
                            mp=null;
                            mp = new MediaPlayer();  //I needed this, maybe you dont hac
                        }catch(Exception e){
                            e.printStackTrace();
                            //System.out.println("I can't believe you get here !");
                        }*/
                        //mp.stop();


                    }
                });


                Button mButton = (Button) findViewById(R.id.button1);
                mButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                            /*if (mp.isPlaying()) {
                                mp.stop();
                                Toast stopAl = Toast.makeText(
                                        getApplicationContext(),
                                        "Alarm Stopped",
                                        Toast.LENGTH_LONG);
                                stopAl.show();
                            }*/

                            Toast setToast = Toast.makeText(
                                    getApplicationContext(),
                                    "Alarm Set",
                                    Toast.LENGTH_LONG);
                            setToast.show();

                            hvalue = alarmTime.getCurrentHour();
                            mvalue = alarmTime.getCurrentMinute();
                            timearr = findTime();
                            start();
                        }
                    });
                }
            }



package com.example.abidin.rabbitrabbit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appolica.flubber.Flubber;

public class MainActivity extends AppCompatActivity {

    // identititas id unik untuk notification
    public static final int NOTIFICATION_ID = 1;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    // Declare the MediaPlayer object
    private MediaPlayer mMediaPlayer;

    private TextView tvShake;
    //private Button btn;
    private ImageView img,imgh,imgrabbit,imgtank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //tvShake = findViewById(R.id.tvShake);
       // btn = findViewById(R.id.btn);
        img = findViewById(R.id.animationView);

        imgh = (ImageView) findViewById(R.id.btnimagehazard);

        imgrabbit = (ImageView) findViewById(R.id.rabbit);

        imgtank = (ImageView) findViewById(R.id.tank);


        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });

        */

        imgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgtank.setImageResource(R.drawable.hazarda);
                imgrabbit.setImageResource(R.drawable.hazardb);
                Flubber.with()
                        .animation(Flubber.AnimationPreset.MORPH) // Slide up animation
                        .repeatCount(0)                              // Repeat once
                        .duration(500)                              // Last for 1000 milliseconds(1 second)
                        .createFor(imgh)                             // Apply it to the view
                        .start();

            }
        });

        imgrabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tanktank);
                mMediaPlayer.start();
                imgrabbit.setImageResource(R.drawable.hazardbs);
                Flubber.with()
                        .animation(Flubber.AnimationPreset.SQUEEZE_RIGHT) // Slide up animation
                        .repeatCount(1)                              // Repeat once
                        .duration(300)                              // Last for 1000 milliseconds(1 second)
                        .createFor(imgrabbit)                             // Apply it to the view
                        .start();

            }
        });

        imgtank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dong);
                mMediaPlayer.start();
                imgtank.setImageResource(R.drawable.hazardas);
                Flubber.with()
                        .animation(Flubber.AnimationPreset.SQUEEZE_UP) // Slide up animation
                        .repeatCount(1)                              // Repeat once
                        .duration(300)                              // Last for 1000 milliseconds(1 second)
                        .createFor(imgtank)                             // Apply it to the view
                        .start();

            }
        });

/*
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flubber.with()
                        .animation(Flubber.AnimationPreset.FALL) // Slide up animation
                        .repeatCount(1)                              // Repeat once
                        .duration(300)                              // Last for 1000 milliseconds(1 second)
                        .createFor(img)                             // Apply it to the view
                        .start();

            }
        });
*/
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {


            public void onShake(int shake) {



                // Initialize media player
              //  mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.rabbitrabbit);


                if (shake > 0 && shake <=5){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dong);
                } else if(shake ==6 ){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.edgerabbit);
                    img.setImageResource(R.drawable.rabbit);
                    imgtank.setImageResource(R.drawable.hazardas);
                    Flubber.with()
                            .animation(Flubber.AnimationPreset.SQUEEZE_UP) // Slide up animation
                            .repeatCount(1)                              // Repeat once
                            .duration(500)                              // Last for 1000 milliseconds(1 second)
                            .createFor(img)                             // Apply it to the view
                            .start();
                } else if(shake> 6 && shake<=11){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dong);
                } else if(shake==12){
                    img.setImageResource(R.drawable.tank);
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.endgetankf);
                    imgrabbit.setImageResource(R.drawable.hazardbs);
                    Flubber.with()
                            .animation(Flubber.AnimationPreset.FLIP_Y) // Slide up animation
                            .repeatCount(1)                              // Repeat once
                            .duration(500)                              // Last for 1000 milliseconds(1 second)
                            .createFor(img)                             // Apply it to the view
                            .start();
                }else if(shake>12 && shake <=17){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tanktank);
                } else if(shake == 18 ){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ayuready);
                    img.setImageResource(R.drawable.rabbittank);
                    Flubber.with()
                            .animation(Flubber.AnimationPreset.ZOOM_IN) // Slide up animation
                            .repeatCount(1)                              // Repeat once
                            .duration(500)                              // Last for 1000 milliseconds(1 second)
                            .createFor(img)                             // Apply it to the view
                            .start();

                } else if (shake == 19){
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song);

                }

                // Add OnCompletionListener to release the
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mMediaPlayer.release();

            }

        });
                mMediaPlayer.start();
            }
        });

    }

    //notif feature

    //Hazard on Notif
    private void showNotifHazard() {
        NotificationManager notificationManager;

        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // builder.setColor(getResources().getColor(R.color.colorPrimary));
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.danger)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.maxhazard))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("MAX HAZARD ON!!")
                .setContentText("WARNING!! DANGER!!");

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(115, builder.build());
    }

//Rabbit rabbit notif
    private void showNotifRabbit() {
        NotificationManager notificationManager;

        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
       // builder.setColor(getResources().getColor(R.color.colorPrimary));
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.rabbit)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.hazardas))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("KURENAI NO SPEEDY JUMPER!! RABBIT RABBIT!!")
                .setContentText("YABEI!! HAEEEEEI!!");

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(115, builder.build());
    }

    //Tank tank notif
    private void showNotifTank() {
        NotificationManager notificationManager;

        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // builder.setColor(getResources().getColor(R.color.colorPrimary));
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.tank)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.hazardbs))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("KOUTETSU NO BLUE WARRIOR!! TANK TANK!!")
                .setContentText("YABEI!! TUEEEEEI!!");

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(115, builder.build());
    }


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
package id.co.imastudio.mengenalwarna;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnOption;
    //    private ImageView btnAbout;
//    private ImageView btnExit;
    private ImageView btnOn;
    private String settinganSound;
    private AudioManager audioManager;
    private ImageView btnYes;
    private ImageView btnNo;
    private ImageView btnBack;
    private ImageView btnGoogle;
    private ImageView btnFacebook;
    private ImageView ivTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();

        YoYo.with(Techniques.Bounce)
                .duration(3000)
                .repeat(1000)
                .playOn(btnPlay);

        YoYo.with(Techniques.Pulse)
                .duration(3000)
                .repeat(1000)
                .playOn(ivTitle);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
                playSound();
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_option);
                dialog.show();

                btnOn = (ImageView) dialog.findViewById(R.id.btnSoundOn);
                btnGoogle = (ImageView) dialog.findViewById(R.id.btn_google);
                btnFacebook = (ImageView) dialog.findViewById(R.id.btn_facebook);

                btnFacebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/IDN-Superkidz-195423731055401/")));
                    }
                });

                btnGoogle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/b/113321261450799988204/113321261450799988204")));
                    }
                });
                //ngambil data
                SharedPreferences pref = getSharedPreferences("setting", 0);
                settinganSound = pref.getString("sound", "on");
                if (settinganSound.equals("on")) {
                    btnOn.setImageResource(R.drawable.option_btn_sound_on);
                } else if (settinganSound.equals("off")) {
                    btnOn.setImageResource(R.drawable.option_btn_sound_off);
                }

                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                btnOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        //ngambil data
                        SharedPreferences pref = getSharedPreferences("setting", 0);
                        settinganSound = pref.getString("sound", "on");
                        if (settinganSound.equals("on")) {
                            btnOn.setImageResource(R.drawable.option_btn_sound_off);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("sound", "off");
                            editor.commit();
                            //simpan data

                            //setting audio on
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_SHOW_UI);

                        } else if (settinganSound.equals("off")) {
                            btnOn.setImageResource(R.drawable.option_btn_sound_on);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("sound", "on");
                            editor.commit();

                            //setting off
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 15, AudioManager.FLAG_SHOW_UI);
                        }

                    }
                });

                btnBack = (ImageView) dialog.findViewById(R.id.btn_back);
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ngambil data
                        dialog.dismiss();
                    }
                });

            }
        });

//        btnAbout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playSound();
//                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.setContentView(R.layout.dialog_about);
//                dialog.show();
//            }
//        });
//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playSound();
//                final Dialog dialog = new Dialog(MainActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.setContentView(R.layout.dialog_exit);
//                dialog.show();
//
//
//                btnYes = (ImageView) dialog.findViewById(R.id.btnYes);
//                btnYes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //ngambil data
//                        finish();
//                        System.exit(0);
//                        playSound();
//                    }
//                });
//
//                btnNo = (ImageView) dialog.findViewById(R.id.btnNo);
//                btnNo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //ngambil data
//                        dialog.dismiss();
//                        playSound();
//                    }
//                });
//
//
//
//            }
//        });


    }

    private void initView() {
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        btnOption = (ImageView) findViewById(R.id.btnOption);
//        btnAbout = (ImageView) findViewById(R.id.btnAbout);
//        btnExit = (ImageView) findViewById(R.id.btnExit);
        ivTitle = (ImageView) findViewById(R.id.iv_title);
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_exit);
        dialog.show();

        btnYes = (ImageView) dialog.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ngambil data
                finish();
                System.exit(0);
                playSound();
            }
        });

        btnNo = (ImageView) dialog.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ngambil data
                dialog.dismiss();
                playSound();
            }
        });


    }

    private void playSound() {
        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.sfx_button);
        player.start();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

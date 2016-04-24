package com.example.crynoz.temp;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainGui extends ActionBarActivity implements TextToSpeech.OnInitListener {


    int init = 0;
    private String text = "Welcome to Able where Disabled are abled AF! Touch any area of the screen to assess what it does and touch and hold for 3 seconds " +
            "to use the functionality";
    TextToSpeech ttos;
    Button b1, b2;
    private ProgressDialog progress;

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            ttos.setLanguage(Locale.UK);
            //textToSpeech.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ttos = new TextToSpeech(getApplicationContext(), this);
        b1 = (Button) findViewById(R.id.ocr);
        b2 = (Button) findViewById(R.id.face);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speek = b1.getText().toString();
                if (init == 0){ttos.speak(text, TextToSpeech.QUEUE_FLUSH, null); init++;}
                ttos.speak(speek, TextToSpeech.QUEUE_FLUSH, null);
                ttos.speak(speek, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        b1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
//                progress = new ProgressDialog(getApplicationContext()this);
//                progress.setMessage("Loading the picture to be displayed");
//                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progress.setIndeterminate(true);
//                progress.setProgress(0);
//                progress.show();

                final int totalProgressTime = 100;
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        int jumpTime = 0;

                        while (jumpTime < totalProgressTime) {
                            try {
                                sleep(200);
                                jumpTime += 5;
//                                progress.setProgress(jumpTime);
                            } catch (InterruptedException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                };
                t.start();
                return true;
            }
        });

        ttos = new TextToSpeech(getApplicationContext(), this);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speek = b2.getText().toString();
                if (init == 0 || init == 1){ttos.speak(text, TextToSpeech.QUEUE_FLUSH, null); init++;}
                ttos.speak(speek, TextToSpeech.QUEUE_FLUSH, null);
                ttos.speak(speek, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        b2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), FaceActivity.class);
                startActivity(i);
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


//        ttos = new TextToSpeech(getApplicationContext(), this);
//        ttos.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
}

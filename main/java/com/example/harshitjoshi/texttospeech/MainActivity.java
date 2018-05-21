package com.example.harshitjoshi.texttospeech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textToSpeech(View view)
    {
       Intent intent=new Intent(this,TextToSpeech.class);
       startActivity(intent);

    }

    public void speechToText(View view) {
        Intent intent=new Intent(this,SpeechToText.class);
        startActivity(intent);
    }
}

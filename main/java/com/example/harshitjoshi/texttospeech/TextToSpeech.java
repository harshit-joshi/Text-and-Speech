package com.example.harshitjoshi.texttospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class TextToSpeech extends AppCompatActivity {
    private android.speech.tts.TextToSpeech textToSpeech;
    private EditText ValueEditText;
    private SeekBar pitch;
    private SeekBar speed;
    private Button sayItButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_to_speech);
        sayItButton=findViewById(R.id.sayIt);
        textToSpeech=new android.speech.tts.TextToSpeech(this, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status== android.speech.tts.TextToSpeech.SUCCESS)
                {
                    int result=textToSpeech.setLanguage(Locale.ENGLISH);
                    if(result== android.speech.tts.TextToSpeech.LANG_MISSING_DATA || result== android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("textToSpeech","does not suport");
                    }
                    else
                    {
                        sayItButton.setEnabled(true);
                    }
                }
                else
                {
                    Log.e("textToSpeech","Initialization Failed");
                }
            }
        });
        ValueEditText=findViewById(R.id.editValue);
        pitch=findViewById(R.id.pitchSeek);
        speed=findViewById(R.id.speedSeek);
        sayItButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                speak();
            }
        });
    }

    private void speak()
    {
        String text=ValueEditText.getText().toString();
        float pitchSeek=pitch.getProgress()/50;
        if (pitchSeek<0.1)pitchSeek=0.1f;
        float speedSeek=speed.getProgress()/50;
        if (speedSeek<0.1)speedSeek=0.1f;
        textToSpeech.setSpeechRate(speedSeek);
        textToSpeech.setPitch(pitchSeek);
        textToSpeech.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    protected void onDestroy()
    {
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}

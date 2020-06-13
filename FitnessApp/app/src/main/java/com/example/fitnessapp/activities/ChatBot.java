package com.example.fitnessapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class ChatBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_chat_bot );
    }
    public void MyWeb(View view){
        openUrl("https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?region=au-syd&integrationID=c82423f9-8300-4752-b60c-4d1e9188ae80&serviceInstanceID=20d29fab-4f2f-4af6-9e78-e41b1b94cf59");

    }
    //working url for now
    public void openUrl(String url){
        Uri uri = Uri.parse(url);
        Intent launchWeb = new Intent(Intent.ACTION_VIEW,uri);
        startActivity ( launchWeb );
    }
}

package com.example.eightactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class second extends AppCompatActivity {
    String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item2);
        Log.d(TAG, this.getClass().getSimpleName() + "---onCreate:");
    }
}
package com.barkov.ais.honestlottogame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void doMain(View v)
    {
        finish();
    }

    @Override
    public void onClick(View v) {
        doMain(v);
    }
}

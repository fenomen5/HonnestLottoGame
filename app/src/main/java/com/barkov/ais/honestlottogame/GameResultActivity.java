package com.barkov.ais.honestlottogame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        Intent mIntent = getIntent();
        Integer scoreValue = mIntent.getIntExtra("score", 0);

        ((TextView)findViewById(R.id.txtResultScore)).setText(scoreValue.toString());
    }

    public void doMain(View v)
    {
        this.finish();
    }
}

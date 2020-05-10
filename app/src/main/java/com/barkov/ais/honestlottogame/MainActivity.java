package com.barkov.ais.honestlottogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.barkov.ais.honestlottogame.rules.Rule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn10, btn50, btn100;
    Button btnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_coins);


        btn10 = findViewById(R.id.btnTenGame);
        btn10.setOnClickListener(this);
        btn50 = findViewById(R.id.btnFiftyGame);
        btn50.setOnClickListener(this);
        btn100 = findViewById(R.id.btnHundredGame);
        btn100.setOnClickListener(this);
        btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Animation clickBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
                v.startAnimation(clickBounce);
                Log.d("dbg", Integer.toString(v.getId()));
                //Log.d("dbg", v.toString());
                switch(v.getId()) {
                    case R.id.btnAbout:
                        doAbout(v);
                    break;
                    case R.id.btnTenGame:
                        Log.d("dbg","ten game");
                        doGameTen(v);
                        break;
                    case R.id.btnFiftyGame:
                        doGameFifty(v);
                        break;
                    case R.id.btnHundredGame:
                        doGameHundred(v);
                        break;
                    default:

                }
    }

    protected void doAbout(View v)
    {
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
    }

    public void doGameTen(View v)
    {
        doGame(Rule.GUESS_NUMBER_OUT_OF_TEN);
    }

    public void doGameFifty(View v)
    {
        doGame(Rule.GUES_NUMBER_OUT_OF_FIFTY);
    }

    public void doGameHundred(View v)
    {
        doGame(Rule.GUES_NUMBER_OUT_OF_HUNDRED);
    }

    public void doGame(int numbers)
    {
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.putExtra("game_type", numbers);
        startActivity(gameActivity);
    }

    public void onWithdrawal(View v) {
        finish();
    }
}

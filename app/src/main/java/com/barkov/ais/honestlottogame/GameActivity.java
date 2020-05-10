package com.barkov.ais.honestlottogame;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.barkov.ais.honestlottogame.blocks.BlocksManager;
import com.barkov.ais.honestlottogame.elements.Element;
import com.barkov.ais.honestlottogame.rules.RulesFactory;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ACTIVIY_EXIT = 5;
    private GameLogic gameLogic;
    private Element[] elements;
    private BlocksManager bManager;
    private int gameType;
    private EditText input;
    private Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_game);

        Intent mIntent = getIntent();
        gameType = mIntent.getIntExtra("game_type", 0);
        input = findViewById(R.id.txtNumber);
        btnSend = findViewById(R.id.btnSubmit);
        btnSend.setOnClickListener(this);
        this.initGame();
    }

    protected boolean initGame() {

        this.gameLogic = new GameLogic();
        this.gameLogic.setGameType(gameType);
        String ruleText = this.gameLogic.getCurrentRule().getRuleText();
        ((TextView)findViewById(R.id.txtCurrentRule)).setText(ruleText);
        this.updateGameInfo("");
        bManager = new BlocksManager();
        return  true;
    }

    @Override
    public void onClick(View v) {
        if (((EditText)input).getText().length() == 0) {
            return;
        }
        int value = Integer.parseInt(((EditText)input).getText().toString());
        MediaPlayer mp;
        String result = "";
        if (value > 0) {
            boolean evaulateResult = this.gameLogic.evaluateChoice(value, getScoreStep());

            if (evaulateResult) {
                mp = MediaPlayer.create(this, R.raw.win);
            } else {
                mp = MediaPlayer.create(this, R.raw.fail);
            }

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });

            result = gameLogic.getLastResponse();
            if (evaulateResult) {
                gameLogic.resetChanses();
                gameLogic.setGameType(this.gameType);
                btnSend.setClickable(false);
                btnSend.setBackgroundColor(getResources().getColor(R.color.colorDisabled));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtResponse = (TextView)findViewById(R.id.txtResponse);
                        txtResponse.setText("New Round");

                        btnSend.setClickable(true);
                        btnSend.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        txtResponse.setText("New Round");
                    }
                }, 2000);

            } else {
                if (gameLogic.checkGameFinished()) {
                    boolean decreaseResult = gameLogic.decreaseScore(2);
                    if (!decreaseResult) {
                        finish();
                    } else {
                        gameLogic.setGameType(gameType);
                        gameLogic.resetChanses();
                    }
                }
            }
            this.updateGameInfo(result);

        }

    }

    protected void updateGameInfo(String result)
    {
        Resources r = getResources();
        int score = this.gameLogic.getScore().getScore();
        TextView txtScore = (TextView)findViewById(R.id.txtScore);
        txtScore.setText(""+score);

        TextView txtResponse = (TextView)findViewById(R.id.txtResponse);
        txtResponse.setText(result);

        int chances = this.gameLogic.getChances().getChances();
        TextView txtChances = (TextView)findViewById(R.id.txtChanses);
        txtChances.setText(""+ chances);

        TextView txtNumber = (TextView)findViewById(R.id.txtNumber);
        txtNumber.setText("");

        String ruleText = this.gameLogic.getCurrentRule().getRuleText();
        ((TextView)findViewById(R.id.txtCurrentRule)).setText(ruleText);
    }

    protected int getScoreStep()
    {
        if (this.gameType == RulesFactory.GUESS_NUMBER_OUT_OF_TEN) {
            return 10;
        }
        if (this.gameType == RulesFactory.GUES_NUMBER_OUT_OF_FIFTY) {
            return 50;
        }
        if (this.gameType == RulesFactory.GUES_NUMBER_OUT_OF_HUNDRED) {
            return 99;
        }

        return 0;
    }
}

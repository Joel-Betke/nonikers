package edu.brandeis.cs.nonikers.nonikers;

import android.content.Intent;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay extends AppCompatActivity {
    private Game game;
    private int[] roundText = new int[]{
            R.string.round_1,
            R.string.round_2,
            R.string.round_3
    };
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "!!!!!!!!!!!!!!!!");
        super.onCreate(savedInstanceState);

        ArrayList<String> cards = getIntent().getStringArrayListExtra("cards");
        if (cards == null) {
            Resources res = getResources();
            String[] cards_arr = res.getStringArray(R.array.monikers_cards);

            cards = new ArrayList<String>(Arrays.asList(cards_arr));
        }
        this.game = new Game(cards);
        setContentView(R.layout.game_play);
        setScreenElements();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("onStart", "!!!!!!!!!!!!!!!!");
        this.game.getRandomUnusedCard();
        updateGameState();
        startTimer();
    }

    @Override
    protected void onPause(){
        Log.d("onPause", "!!!!!!!!!!");
        super.onPause();
        this.timer.cancel();
    }

    protected void setScreenElements() {
        final Button correctBtn = (Button) findViewById(R.id.correctBtn);
        final Button skipBtn = (Button) findViewById(R.id.skipBtn);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(this.game.getInitialDeckSize());

        final Game game = this.game;

        correctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.markActiveCardCorrect();
                if (game.gameOver()){
                    Intent i = new Intent(GamePlay.this, MainActivity.class);
                    // TODO: add data to activity about who won
                    startActivity(i);
                } else if (game.roundOver()) {
                    game.nextRound();
                    game.toggleActiveTeam();
                    Intent i = new Intent(GamePlay.this, MainActivity.class);
                    startActivity(i);
                } else {
                    game.getRandomUnusedCard();
                    updateGameState();
                }
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.skipActiveCard();
                game.getRandomUnusedCard();
                updateGameState();
            }
        });
    }

    protected void updateGameState() {
        TextView team1score = (TextView) findViewById(R.id.team1score);
        TextView team2score = (TextView) findViewById(R.id.team2score);
        TextView round = (TextView) findViewById(R.id.round);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        TextView card = (TextView) findViewById(R.id.card);

        if (card != null) {
            card.setText(this.game.getActiveCard());
        }
        if (team1score != null) {
            team1score.setText(String.valueOf(this.game.getTeam1Score()));
        }
        if (team2score != null) {
            team2score.setText(String.valueOf(this.game.getTeam2Score()));
        }
        if (round != null) {
            round.setText(roundText[this.game.getRound() - 1]);
        }

        bar.setProgress(this.game.getUsedDeckSize());


    }

    protected void startTimer() {
        final TextView timer = (TextView) findViewById(R.id.timer);
        final Game game = this.game;

        this.timer = new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                timer.setText(
                        String.valueOf(java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))
                );
            }

            public void onFinish() {
                Log.d("TIMER", "onFinish");
                game.skipActiveCard();
                game.toggleActiveTeam();
                Intent i = new Intent(GamePlay.this, MainActivity.class);
                startActivity(i);
            }
        };
        this.timer.start();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("onSave", "!!!!!!!!!!!!!!!!");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("team1score", this.game.getTeam1Score());
        savedInstanceState.putInt("team2score", this.game.getTeam2Score());
        savedInstanceState.putInt("round", this.game.getRound());
        savedInstanceState.putInt("activeTeam", this.game.getActiveTeam());
        savedInstanceState.putStringArrayList("usedCards", this.game.getUsedCards());
        savedInstanceState.putStringArrayList("unusedCards", this.game.getUnusedCards());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("onRestore", "!!!!!!!!!!!!!!!!");
        super.onRestoreInstanceState(savedInstanceState);
        int team1score = savedInstanceState.getInt("team1score");
        int team2score = savedInstanceState.getInt("team2score");
        int round = savedInstanceState.getInt("round");
        int activeTeam = savedInstanceState.getInt("activeTeam");
        ArrayList<String> usedCards = savedInstanceState.getStringArrayList("usedCards");
        ArrayList<String> unusedCards = savedInstanceState.getStringArrayList("unusedCards");
        this.game = new Game(usedCards, unusedCards, team1score, team2score, round, activeTeam);
        startTimer();
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }
}
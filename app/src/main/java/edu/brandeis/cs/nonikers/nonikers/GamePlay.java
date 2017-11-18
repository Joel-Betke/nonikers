package edu.brandeis.cs.nonikers.nonikers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GamePlay extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        setScreenElements();
    }

    protected void setScreenElements(){
        final Button correctBtn = (Button) findViewById(R.id.correctBtn);
        final Button skipBtn = (Button) findViewById(R.id.skipBtn);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(this.game.getInitialDeckSize());

        final Game game = this.game;

        correctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.markActiveCardCorrect();
                updateGameState();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.skipActiveCard();
                updateGameState();
            }
        });
    }

    protected void updateGameState(){
        TextView team1score = (TextView) findViewById(R.id.team1score);
        TextView team2score = (TextView) findViewById(R.id.team2score);
        TextView round = (TextView) findViewById(R.id.round);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);

        round.setText(R.string.round_2);

        bar.setProgress(this.game.getUsedDeckSize());


    }
}

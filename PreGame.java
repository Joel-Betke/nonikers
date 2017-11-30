package edu.brandeis.cs.nonikers.nonikers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.brandeis.cs.nonikers.nonikers.R;

/**
 * Created by Public on 11/30/2017.
 */

public class PreGame extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregame);
        String[] rules = {"This is round number one. Use ANY WORDS except the name itself, including other card text",
                "This is round number two. Use only ONE WORD", "This is round number three. NO LANGUAGE allowed, only physical gestures and imitations"};
        Intent intent = getIntent();
        String roundNumber = intent.getStringExtra("round");
        String activeTeam = intent.getStringExtra("team");
        TextView roundRules = (TextView) findViewById(R.id.roundRules);
        TextView team = (TextView) findViewById(R.id.activeTeam);
        if (roundNumber == "one") {
            roundRules.setText(rules[0]);
        }
        if (roundNumber == "two") {
            roundRules.setText(rules[1]);
        }
        if (roundNumber == "three") {
            roundRules.setText(rules[2]);
        }
        team.setText("Team " + activeTeam + ", " + "get ready");

    }

    public void start(View v){
        //method completion.
    }



}

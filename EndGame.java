package edu.brandeis.cs.nonikers.nonikers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Public on 11/30/2017.
 */

public class EndGame extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        Intent i = getIntent();
        String endGameTextB = i.getStringExtra("winner");
        TextView endGameText = (TextView)findViewById(R.id.endGameText);
        endGameText.setText("Congratulations "+endGameTextB+" .You excell at party-slanted card games ");
    }

    public void newGame(View view) {
        //new game
    }

}

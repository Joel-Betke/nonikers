package edu.brandeis.cs.nonikers.nonikers;


import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<String> used_cards;
    ArrayList<String> unused_cards;
    int team1Score;
    int team2Score;
    int round;
    String activeCard;
    int activeTeam;
    int initialDeckSize;

    public Game(ArrayList<String> cards){
        this.unused_cards = cards;
        this.initialDeckSize = cards.size();
        this.team1Score = 0;
        this.team2Score = 0;
        this.round = 1;
        this.activeTeam = 1;
    }

    public void incrementTeam1Score(){
        this.team1Score++;
    }

    public void incrementTeam2Score(){
        this.team2Score++;
    }

    public int getTeam1Score(){
        return this.team1Score;
    }

    public int getTeam2Score(){
        return this.team2Score;
    }

    public int getRound(){
        return this.round;
    }

    public int getActiveTeam(){
        return this.activeTeam;
    }

    public String getRandomUnusedCard(){
        int rand = new Random().nextInt(this.unused_cards.size());
        this.activeCard = this.unused_cards.remove(rand);
        return this.activeCard;
    }

    public void markActiveCardCorrect(){
        this.used_cards.add(this.activeCard);
        this.activeCard = null;
        switch (this.activeTeam){
            case(1):
                this.incrementTeam1Score();
                break;
            case(2):
                this.incrementTeam2Score();
                break;
            default:
                break;
        }
    }

    public void skipActiveCard(){
        this.unused_cards.add(this.activeCard);
        this.activeCard = null;
    }

    public int getInitialDeckSize() {
        return this.initialDeckSize;
    }

    public int getUsedDeckSize(){
        return this.unused_cards.size();
    }
}

package edu.brandeis.cs.nonikers.nonikers;


import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<String> used_cards;
    private ArrayList<String> unused_cards;
    private int team1Score;
    private int team2Score;
    private int round;
    private String activeCard;
    private int activeTeam;
    private int initialDeckSize;

    public Game(ArrayList<String> cards){
        this.unused_cards = cards;
        this.used_cards = new ArrayList<>();
        this.initialDeckSize = cards.size();
        this.team1Score = 0;
        this.team2Score = 0;
        this.round = 1;
        this.activeTeam = 1;
    }

    public Game(ArrayList<String> unused_cards,
                ArrayList<String> used_cards,
                int team1Score,
                int team2Score,
                int round,
                int activeTeam
                ) {
        this.unused_cards = unused_cards;
        this.used_cards = used_cards;
        this.initialDeckSize = unused_cards.size() + used_cards.size();
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.round = round;
        this.activeTeam = activeTeam;
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

    public void toggleActiveTeam(){
        switch (this.activeTeam){
            case(1):
                this.activeTeam = 2;
                break;
            case(2):
                this.activeTeam = 1;
                break;
            default:
                break;
        }
    }

    public String getActiveCard() { return this.activeCard; }

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
        return this.used_cards.size();
    }

    public boolean roundOver() {
        return this.unused_cards.size() == 0;
    }

    public void nextRound() {
        this.unused_cards = this.used_cards;
        this.used_cards = new ArrayList<>();
        this.round++;
    }

    public int getActiveTeam() {
        return this.activeTeam;
    }

    public ArrayList<String> getUnusedCards() {
        return this.unused_cards;
    }

    public ArrayList<String> getUsedCards() {
        return this.used_cards;
    }

    public boolean gameOver() {
        return this.unused_cards.size() == 0 && this.round == 3;
    }
}

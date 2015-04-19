package edu.washington.jhand1.lifecounter;

/**
 * Created by Jordan on 4/18/2015.
 */
public class Player {

    private int lives;
    private String name;
    private boolean alive;

    public Player() {
        this.lives = 20;
        this.name = "player";
        this.alive = true;
    }

    public Player(int lives, int playerNumber) {
        this.lives = lives;
        this.name = "player " + playerNumber;
        this.alive = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public void addLife(int n) {
        lives += n;
    }

    public void subtractLife(int n) {
        lives -= n;
    }

    //Allows client to add or subtract lives using positive and negative numbers
    public void updateLives(int n) { lives += n; }

    public int getLives() {
        return lives;
    }
}

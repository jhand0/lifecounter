package edu.washington.jhand1.lifecounter;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private int numPlayers;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numPlayers = 4;
        if (savedInstanceState != null) {
            createPlayers(savedInstanceState.getIntegerArrayList("playerLives"));
        } else {
            createPlayers();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntegerArrayList("playerLives", playerLives());
    }

    public void createPlayers(List<Integer> lives) {
        for (int i = 0; i < lives.size(); i++) {
            players.add(new Player(lives.get(i), i));
        }

    }

    public void createPlayers() {
        players = new ArrayList<Player>();

        for (int i = 0; i <= numPlayers; i++) {
            players.add(new Player(20, i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mFive_0:
                update(0, -5);
                break;
            case R.id.minus_0:
                update(0, -1);
                break;
            case R.id.plus_0:
                update(0, 1);
                break;
            case R.id.pFive_0:
                update(0, 5);
                break;
            case R.id.mFive_1:
                update(1, -5);
                break;
            case R.id.minus_1:
                update(1, -1);
                break;
            case R.id.plus_1:
                update(1, 1);
                break;
            case R.id.pFive_1:
                update(1, 5);
                break;
            case R.id.mFive_2:
                update(2, -5);
                break;
            case R.id.minus_2:
                update(2, -1);
                break;
            case R.id.plus_2:
                update(2, 1);
                break;
            case R.id.pFive_2:
                update(2, 5);
                break;
            case R.id.mFive_3:
                update(3, -5);
                break;
            case R.id.minus_3:
                update(3, -1);
                break;
            case R.id.plus_3:
                update(3, 1);
                break;
            case R.id.pFive_3:
                update(3, 5);
                break;
            default:
                break;
        }

    }

    public void update(int pNum, int lifeChange) {
        TextView count = null;
        Player current = players.get(pNum);
        current.updateLives(lifeChange);

        switch (pNum) {
            case 0:
                count =  (TextView) findViewById(R.id.lCount_0);
                break;
            case 1:
                count = (TextView) findViewById(R.id.lCount_1);
                break;
            case 2:
                count = (TextView) findViewById(R.id.lCount_2);
                break;
            case 3:
                count = (TextView) findViewById(R.id.lCount_3);
                break;
            default:
                break;
        }
        if (count != null) {
            count.setText("Lives: " + current.getLives());
            if (players.get(pNum).getLives() <= 0 && current.isAlive()) {
                current.kill();
                count.setText("Lives: " + current.getLives());
                Toast.makeText(getApplicationContext(), "Player " + (pNum + 1) + " LOSES!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<Integer> playerLives() {
        ArrayList<Integer> lives = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); i++) {
            lives.set(i, players.get(i).getLives());
        }
        return lives;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
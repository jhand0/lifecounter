package edu.washington.jhand1.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
            for (int i = 0; i < players.size(); i++) {
                updateLifeText(i, players.get(i));
            }
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
        players = new ArrayList<Player>();
        for (int i = 0; i < lives.size(); i++) {
            players.add(new Player(lives.get(i), i + 1));
        }

    }

    public void createPlayers() {
        players = new ArrayList<Player>();

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(20, i + 1));
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
        Player current = players.get(pNum);
        current.updateLives(lifeChange);
        if (current.isAlive()) {
            updateLifeText(pNum, current);
            if (players.get(pNum).getLives() <= 0) {
                current.kill();
                Toast.makeText(getApplicationContext(), current.getName() + " LOSES!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), current.getName() + " is already dead!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLifeText(int pNum, Player current) {
        TextView countTxt = null;
        switch (pNum) {
            case 0:
                countTxt = (TextView) findViewById(R.id.lCount_0);
                break;
            case 1:
                countTxt = (TextView) findViewById(R.id.lCount_1);
                break;
            case 2:
                countTxt = (TextView) findViewById(R.id.lCount_2);
                break;
            case 3:
                countTxt = (TextView) findViewById(R.id.lCount_3);
                break;
            default:
                break;
        }
        if (countTxt != null) {
            countTxt.setText("Lives: " + current.getLives());
        }
    }

    public ArrayList<Integer> playerLives() {
        ArrayList<Integer> lives = new ArrayList<Integer>();
        for (Player p : players) {
            lives.add(p.getLives());
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
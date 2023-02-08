package com.example.l4_tictactoe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //public static final String TAG = MainActivity.class.getSimpleName();

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8;
    Button btnNewGame;
    TextView textDisplay;
    String tvString;
    int counter = 0;
    private String[] playBox = {"","","","","","","","",""};
    boolean gameEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.tictactoe_icon);

        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        textDisplay = findViewById(R.id.textDisplay);
        btnNewGame = findViewById(R.id.btnNewGame);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button0:
                checkBoxes(button0, 0);
                break;
            case R.id.button1:
                checkBoxes(button1, 1);
                break;
            case R.id.button2:
                checkBoxes(button2, 2);
                break;
            case R.id.button3:
                checkBoxes(button3, 3);
                break;
            case R.id.button4:
                checkBoxes(button4, 4);
                break;
            case R.id.button5:
                checkBoxes(button5, 5);
                break;
            case R.id.button6:
                checkBoxes(button6, 6);
                break;
            case R.id.button7:
                checkBoxes(button7, 7);
                break;
            case R.id.button8:
                checkBoxes(button8, 8);
                break;
        }
    }

    public void checkBoxes(Button chosenBtn, int btnIndex){
        //check if filled
        if (chosenBtn.getText().toString().equals("") && !gameEnd) {
            //empty, fill the box, calculate result
            if (counter%2 == 0) {
                chosenBtn.setText("X");
                //playBox[btnIndex]='X';
                playBox[btnIndex]="X";
                counter++;
                calculateResult("X");
            }
            else{
                chosenBtn.setText("O");
                //playBox[btnIndex]='O';
                playBox[btnIndex]="O";
                counter++;
                calculateResult("O");
            }
        }
        else if (gameEnd){
            Toast.makeText(MainActivity.this, "Game ends. Press New Game", Toast.LENGTH_LONG).show();
        }
        else{ //cannot be shown
            Toast.makeText(MainActivity.this, "Box filled, choose another box!", Toast.LENGTH_LONG).show();
        }
    }

    public void calculateResult(String player){
        //check any win
        if ((playBox[0].equals(player)) && (playBox[1].equals(player)) && (playBox[2].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[3].equals(player)) && (playBox[4].equals(player)) && (playBox[5].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[6].equals(player)) && (playBox[7].equals(player)) && (playBox[8].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[0].equals(player)) && (playBox[3].equals(player)) && (playBox[6].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[1].equals(player)) && (playBox[4].equals(player)) && (playBox[7].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[2].equals(player)) && (playBox[5].equals(player)) && (playBox[8].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[2].equals(player)) && (playBox[4].equals(player)) && (playBox[6].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        else if ((playBox[0].equals(player)) && (playBox[4].equals(player)) && (playBox[8].equals(player))){
            tvString = "Player " + player + " wins!";
            gameEnd = true;
        }
        //if all filled, 0 wins
        else if (arrayAllFilled()){
            tvString = "It's a tie!";
        }
        //if no win, switch user
        else{
            if (counter%2 == 0) {
                tvString = "Player X\'s turn!";
            }
            else{
                tvString = "Player O\'s turn!";
            }
        }
        textDisplay.setText(tvString);
    }

    public boolean arrayAllFilled() {
        boolean allFilled = true;
        String tempStr;

        for (int i=0; i<playBox.length; i++) {
            tempStr = playBox[i];
            if (tempStr.equals("")) {
                allFilled = false;
                break;
            }
        }
        return allFilled;
    }

    public void startNewGame(View view) {
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");

        playBox = new String[]{"","","","","","","","",""};
        gameEnd = false;
        counter = 0;

        textDisplay.setText("New Game. Player X\'s turn");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("textViewString", tvString);
        outState.putStringArray("playBoxArr",playBox);
        outState.putInt("counter", counter);
        outState.putBoolean("game_end",gameEnd);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState!=null){
            tvString = savedInstanceState.getString("textViewString", "New Game. Player X\'s turn");
            playBox = savedInstanceState.getStringArray("playBoxArr");
            counter = savedInstanceState.getInt("counter");
            gameEnd = savedInstanceState.getBoolean("game_end");

            textDisplay.setText(tvString);
            button0.setText(playBox[0]);
            button1.setText(playBox[1]);
            button2.setText(playBox[2]);
            button3.setText(playBox[3]);
            button4.setText(playBox[4]);
            button5.setText(playBox[5]);
            button6.setText(playBox[6]);
            button7.setText(playBox[7]);
            button8.setText(playBox[8]);
        }
    }
}
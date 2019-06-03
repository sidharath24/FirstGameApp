package com.example.owner.firstgame;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult, scoreText;
    Button rollButton;
    int score, die1, die2, die3;

    ArrayList<ImageView> diceImageViews;

    ArrayList<Integer> dice;
    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;
        Toast.makeText(this,"Welcome to DiceOut",Toast.LENGTH_SHORT).show();

        rollResult = (TextView)findViewById(R.id.rollResult);
        rollButton = (Button)findViewById(R.id.rollButton);
        scoreText= (TextView)findViewById(R.id.scoreText);

        rand = new Random();

        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView)findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
    }

    public void rollDice(View v){
        rollResult.setText("Clicked");
    die1 = rand.nextInt(6)+1;
    die2 = rand.nextInt(6)+1;
    die3 = rand.nextInt(6)+1;

    dice.clear();
    dice.add(die1);
    dice.add(die2);
    dice.add(die3);

    for(int dieOfSet = 0; dieOfSet<3; dieOfSet++){
        String imageName = "face" + dice.get(dieOfSet) + ".png";

        try{
            InputStream stream = getAssets().open(imageName);
            Drawable d = Drawable.createFromStream(stream,null);
            diceImageViews.get(dieOfSet).setImageDrawable(d);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    String msg;

    if(die1==die2&&die1==die3) {
        int scoreKeeper = die1 * 100;
        msg = "You scored a triple for " + scoreKeeper + " points.";
        score += scoreKeeper;
    }
    else if(die1 == die2 || die1 == die3 || die2==die3){
        msg = "You scored double for 50 points.";
                score += 50;
    }
    else{
        msg = "You did not score. Please try again!";
    }

    rollResult.setText(msg);
    scoreText.setText("Score: "+score);
    }



}

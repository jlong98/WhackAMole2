package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler;
    private Boolean off;
    private updateMole upMole;
    private EditText moleCount;
    private int count;
    private int health;
    private EditText healthCount;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        button = findViewById(R.id.button);
        off = false;
        upMole = new updateMole();
        count = 0;
        health = 5;
        healthCount = findViewById(R.id.healthCount);
        moleCount = findViewById(R.id.moleCount);
        moleLocation = rand.nextInt(16);
        for(int i=0;i<16;i++){
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view,null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if(i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }


    }
    public void startPressed(View v){
        if(off){
            button.setText("START");
            off = false;
            count=0;
            health=5;
            moleCount.setText(count+"");
            healthCount.setText(health+"");
            handler.removeCallbacks(upMole);
        }else{
            button.setText("STOP");
            off = true;
            handler.postDelayed(upMole,1000);

        }
    }
    public class updateMole implements Runnable{
        public void run(){
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(upMole,1000);

        }
    }
    public void updateCount(View v){
        if(off) {
            if (imageViews[moleLocation].isPressed()) {
                count++;
                moleCount.setText(count + "");
            } else {
                health--;
                if (health == 0) {
                    button.setText("START");
                    count = 0;
                    health = 5;
                    moleCount.setText(count + "");
                    healthCount.setText(health + "");
                    off = false;
                    handler.removeCallbacks(upMole);
                    Toast.makeText(this, "You lost, try again?", Toast.LENGTH_LONG).show();
                }
                healthCount.setText(health+"");
            }
        }
    }
}

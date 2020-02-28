package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {
    private RadioButton frogButton;
    private RadioButton turkeyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        frogButton = findViewById(R.id.frogButton);
        turkeyButton = findViewById(R.id.turkeyButton);

    }
    @Override
    public void onBackPressed(){
        int image;
        if(frogButton.isChecked())
            image=2;
        else if(turkeyButton.isChecked())
            image=3;
        else
            image=1;
        Intent i = new Intent();
        i.putExtra("IMAGE",image);
        setResult(RESULT_OK,i);
        finish();
    }
}

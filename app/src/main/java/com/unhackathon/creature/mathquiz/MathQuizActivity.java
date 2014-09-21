package com.unhackathon.creature.mathquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unhackathon.creature.R;
import com.unhackathon.creature.mastermind.About;
import com.unhackathon.creature.mastermind.PlayGame;

/**
 * Created by Milk on 9/21/2014.
 */
public class MathQuizActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((Button) findViewById(R.id.play_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.about_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.exit_btn)).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_btn:
                Intent i = new Intent(this, PlayGame.class);
                startActivity(i);
                break;
            case R.id.about_btn:
                Intent j = new Intent(this, About.class);
                startActivity(j);
                break;
            case R.id.exit_btn:
                System.exit(0);
                break;
        }
    }
}
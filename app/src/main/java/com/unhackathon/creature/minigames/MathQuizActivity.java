package com.unhackathon.creature.minigames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kilobolt.framework.implementation.AndroidGame;
import com.unhackathon.creature.R;
import com.unhackathon.creature.UserStats;
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
        setContentView(R.layout.mathquizmain);
        ((Button)findViewById(R.id.submit_btn)).setOnClickListener(this);
        ((EditText) findViewById(R.id.answer)).setOnClickListener(this);
        TextView txtFirst = ((TextView) findViewById(R.id.first));
        TextView txtSec = ((TextView) findViewById(R.id.sec));
        TextView oper = ((TextView) findViewById(R.id.operator));

        int fir = (int) (Math.random() * 100);
        int sec = (int) (Math.random() * 100);

        if(fir < sec)
        {
            int temp = fir;
            fir = sec;
            sec = temp;
        }

        if(fir < 10)
            txtFirst.setText("0" + fir);
        else
            txtFirst.setText(fir + "");

        if(sec < 10)
            txtSec.setText("0" + sec);
        else
            txtSec.setText(sec + "");

        if(Math.random() < .5)
            oper.setText("+");
        else
            oper.setText("-");
    }

    public void onClick(View view) {

        if(view.getId() == R.id.submit_btn)
        {
            TextView txtFirst = ((TextView) findViewById(R.id.first));
            TextView txtSec = ((TextView) findViewById(R.id.sec));
            TextView suc = ((TextView) findViewById(R.id.success));
            TextView fail = ((TextView) findViewById(R.id.fail));
            TextView oper = ((TextView) findViewById(R.id.operator));
            EditText an = ((EditText) findViewById(R.id.answer));
            int first = Integer.parseInt(txtFirst.getText().toString());
            int second = Integer.parseInt(txtSec.getText().toString());
            int answer = 0;

            if(oper.getText().equals("+"))
                answer = first + second;
            else
                answer = first - second;

            if(Integer.parseInt(an.getText().toString()) == answer)
            {
                suc.setVisibility(view.VISIBLE);
                fail.setVisibility(view.INVISIBLE);
                //AndroidGame ag = (AndroidGame)game;

                UserStats stats = new UserStats(this);
                stats.addExperience(600);

                this.recreate();
             }
            else
            {
                suc.setVisibility(view.INVISIBLE);
                fail.setVisibility(view.VISIBLE);
            }
        }

    }
}
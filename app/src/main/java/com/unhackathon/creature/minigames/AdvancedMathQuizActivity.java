package com.unhackathon.creature.minigames;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unhackathon.creature.R;
import com.unhackathon.creature.UserStats;

/**
 * Created by Milk on 9/21/2014.
 */
public class AdvancedMathQuizActivity extends Activity implements View.OnClickListener {
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

        if(Math.random() < .5)
            oper.setText("x");
        else
            oper.setText("÷");

        if(oper.getText().equals("x"))
        {
            int fir = (int) (Math.random() * 50);
            int sec = (int) (Math.random() * 50);
            if(fir < 10)
                txtFirst.setText("0" + fir);
            else
                txtFirst.setText(fir + "");

            if(sec < 10)
                txtSec.setText("0" + sec);
            else
                txtSec.setText(sec + "");
        }
        else
        {
            int sec = (int) (Math.random() * 25);
            int fir = ((int)(Math.random() * 25)) * sec;

            while(sec == 0 || fir == 0)
            {
                sec = (int) (Math.random() * 25);
                fir = ((int)(Math.random() * 25)) * sec;
            }

            if(fir < 10)
                txtFirst.setText("0" + fir);
            else
                txtFirst.setText(fir + "");

            if(sec < 10)
                txtSec.setText("0" + sec);
            else
                txtSec.setText(sec + "");
        }
    }

    public void onClick(View view) {

        if(view.getId() == R.id.submit_btn)
        {
            TextView txtFirst = ((TextView) findViewById(R.id.first));
            TextView txtSec = ((TextView) findViewById(R.id.sec));
            TextView suc = ((TextView) findViewById(R.id.success));
            TextView fail = ((TextView) findViewById(R.id.fail));
            EditText an = ((EditText) findViewById(R.id.answer));
            TextView oper = ((TextView) findViewById(R.id.operator));
            int first = Integer.parseInt(txtFirst.getText().toString());
            int second = Integer.parseInt(txtSec.getText().toString());
            int answer = 0;
            if(oper.getText().equals("x"))
                answer = first * second;
            else
                answer = first/second;

            if(Integer.parseInt(an.getText().toString()) == answer)
            {
                suc.setVisibility(view.VISIBLE);
                fail.setVisibility(view.INVISIBLE);
                this.recreate();

                UserStats stats = new UserStats(this);
                stats.addExperience(120);

            }
            else
            {
                suc.setVisibility(view.INVISIBLE);
                fail.setVisibility(view.VISIBLE);
            }
        }

    }
}

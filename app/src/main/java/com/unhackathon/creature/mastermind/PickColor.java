package com.unhackathon.creature.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.unhackathon.creature.R;

public class PickColor extends Activity implements View.OnClickListener{
    /** Called when the activity is first created. */
	//String choice;
	@Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_color);
        ((ImageButton)findViewById(R.id.ibtnBlack)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.ibtnWhite)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.ibtnRed)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.ibtnGreen)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.ibtnYellow)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.ibtnBlue)).setOnClickListener(this);
    }
	public void onClick(View view)
	{
		int choice = -1;
		switch(view.getId())
		{
		case R.id.ibtnBlack:
			choice = R.drawable.black;
			break;
		case R.id.ibtnWhite:
			choice = R.drawable.white;
			break;
		case R.id.ibtnBlue:
			choice = R.drawable.blue;
			break;
		case R.id.ibtnGreen:
			choice = R.drawable.green;
			break;
		case R.id.ibtnRed:
			choice = R.drawable.red;
			break;
		case R.id.ibtnYellow:
			choice = R.drawable.yellow;
			break;
		}
		Intent resultIntent = new Intent();
		Bundle b = new Bundle();
		b.putInt("Color", choice);
		resultIntent.putExtras(b);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	
	
}
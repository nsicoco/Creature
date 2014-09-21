//Francis Rohner, V2.0
package com.unhackathon.creature.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.unhackathon.creature.R;
import com.unhackathon.creature.UserStats;

import java.util.Random;

public class PlayGame extends Activity implements View.OnClickListener {
	/** Called when the activity is first created. */
	ImageButton lastSelected;
	int currentRow = 1;
	int currentButton;
	int currentPlayerCode = 0, picks = 0;
	Integer colorsUsed[] = new Integer[]{0,0,0,0,0,0,0,0};
	Integer computerCode[];
	Integer playerCode[] = new Integer[]{0,0,0,0};
	Random randomGenerator = new Random();
	ImageButton[][] guesses = new ImageButton[8][4];
	ImageButton[] solution = new ImageButton[4];

	boolean gameover = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_board);

		generateComputerCode();


		currentButton = R.id.G1_0;
		//Top most in xml
		Log.v("G1_0 Id:",Integer.toString( R.id.G1_0));
		Log.v("G1_3 Id:",Integer.toString( R.id.G1_3));

		Log.v("G2_0 Id:",Integer.toString( R.id.G2_0));
		Log.v("G2_3 Id:",Integer.toString( R.id.G2_3));

		Log.v("G3_0 Id:",Integer.toString( R.id.G3_0));
		Log.v("G3_3 Id:",Integer.toString( R.id.G3_3));

		//Bottom most in xml
		Log.v("G8_0 Id:",Integer.toString( R.id.G8_0));
		Log.v("G8_3 Id:",Integer.toString( R.id.G8_3));


		guesses[0][0] = ((ImageButton)findViewById(R.id.G1_0));
		guesses[0][1] = ((ImageButton)findViewById(R.id.G1_1));
		guesses[0][2] = ((ImageButton)findViewById(R.id.G1_2));
		guesses[0][3] = ((ImageButton)findViewById(R.id.G1_3));

		guesses[1][0] = ((ImageButton)findViewById(R.id.G2_0));
		guesses[1][1] = ((ImageButton)findViewById(R.id.G2_1));
		guesses[1][2] = ((ImageButton)findViewById(R.id.G2_2));
		guesses[1][3] = ((ImageButton)findViewById(R.id.G2_3));

		guesses[2][0] = ((ImageButton)findViewById(R.id.G3_0));
		guesses[2][1] = ((ImageButton)findViewById(R.id.G3_1));
		guesses[2][2] = ((ImageButton)findViewById(R.id.G3_2));
		guesses[2][3] = ((ImageButton)findViewById(R.id.G3_3));

		guesses[3][0] = ((ImageButton)findViewById(R.id.G4_0));
		guesses[3][1] = ((ImageButton)findViewById(R.id.G4_1));
		guesses[3][2] = ((ImageButton)findViewById(R.id.G4_2));
		guesses[3][3] = ((ImageButton)findViewById(R.id.G4_3));

		guesses[4][0] = ((ImageButton)findViewById(R.id.G5_0));
		guesses[4][1] = ((ImageButton)findViewById(R.id.G5_1));
		guesses[4][2] = ((ImageButton)findViewById(R.id.G5_2));
		guesses[4][3] = ((ImageButton)findViewById(R.id.G5_3));

		guesses[5][0] = ((ImageButton)findViewById(R.id.G6_0));
		guesses[5][1] = ((ImageButton)findViewById(R.id.G6_1));
		guesses[5][2] = ((ImageButton)findViewById(R.id.G6_2));
		guesses[5][3] = ((ImageButton)findViewById(R.id.G6_3));

		guesses[6][0] = ((ImageButton)findViewById(R.id.G7_0));
		guesses[6][1] = ((ImageButton)findViewById(R.id.G7_1));
		guesses[6][2] = ((ImageButton)findViewById(R.id.G7_2));
		guesses[6][3] = ((ImageButton)findViewById(R.id.G7_3));

		guesses[7][0] = ((ImageButton)findViewById(R.id.G8_0));
		guesses[7][1] = ((ImageButton)findViewById(R.id.G8_1));
		guesses[7][2] = ((ImageButton)findViewById(R.id.G8_2));
		guesses[7][3] = ((ImageButton)findViewById(R.id.G8_3));

		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 4; j++)
				guesses[i][j].setOnClickListener(this);

		solution[0] = ((ImageButton)findViewById(R.id.S_0));
		solution[1] = ((ImageButton)findViewById(R.id.S_1));
		solution[2] = ((ImageButton)findViewById(R.id.S_2));
		solution[3] = ((ImageButton)findViewById(R.id.S_3));

	}
	public void onClick(View view)
	{
		if(!gameover)
		{

			int id = view.getId();
			if(id != R.id.S_0 && id != R.id.S_1 && id != R.id.S_2 && id != R.id.S_3 && id == currentButton)
			{
				lastSelected = ((ImageButton)findViewById(view.getId()));
				Intent i = new Intent(this, PickColor.class);
				startActivityForResult(i, 100);
			}
			else
			{
				CharSequence text = "Start at the first left-most button of Guess 1 work your way to the right side then repeat the proccess with Guess 2 cheers!";
				Toast wheat = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
				wheat.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				wheat.show();
			}
		}
	}


	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
		super.onActivityResult(requestCode, resultCode, data); 
		switch(requestCode) { 
		case (100) : { 
			if (resultCode == Activity.RESULT_OK) { 
				int color = data.getExtras().getInt("Color");
				if(!isUsed(color))
				{			
					use(color);
					lastSelected.setImageDrawable(getResources().getDrawable(color));
					isGameOver();
					if(gameover)
						break;
					if(currentRow == 4)
					{
						int numCorrect = 0;
						for(int i = 0; i < 4; i++)
							if(playerCode[i].equals(computerCode[i]))
								numCorrect++;
							CharSequence text = numCorrect + " colors are in the correct place.";
							Toast white = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
							white.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							white.show();

						currentButton -= 8;

						currentRow = 1;
						colorsUsed = new Integer[]{0,0,0,0,0,0,0,0};
						playerCode = new Integer[]{0,0,0,0};
						currentPlayerCode = 0;
					}
					else
					{

						currentButton++;
						currentRow++;
						currentPlayerCode++;
					}

				}
				else
				{

					CharSequence text = "Color already chosen =/";
					Toast rye = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
					rye.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					rye.show();
				}

				Log.d("CurrentButton:", Integer.toString(currentButton));

			} 
			break; 
		} 
		} 

	}
	public void use(int choice)
	{
		if(choice == R.drawable.black)
		{
			colorsUsed[0] = 1;
			playerCode[currentPlayerCode] = 1;
		}

		else if(choice == R.drawable.white)
		{
			colorsUsed[1] = 1;
			playerCode[currentPlayerCode] = 2;
		}

		else if(choice == R.drawable.yellow)
		{
			colorsUsed[2] = 1;
			playerCode[currentPlayerCode] = 3;
		}

		else if(choice == R.drawable.blue)
		{
			colorsUsed[3] = 1;
			playerCode[currentPlayerCode] = 4;
		}

		else if(choice == R.drawable.red)
		{
			colorsUsed[4] = 1;
			playerCode[currentPlayerCode] = 5;
		}

		else//if(choice == R.drawable.green)
		{
			colorsUsed[5] = 1;
			playerCode[currentPlayerCode] = 6;
		}

		picks++;
		Log.d("Player Code:", intToColor(playerCode[0]) + "," + intToColor(playerCode[1]) + "," + intToColor(playerCode[2]) + "," + intToColor(playerCode[3]));
		if(isMatch())
			Log.d("Holy smokes", "MATCH!!!!");


	}
	public String intToColor(int x)
	{
		if(x == 1)
			return "Black";
		else if(x == 2)
			return "White";
		else if(x ==3)
			return "Yellow";
		else if(x == 4)
			return "Blue";
		else if(x == 5)
			return "Red";
		else if(x == 6)
			return "Green";
		else 
			return "Nothing";
	}
	public boolean isUsed(int choice)
	{
		int used = 0;
		if(choice == R.drawable.black)//0, 1
			used = colorsUsed[0];
		else if(choice == R.drawable.white)//1, 2
			used = colorsUsed[1];
		else if(choice == R.drawable.yellow)//2, 3
			used = colorsUsed[2];	
		else if(choice == R.drawable.blue)//3, 4
			used = colorsUsed[3];
		else if(choice == R.drawable.red)//4, 5 Black, Yellow, White, Red
			used = colorsUsed[4];
		else//if(choice == R.drawable.green) 5, 6
			used = colorsUsed[5];


		if(used == 1)
			return true;
		else return false;
	}
	public void generateComputerCode()
	{
		int n1 = randomGenerator.nextInt(5) + 1;
		int n2 = n1, n3 = n2, n4 = n3;
		while(n2 == n1)
		{
			n2 = randomGenerator.nextInt(5) + 1;
		}
		while(n3 == n1 || n3 == n2)
		{
			n3 = randomGenerator.nextInt(5) + 1;
		}
		while(n4 == n1 || n4 == n2 || n4 == n3)
		{
			n4 = randomGenerator.nextInt(5) + 1;
		}

		computerCode = new Integer[]{n1,n2,n3,n4};
		Log.d("Computer Code:", intToColor(n1) + "," + intToColor(n2) + "," + intToColor(n3) + "," + intToColor(n4));

	}
	public void isGameOver()
	{
		CharSequence text = "Congratulations, you won!";
		CharSequence text2 = "Better luck next time.";
		Toast pumpernickel = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
		pumpernickel.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

		if(isMatch())
		{
			pumpernickel.show();
			showSolution();
			gameover = true;

            UserStats stats = new UserStats(this);
            stats.addExperience(160);

        }
		else if(picks == 32)
		{
			pumpernickel.setText(text2);
			pumpernickel.show();
			showSolution();
			gameover = true;

		}


	}
	public void showSolution()
	{
		for(int i = 0; i < 4; i++)
			solution[i].setImageDrawable(getResources().getDrawable(intToDrawableInt(computerCode[i])));
	}
	public void clearSolution()
	{
		for(int i = 0; i < 4; i++)
			solution[i] = new ImageButton(getApplicationContext());
	}
	public void clearGuesses()
	{
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 4; i++)
				guesses[i][j] = new ImageButton(getApplicationContext());
	}
	public int intToDrawableInt(int x)
	{
		String color = intToColor(x);
		if(color.equals("Black"))
			return R.drawable.black;
		else if(color.equals("White"))
			return R.drawable.white;
		else if(color.equals("Yellow"))
			return R.drawable.yellow;
		else if(color.equals("Blue"))
			return R.drawable.blue;
		else if(color.equals("Red"))
			return R.drawable.red;
		else 
			return R.drawable.green;
	}
	public boolean isMatch()
	{
		boolean match = true;
		for(int i = 0; i < 4 && match; i++)
		{
			if(!playerCode[i].equals(computerCode[i]))
				match = false;
		}
		return match;
	}


}
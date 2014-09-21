package com.unhackathon.creature.screens;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidGame;
import com.kilobolt.framework.implementation.AndroidImage;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.mastermind.MastermindActivity;
import com.unhackathon.creature.minigames.AdvancedMathQuizActivity;
import com.unhackathon.creature.minigames.MathQuizActivity;

import java.util.List;

/**
 * Created by Noel on 9/21/2014.
 */
public class GameMenuScreen extends Screen {
    private Button startOverButton;

    public GameMenuScreen(Game game) {
        super(game);
        Graphics g = game.getGraphics();

        startOverButton = new AndroidButton("StartOver", Assets.startOverButton, Assets.startOverButtonPressed, new Point(20, 20));
        addButton(startOverButton);
        int yOffset = 20;
        if(Assets.avatar != null) {
            yOffset += 20 + Assets.avatar.getHeight();
        }

        int x = (g.getWidth() - Assets.mastermindButton.getWidth()) / 2;
        addButton(new AndroidButton("Mastermind", Assets.mastermindButton, Assets.mastermindButtonPressed, new Point(x, yOffset)));
        yOffset += 20 + Assets.mastermindButton.getHeight();

        x = (g.getWidth() - Assets.anagramButton.getWidth()) / 2;
        addButton(new AndroidButton("Anagram", Assets.anagramButton, Assets.anagramPressedButton, new Point(x, yOffset)));

        yOffset += 20 + Assets.anagramButton.getHeight();
        
        x = (g.getWidth() - Assets.mathQuizButton.getWidth()) / 2;
        addButton(new AndroidButton("MathQuiz", Assets.mathQuizButton, Assets.mathQuizButtonPressed, new Point(x, yOffset)));
        
        yOffset += 20 + Assets.mathQuizButton.getHeight();
        
        x = (g.getWidth() - Assets.advancedMathQuizButton.getWidth()) / 2;
        addButton(new AndroidButton("AdvancedMathQuiz", Assets.advancedMathQuizButton, Assets.advancedMathQuizButtonPressed, new Point(x, yOffset)));
        
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for(Input.TouchEvent event : touchEvents) {
            for(Button button : getButtons()) {
                Rect bounds = button.getBounds();
                if(inBounds(event, bounds.left, bounds.top, bounds.width(), bounds.height())) {
                    if(event.type == Input.TouchEvent.TOUCH_DOWN || event.type == Input.TouchEvent.TOUCH_HOLD
                            || event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                        button.setPressed(true);
                    }
                    else if(event.type == Input.TouchEvent.TOUCH_UP)
                    {
                        button.setPressed(false);
                        //TODO
                        AndroidGame ag = (AndroidGame) game;
                        if(button.getName().equalsIgnoreCase("Mastermind")) {
                            Intent intent = new Intent(ag, MastermindActivity.class);
                            ag.startActivity(intent);
                            return;
                        }
                        else if(button.getName().equals("Anagram"))
                        {
                            game.setScreen(new GameScreen(game));
                            return;
                        }
                        else if(button.getName().equalsIgnoreCase("StartOver")) {
                            game.setScreen(new CharacterCreationScreen(game));
                        }
                        else if(button.getName().equals("MathQuiz"))
                        {
                            Intent intent = new Intent(ag, MathQuizActivity.class);
                            ag.startActivity(intent);
                            return;
                        }
                        else if(button.getName().equals("AdvancedMathQuiz"))
                        {
                            Intent intent = new Intent(ag, AdvancedMathQuizActivity.class);
                            ag.startActivity(intent);
                            return;
                        }
                    }
                } else {
                    button.setPressed(false);
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menuBackground, 0, 0);

        int yOffset = 20;

        if(Assets.avatar != null) {
            int x = (g.getWidth() - Assets.avatar.getWidth()) / 2;
            g.drawImage(Assets.avatar, x, yOffset);
            yOffset += 20 + Assets.avatar.getHeight();
        }

        for(Button button : getButtons()) {
            Rect bounds = button.getBounds();
            g.drawImage(button.getImage(), bounds.left, bounds.top);
        }
        /*
        for(int i = 0; i < buttons.size(); i++) {
            Rect bounds = buttons.get(i).getBounds();
//            if(buttons.get(i).getName().equals("Anagram"))
//            {
//                g.drawImage(buttons.get(i).getImage(), 250, 400);
//                return;
//            }
            int row = i / 2;
            int col = (i % 2) + 1;
            int x = (g.getWidth() - col*(10 + bounds.width())) / 2;
            int y = yOffset + row*(bounds.height() + 20);
            buttons.get(i).setLocation(new Point(x, y));
            g.drawImage(buttons.get(i).getImage(), x, y);
        }//*/
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        game.setScreen(new MainMenuScreen(game));
    }
}
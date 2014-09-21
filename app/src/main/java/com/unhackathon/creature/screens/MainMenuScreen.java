package com.unhackathon.creature.screens;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;

import java.util.List;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidFileIO;
import com.kilobolt.framework.implementation.AndroidGame;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.PreferenceKeys;


public class MainMenuScreen extends Screen {
    boolean startButtonDown = false;
    boolean quitButtonDown = false;

    public MainMenuScreen(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        int x = (g.getWidth() - Assets.startButton.getWidth()) / 2;
        addButton(new AndroidButton("Play", Assets.startButton, Assets.startButtonPressed, new Point(x, 200)));
        addButton(new AndroidButton("Quit", Assets.quitButton, Assets.quitButtonPressed, new Point(x, 600)));
    }


    @Override
    public void update(float deltaTime) {

        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            for(Button button: this.getButtons())
            {
                if(inBounds(event, button.getBounds().left, button.getBounds().top, button.getBounds().width(), button.getBounds().height()))
                {
                    if(event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_HOLD
                            || event.type == TouchEvent.TOUCH_DRAGGED) {
                        button.setPressed(true);
                    }
                    else if(event.type == TouchEvent.TOUCH_UP)
                    {
                        button.setPressed(false);

                        //game.setScreen(new AnagramScreen(game));
                        if(button.getName().equalsIgnoreCase("Play")) {
                            AndroidFileIO io = new AndroidFileIO((AndroidGame) game);
                            SharedPreferences pref = io.getSharedPref();
                            boolean characterCreated = pref.getBoolean(PreferenceKeys.CHARACTER_CREATED, false);

                            Screen screen;
                            if(characterCreated) {
                                screen = new GameMenuScreen(game);
                            } else {
                                screen = new CharacterCreationScreen(game);
                            }
                            game.setScreen(screen);
                        } else if(button.getName().equalsIgnoreCase("Quit")) {
                            game.quit();
                        }

                    }
                }
                else
                    button.setPressed(false);


            }


        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
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
        //g.drawImage(Assets.menuBackground, 0, 0);
        g.drawRect(0, 0, g.getWidth(), g.getHeight(), Color.rgb(242, 242, 242));
        for(Button button: getButtons())
            g.drawImage(button.getImage(), button.getBounds().left, button.getBounds().top);
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
        //Display "Exit Game?" Box


    }
}

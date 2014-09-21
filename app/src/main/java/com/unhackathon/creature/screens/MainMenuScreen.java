package com.unhackathon.creature.screens;

import android.util.Log;

import java.util.List;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.unhackathon.creature.Assets;


public class MainMenuScreen extends Screen {
    boolean startButtonDown = false;
    boolean quitButtonDown = false;

    public MainMenuScreen(Game game) {
        super(game);
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
                if(inBounds(event, button.getBounds().left, button.getBounds().top, button.getBounds().right, button.getBounds().bottom))
                {
                    if(event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_HOLD
                            || event.type == TouchEvent.TOUCH_DRAGGED) {
                        button.setPressed(true);
                    }
                    else if(event.type == TouchEvent.TOUCH_UP)
                    {
                        button.setPressed(false);
                        game.setScreen(new GameScreen(game));

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
        g.drawImage(Assets.menuBackground, 0, 0);
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

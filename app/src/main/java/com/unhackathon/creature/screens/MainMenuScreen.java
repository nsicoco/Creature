package com.unhackathon.creature.screens;

import android.content.Intent;
import android.provider.MediaStore;

import java.util.List;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.Camera;
import com.unhackathon.creature.MainActivity;


public class MainMenuScreen extends Screen {
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
            if (event.type == TouchEvent.TOUCH_UP) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ((MainActivity) game).startActivityForResult(intent, 100);

                if (inBounds(event, 0, 0, 250, 250)) {
                    //START GAME
                    game.setScreen(new GameScreen(game));
                }


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
        g.drawImage(Assets.startButton, 100, 100);
        g.drawImage(Assets.quitButton, 1000, 300);
        if(Camera.getCapturedImage() != null) {
            g.drawImage(Camera.getCapturedAndroidImage(), 0, 0);
        }
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

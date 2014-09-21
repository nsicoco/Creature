package com.unhackathon.creature.screens;

import android.graphics.Color;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input;
import com.kilobolt.framework.Screen;
import com.unhackathon.creature.MainActivity;

import java.util.List;

/**
 * Created by Noel on 9/21/2014.
 */
public class FBScreen extends Screen {
    private static float gravity = -0.01f;
    private float height;
    private float v;

    public FBScreen(Game game) {
        super(game);
        height = 50;
        v = 2;
    }

    @Override
    public void update(float deltaTime) {
        v += gravity * deltaTime;
        height += v * deltaTime;

        if(MainActivity.fap) {
            MainActivity.fap = false;
            v = 1;
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, g.getWidth(), g.getHeight(), Color.WHITE);

        g.drawRect(5, 200 - (int)height, 75, 75, Color.RED);
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

    }
}

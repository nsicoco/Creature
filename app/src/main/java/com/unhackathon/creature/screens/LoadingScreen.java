package com.unhackathon.creature.screens;

/**
 * Created by francis on 9/20/14.
 */
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.unhackathon.creature.Assets;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuBackground = g.newImage("img/menuBackground.png", ImageFormat.RGB565);
        Assets.startButton = g.newImage("img/startButton.png", ImageFormat.RGB565);
        Assets.quitButton = g.newImage("img/quitButton.png", ImageFormat.RGB565);
        //Assets.click = game.getAudio().createSound("explode.ogg");



        game.setScreen(new MainMenuScreen(game));


    }


    @Override
    public void paint(float deltaTime) {


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
package com.unhackathon.creature.screens;

/**
 * Created by francis on 9/20/14.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidGame;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.mastermind.MastermindActivity;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) { super(game); }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuBackground = g.newImage("img/menuBackground.png", ImageFormat.RGB565);

        Assets.startButton = g.newImage("img/startButton.png", ImageFormat.RGB565);
        Assets.quitButton = g.newImage("img/quitButton.png", ImageFormat.RGB565);

        Assets.startButtonPressed = g.newImage("img/startButtonPressed.png", ImageFormat.RGB565);
        Assets.quitButtonPressed = g.newImage("img/quitButtonPressed.png", ImageFormat.RGB565);

        //Assets.click = game.getAudio().createSound("explode.ogg");

        MainMenuScreen mainMenuScreen = new MainMenuScreen(game);
        mainMenuScreen.addButton(new AndroidButton("Play", Assets.startButton, Assets.startButtonPressed, new Point(250, 100)));
        mainMenuScreen.addButton(new AndroidButton("Quit", Assets.quitButton, Assets.quitButtonPressed, new Point(250, 600)));
        //game.setScreen(mainMenuScreen);
        AndroidGame a = (AndroidGame)game;
        a.startActivity(new Intent(a,MastermindActivity.class));
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
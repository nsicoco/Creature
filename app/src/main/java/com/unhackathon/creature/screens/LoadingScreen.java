package com.unhackathon.creature.screens;

/**
 * Created by francis on 9/20/14.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Environment;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidFileIO;
import com.kilobolt.framework.implementation.AndroidGame;
import com.kilobolt.framework.implementation.AndroidImage;
import com.unhackathon.creature.Assets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

        Assets.takePictureButton = g.newImage("img/takePictureButton.png", ImageFormat.RGB565);
        Assets.takePictureButtonPressed = g.newImage("img/takePictureButtonPressed.png", ImageFormat.RGB565);

        Assets.confirmButton = g.newImage("img/confirmButton.png", ImageFormat.RGB565);
        Assets.confirmButtonPressed = g.newImage("img/confirmButtonPressed.png", ImageFormat.RGB565);

        Assets.mastermindButton = g.newImage("img/mastermindButton.png", ImageFormat.RGB565);
        Assets.mastermindButtonPressed = g.newImage("img/mastermindButtonPressed.png", ImageFormat.RGB565);

        Assets.anagramButton = g.newImage("img/anagramButton.png", ImageFormat.RGB565);
        Assets.anagramPressedButton = g.newImage("img/anagramButtonPressed.png", ImageFormat.RGB565);

        File avatarFile = new File(Environment.getExternalStorageDirectory(), "Creature" + File.separator + "avatar.png");
        if(avatarFile.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bmp = BitmapFactory.decodeFile(avatarFile.getPath(), options);
            Assets.avatar = new AndroidImage(bmp, ImageFormat.ARGB8888);
        }


        //Load Letters
        String alphabet = new String("abcdefghijklmnopqrstuvwxyz");
        char[] letters = alphabet.toCharArray();
        Assets.alphabetImages = new Image[alphabet.length()];
        for(int i = 0; i < letters.length; i++)
            Assets.alphabetImages[i] = g.newImage("img/alphabet/" + letters[i] + ".png", ImageFormat.RGB565);

        Assets.letterBox = g.newImage("img/alphabet/box.png", ImageFormat.RGB565);


        //Data load stuff
        AndroidFileIO androidFileIO = new AndroidFileIO((AndroidGame) game);
        try {
            ArrayList<String> dictionaryArrayList = new ArrayList<String>();
            Scanner scanner = new Scanner(androidFileIO.readAsset("data/dictionary.txt"));
            while(scanner.hasNext())
            {
                dictionaryArrayList.add(scanner.nextLine());
            }
            scanner.close();
            Assets.dictionary = new String[dictionaryArrayList.size()];
            dictionaryArrayList.toArray(Assets.dictionary);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sounds here




        //Assets.click = game.getAudio().createSound("explode.ogg");

        MainMenuScreen mainMenuScreen = new MainMenuScreen(game);
        mainMenuScreen.addButton(new AndroidButton("Play", Assets.startButton, Assets.startButtonPressed, new Point(250, 100)));
        mainMenuScreen.addButton(new AndroidButton("Quit", Assets.quitButton, Assets.quitButtonPressed, new Point(250, 600)));
        game.setScreen(mainMenuScreen);


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
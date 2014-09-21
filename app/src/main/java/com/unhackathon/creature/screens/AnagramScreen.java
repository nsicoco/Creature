package com.unhackathon.creature.screens;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidGame;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.UserStats;
import com.unhackathon.creature.minigames.Anagram;

public class AnagramScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    // You would create game objects here.

    private String gameMode, word;
    private Anagram anagram;
    private char[] anagramLetters;
    private Button[] letterBoxes;
    private char[] enteredLetters;
    Paint paint;
    private Button letterBeingDragged;

    public AnagramScreen(Game game) {
        super(game);

        // Initialize game objects here

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(85);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        gameMode = "Anagram";

        if(gameMode.equals("Anagram"))
        {
            word = Assets.dictionary[(int) (Math.random() * Assets.dictionary.length)];
            anagram = new Anagram(word);
            anagramLetters = anagram.getLetters();
            Button[] letterButtons = new Button[anagramLetters.length];
            letterBoxes = new Button[anagramLetters.length];
            enteredLetters = new char[anagramLetters.length];
            int startX = 75;
            int startY = 100;
            for(int i = 0; i < anagramLetters.length; i++) {
                Image letterImage = Assets.alphabetImages[(anagramLetters[i] - 65)];
                letterButtons[i] = new AndroidButton(""+anagramLetters[i], letterImage, letterImage,
                        new Point(startX + (i*100), startY));
                addButton(letterButtons[i]);

                letterBoxes[i] = new AndroidButton("", Assets.letterBox, Assets.letterBox, new Point(startX + (i*100), startY+300));
            }
        }

        //g.incrementTextSize(0.9f);

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void checkEnteredLetters()
    {

    }


    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        //This is identical to the update() method from our Unit 2/3 game.


        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (TouchEvent event : touchEvents) {
            //TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {
                if(letterBeingDragged == null) {
                    for(Button letter : getButtons()) {
                        Rect bounds = letter.getBounds();
                        if(bounds.contains(event.x, event.y)) {
                            letterBeingDragged = letter;
                            for(int i = 0; i < letterBoxes.length; i++) {
                                Rect boxBounds = letterBoxes[i].getBounds();
                                if(boxBounds.left == bounds.left && boxBounds.top == bounds.top) {
                                    enteredLetters[i] = '\u0000';
                                }
                            }
                        }
                    }
                }
            }

            if(event.type == TouchEvent.TOUCH_DRAGGED) {
                if(letterBeingDragged != null) {
                    Rect bounds = letterBeingDragged.getBounds();
                    letterBeingDragged.setLocation(new Point(event.x - (bounds.width()/2), event.y - (bounds.height()/2)));
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if(letterBeingDragged != null) {
                    for(int i = 0; i < letterBoxes.length; i++) {
                        Rect bounds = letterBoxes[i].getBounds();
                        if(bounds.contains(event.x, event.y) ) {//  && enteredLetters[i] == '\u0000') {

                            if(enteredLetters[i] != '\u0000')
                            {
                                letterBeingDragged.setLocation(new Point(letterBeingDragged.getBounds().left, letterBeingDragged.getBounds().top + 250));
                                return;
                            }

                            letterBeingDragged.setLocation(new Point(bounds.left, bounds.top));

                            enteredLetters[i] = letterBeingDragged.getName().charAt(0);
                            if(anagram.differenceBetweenWords(enteredLetters) == 0) {
                                //TODO finish game

                                UserStats stats = new UserStats((AndroidGame) game);
                                stats.addExperience(40);

                                game.setScreen(new GameMenuScreen(game));
                            }
                        }

                    }
                }
                letterBeingDragged = null;
            }


        }


        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // First draw the game elements.

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(255, 255, 255, 255);


        //g.drawString("Yeah dude.",
          //      500, 300, paint);

        //Start index
            int startX = 75, startY = 100;
            for(Button box : letterBoxes) {
                Rect bounds = box.getBounds();
                g.drawImage(box.getImage(), bounds.left, bounds.top);
            }
            for(int i = 0; i < anagramLetters.length; i++) {
                g.drawImage(Assets.alphabetImages[(anagramLetters[i] - 65)], startX + (i * 100), startY);
            }



    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(255, 255, 255, 255);


        //g.drawString("Yeah dude.",
        //      500, 300, paint);

        //Start index
            int startX = 75, startY = 100;
            for(Button box : letterBoxes) {
                Rect bounds = box.getBounds();
                g.drawImage(box.getImage(), bounds.left, bounds.top);
            }
            for(Button button : getButtons()) {
                Rect bounds = button.getBounds();
                g.drawImage(button.getImage(), bounds.left, bounds.top);
            }
//            for(int i = 0; i < anagramLetters.length; i++)
//                g.drawImage(Assets.alphabetImages[(anagramLetters[i] - 65)], startX + (i * 100), startY);


    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        //g.drawARGB(255, 255, 255, 255);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    @Override
    public void pause() {
        //if (state == GameState.Running)
            //state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        //pause();
        game.setScreen(new GameMenuScreen(game));
    }
}
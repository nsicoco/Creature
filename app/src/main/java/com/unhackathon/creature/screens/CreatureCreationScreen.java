package com.unhackathon.creature.screens;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidImage;
import com.unhackathon.creature.Camera;

import java.util.List;

/**
 * Created by Noel on 9/20/2014.
 */
public class CreatureCreationScreen extends Screen {
  //  private GameButton cameraButton;

    public CreatureCreationScreen(Game game) {
        super(game);
    //    cameraButton = new GameButton(10, 10, 100, 100);
    //    cameraButton.setOnTouchListener(new GameButton.OnTouchListener() {
    //        public boolean onTouch() {
    //            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //            return true;
    //        }
    //    });
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        for(Input.TouchEvent e : touchEvents) {
            if(e.type == Input.TouchEvent.TOUCH_UP) {

            } else if(e.type == Input.TouchEvent.TOUCH_DOWN) {

            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        final int buffer = 10;

        int yOffset = buffer;

        //TODO: Scale image

        final int screenW = g.getWidth();
        final int screenH = g.getHeight();

        if(Camera.getCapturedImage() != null) {
            AndroidImage cameraImage = Camera.getCapturedAndroidImage();
            int avatarW = cameraImage.getWidth();
            int avatarH = cameraImage.getHeight();

            g.drawImage(cameraImage, (screenW - avatarW) / 2, (screenH - avatarH) / 2);

            yOffset += avatarH + buffer;
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

    }
}
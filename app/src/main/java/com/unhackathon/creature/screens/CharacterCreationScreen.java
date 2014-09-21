package com.unhackathon.creature.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidButton;
import com.kilobolt.framework.implementation.AndroidFileIO;
import com.kilobolt.framework.implementation.AndroidGame;
import com.kilobolt.framework.implementation.AndroidImage;
import com.unhackathon.creature.Assets;
import com.unhackathon.creature.Camera;
import com.unhackathon.creature.PreferenceKeys;
import com.unhackathon.creature.UserStats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Noel on 9/20/2014.
 */
public class CharacterCreationScreen extends Screen {
    private AndroidButton cameraButton;
    private AndroidButton confirmButton;

    public CharacterCreationScreen(Game game) {
        super(game);
        int screenWidth = game.getGraphics().getWidth();
        int centeredX = (screenWidth - Assets.takePictureButton.getWidth()) / 2;
        cameraButton = new AndroidButton("camera", Assets.takePictureButton, Assets.takePictureButtonPressed, new Point(centeredX, 0));
        addButton(cameraButton);

        centeredX = (screenWidth - Assets.confirmButton.getWidth()) / 2;
        confirmButton = new AndroidButton("confirm", Assets.confirmButton, Assets.confirmButtonPressed, new Point(centeredX, 0));
        addButton(confirmButton);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        for(Input.TouchEvent event : touchEvents) {
            for(Button button : getButtons()) {
                Rect bounds = button.getBounds();
                //Log.d("Creature", event.x + ", " + event.y + " :: " + bounds.left + ", " + bounds.right);
                if(inBounds(event, bounds.left, bounds.top, bounds.width(), bounds.height())) {
                    if(event.type == Input.TouchEvent.TOUCH_DOWN || event.type == Input.TouchEvent.TOUCH_HOLD
                            || event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                        button.setPressed(true);
                    }
                    else if(event.type == Input.TouchEvent.TOUCH_UP)
                    {
                        button.setPressed(false);
                        AndroidGame ag = (AndroidGame) game;
                        if(button == cameraButton) {
                            //Toast.makeText(ag, "WTFFFFFFFFFFFFFF", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ag.startActivityForResult(intent, Camera.TAKE_PICTURE_REQUEST_CODE);

                        } else if(button == confirmButton) {
                            if(Camera.getCapturedImage() != null) {
                                Assets.avatar = Camera.getCapturedAndroidImage();

                                File folder = new File(Environment.getExternalStorageDirectory(), "Creature");
                                folder.mkdirs();
                                File file = new File(folder, "avatar.png");
                                file.delete();
                                AndroidFileIO io = new AndroidFileIO(ag);
                                OutputStream out = null;
                                try {
                                    file.createNewFile();
                                    out = io.writeFile("creature" + File.separator + "avatar.png");
                                    Camera.getCapturedImage().compress(Bitmap.CompressFormat.PNG, 90, out);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if(out != null) {
                                            out.close();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                SharedPreferences pref = io.getSharedPref();
                                pref.edit().putBoolean(PreferenceKeys.CHARACTER_CREATED, true).apply();

                                UserStats stats = new UserStats(ag);
                                stats.setExperience(0);
                                stats.setLevel(0);

                                game.setScreen(new GameMenuScreen(game));
                            }
                        }
                    }
                }
                else
                {
                    button.setPressed(false);
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_UP) {

            } else if(event.type == Input.TouchEvent.TOUCH_DOWN) {

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

        //g.drawImage(Assets.menuBackground, 0, 0);
        g.drawRect(0, 0, g.getWidth(), g.getHeight(), Color.rgb(242, 242, 242));

        final int buffer = 50;

        int yOffset = buffer + 200;

        //TODO: Scale image

        final int screenW = g.getWidth();
        final int screenH = g.getHeight();

        AndroidImage cameraImage = Camera.getCapturedAndroidImage();
        if(cameraImage != null) {
            cameraImage = Camera.getCapturedAndroidImage();
            int avatarW = cameraImage.getWidth();
            int avatarH = cameraImage.getHeight();

            g.drawImage(cameraImage, (screenW - avatarW) / 2, yOffset);

            yOffset += avatarH + buffer;
        }
        Rect cameraBounds = cameraButton.getBounds();
        cameraButton.setLocation(new Point(cameraBounds.left, yOffset));
        g.drawImage(cameraButton.getImage(), cameraBounds.left, yOffset);

//        yOffset += 10*buffer + cameraBounds.height();
        yOffset = cameraBounds.bottom + buffer;

        Rect confirmBounds = confirmButton.getBounds();
        confirmButton.setLocation((new Point(confirmBounds.left, yOffset)));
        g.drawImage(confirmButton.getImage(), confirmBounds.left, yOffset);
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
package com.kilobolt.framework.implementation;

import android.graphics.Point;
import android.graphics.Rect;

import com.kilobolt.framework.Button;
import com.kilobolt.framework.Image;

/**
 * Created by francis on 9/20/14.
 */
public class AndroidButton implements Button {
    Image depressed, pressed;
    boolean isPressed;
    Point location;
    String name;

    public AndroidButton(String name, Image depressed, Image pressed, Point location)
    {
        this.depressed = depressed;
        this.pressed = pressed;
        isPressed = false;
        this.location = location;
        this.name = name;
    }
    public AndroidButton(String name, Image depressed, Image pressed)
    {
        this(name, depressed, pressed, new Point(0, 0));
    }
    @Override
    public Image getImage() {
       if(isPressed)
           return pressed;
        else
           return depressed;
    }

    @Override
    public void setPressed(boolean state) {
        isPressed = state;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    public Rect getBounds()
    {
        return new Rect(location.x, location.y, location.x+getImage().getWidth(), location.y+getImage().getHeight());
    }

    @Override
    public String getName() {
        return name;
    }
}

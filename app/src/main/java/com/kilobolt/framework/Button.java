package com.kilobolt.framework;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by francis on 9/20/14.
 */
public interface Button
{
    public Image getImage();
    public void setPressed(boolean state);
    public void setLocation(Point point);
    public Rect getBounds();
}

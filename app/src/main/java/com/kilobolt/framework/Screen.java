package com.kilobolt.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen {
    protected final Game game;
    protected List<Button> buttons;


    public List<Button> getButtons() { return buttons; }

    public void addButton(Button button) { buttons.add(button); }

    public Screen(Game game) {
        this.game = game;
        buttons = new ArrayList<Button>();
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
    
	public abstract void backButton();
}

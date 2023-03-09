package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public abstract class BaseObject {

    public Vector2 position;
    public int width;
    public int height;

    public BaseObject() {
        position = new Vector2();
    }
}

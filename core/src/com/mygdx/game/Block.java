package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Block extends BaseObject implements Collidable {

    private boolean destroyed;
    private Rectangle boundingRectangle;

    public Block(float positionX, float positionY, int width, int height) {
        position.set(positionX, positionY);
        this.width = width;
        this.height = height;
        boundingRectangle = new Rectangle(position.x, position.y, width, height);
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(position.x, position.y, width, height);
    }

    @Override
    public Rectangle getBoundingBox() {
        return boundingRectangle;
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}

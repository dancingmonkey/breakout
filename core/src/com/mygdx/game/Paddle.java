package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle extends BaseObject implements Collidable {

    private Rectangle boundingRectangle;

    public Paddle(float positionX, float positionY, int width, int height) {
        position.set(positionX, positionY);
        this.width = width;
        this.height = height;
        boundingRectangle = new Rectangle(position.x, position.y, width, height);
    }

    public void update() {
        updatePosition();
    }

    private void updatePosition() {
        position.x = Gdx.input.getX() - width / 2;
        boundingRectangle.setPosition(position.x, position.y);
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
    }
}

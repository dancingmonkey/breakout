package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends BaseObject {

    private int diameter;
    private int velocityX;
    private int velocityY;
    private Color color = Color.WHITE;
    private Circle boundingCircle;

    public Ball(float positionX, float positionY, int diameter, int velocityX, int velocityY) {
        position.set(positionX, positionY);
        this.diameter = diameter;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        boundingCircle = new Circle(position.x, position.y, diameter / 2);
    }

    public void update() {
        updatePosition();
        checkIfWallsAreHit();
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(position.x, position.y, diameter / 2);
    }

    private void updatePosition() {
        position.x += velocityX;
        position.y += velocityY;
        boundingCircle.setPosition(position.x, position.y);
    }

    private void checkIfWallsAreHit() {
        if (hitsLeftOrRightWall()) {
            reverseVelocityX();
        }

        if (hitsTopOrBottomWall()) {
            reverseVelocityY();
        }
    }

    private boolean hitsLeftOrRightWall() {
        if (position.x - diameter / 2 < 0 || position.x + diameter / 2 > Gdx.graphics.getWidth()) {
            return true;
        }
        return false;
    }

    private boolean hitsTopOrBottomWall() {
        if (position.y - diameter / 2 < 0 || position.y + diameter / 2 > Gdx.graphics.getHeight()) {
            return true;
        }
        return false;
    }

    private void reverseVelocityX() {
        velocityX = -velocityX;
    }

    private void reverseVelocityY() {
        velocityY = -velocityY;
    }

    public void checkCollision(Collidable collidable) {
        Rectangle boundingBox = collidable.getBoundingBox();
        if (Intersector.overlaps(boundingCircle, boundingBox)) {
            velocityY = -velocityY;
            if (collidable instanceof Block) {
                (collidable).destroy();
            }
        }
    }
}

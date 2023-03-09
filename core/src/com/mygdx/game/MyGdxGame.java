package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    private ShapeRenderer shape;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Block> blocks = new ArrayList<>();
    private Sound hitSound;

    @Override
    public void create() {

        hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));

        shape = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth() / 2, 50, 20, 5, 7);
        paddle = new Paddle(Gdx.graphics.getWidth() / 2, 20, 100, 10);

        createBlocks(60, 20);
    }

    private void createBlocks(int blockWidth, int blockHeight) {
        for (int positionY = Gdx.graphics.getHeight() / 2; positionY < Gdx.graphics.getHeight(); positionY += blockHeight + 10) {
            for (int positionX = 0; positionX < Gdx.graphics.getWidth(); positionX += blockWidth + 10) {
                blocks.add(new Block(positionX, positionY, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void render() {
        clearScreen();
        updateEntities();
        checkCollisions();
        removeDestroyedBlocks();
        drawShapes();
    }

    private void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void updateEntities() {
        ball.update();
        paddle.update();
    }

    private void checkCollisions() {
        ball.checkCollision(paddle);
        for (Block block : blocks) {
            ball.checkCollision(block);
        }
    }

    private void drawShapes() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        for (Block block : blocks) {
            block.draw(shape);
        }
        shape.end();
    }

    private void removeDestroyedBlocks() {
        for (int block = 0; block < blocks.size(); block++) {
            Block b = blocks.get(block);
            if (b.isDestroyed()) {
                blocks.remove(b);
                hitSound.play(0.2F);
                // we need to decrement i when a ball gets removed, otherwise we skip a ball!
                block--;
            }
        }
    }

    @Override
    public void dispose() {
        shape.dispose();
        hitSound.dispose();
    }
}

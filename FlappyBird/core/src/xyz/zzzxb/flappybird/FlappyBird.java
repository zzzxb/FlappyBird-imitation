package xyz.zzzxb.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.zzzxb.flappybird.game.Assets;
import xyz.zzzxb.flappybird.game.GameController;
import xyz.zzzxb.flappybird.game.GameWorld;

public class FlappyBird implements ApplicationListener {

    private GameController gameController;
    private GameWorld gameWorld;

    @Override
    public void create() {
        Assets.instants.init();
        gameController = new GameController();
        gameWorld = new GameWorld(gameController);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        gameController.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameWorld.render();
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
}

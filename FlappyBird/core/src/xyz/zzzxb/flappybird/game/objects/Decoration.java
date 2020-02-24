package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import xyz.zzzxb.flappybird.game.Assets;

public class Decoration extends AbstractGameObjects{

    public TextureRegion over;
    public TextureRegion ready;
    public TextureRegion title;
    public TextureRegion tutorial;
    public GAME_STATUS gameStatus;

    public Decoration() {
        over = Assets.instants.assetsDecoration.over;
        ready = Assets.instants.assetsDecoration.ready;
        title = Assets.instants.assetsDecoration.title;
        tutorial = Assets.instants.assetsDecoration.tutorial;
    }

    public enum GAME_STATUS {
        READY, OVER, NONE
    }

    public void init() {
        gameStatus = GAME_STATUS.READY;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (gameStatus.equals(GAME_STATUS.READY)) {
            batch.draw(title,
                    Gdx.graphics.getWidth() / 2 - title.getRegionWidth() / 2,
                    Gdx.graphics.getHeight() - title.getRegionHeight() * 2);
            batch.draw(tutorial,
                    Gdx.graphics.getWidth() / 2 - tutorial.getRegionWidth() / 2,
                    Gdx.graphics.getHeight() / 2);
            batch.draw(ready,
                    Gdx.graphics.getWidth() / 2 - ready.getRegionWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - ready.getRegionHeight() - 20);
        } else if (gameStatus.equals(GAME_STATUS.OVER)){
            batch.draw(over,
                    Gdx.graphics.getWidth() / 2 - over.getRegionWidth() / 2,
                    Gdx.graphics.getHeight() / 2 + over.getRegionHeight() * 2);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}

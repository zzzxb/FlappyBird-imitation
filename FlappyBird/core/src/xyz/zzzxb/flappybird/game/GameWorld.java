package xyz.zzzxb.flappybird.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import xyz.zzzxb.flappybird.game.objects.Decoration;
import xyz.zzzxb.flappybird.game.objects.GameBackground;

public class GameWorld implements Disposable {

    private SpriteBatch batch;
    private GameController gameController;

    public GameWorld(GameController gameController) {
        batch = new SpriteBatch();
        this.gameController = gameController;
        init();
    }

    public void init () {
        gameController.init();
    }

    public void render() {
        batch.begin();
        gameController.gameBackground.render(batch);
        gameController.tube.render(batch);
        gameController.land.render(batch);
        gameController.bird.render(batch);
        if (!gameController.decoration.gameStatus.equals(Decoration.GAME_STATUS.READY)) {
            gameController.numbers.render(batch);
        }
        gameController.decoration.render(batch);
        batch.end();
    }

    public void update(float deltaTime) {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

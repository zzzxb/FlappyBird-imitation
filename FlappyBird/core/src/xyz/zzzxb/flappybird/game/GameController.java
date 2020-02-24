package xyz.zzzxb.flappybird.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import xyz.zzzxb.flappybird.game.objects.Bird;
import xyz.zzzxb.flappybird.game.objects.Decoration;
import xyz.zzzxb.flappybird.game.objects.GameBackground;
import xyz.zzzxb.flappybird.game.objects.Land;
import xyz.zzzxb.flappybird.game.objects.Numbers;
import xyz.zzzxb.flappybird.game.objects.Tube;

public class GameController {
    public GameBackground gameBackground;
    public Land land;
    public Tube tube;
    public Bird bird;
    public Numbers numbers;
    public Decoration decoration;

    public boolean start = false;
    private float jumpTimer = 2f;
    private JUMP_STATUS jump_status;

    public GameController() {
        gameBackground = new GameBackground();
        land = new Land();
        tube = new Tube();
        bird = new Bird();
        numbers = new Numbers();
        decoration = new Decoration();
    }

    public enum JUMP_STATUS {
        JUMP, DOWN, NONE
    }

    public void init() {
        gameBackground.init();
        start = false;
        tube.init();
        land.init();
        bird.init();
        decoration.init();
        numbers.init();
        jump_status = JUMP_STATUS.NONE;
    }

    public void update (float deltaTime) {
        if (start) {
            gameBackground.update(deltaTime);
            tube.update(deltaTime);
            land.update(deltaTime);
            bird.update(deltaTime);
            numbers.update(deltaTime);
            decoration.gameStatus = Decoration.GAME_STATUS.NONE;
        }

        if (!jump_status.equals(JUMP_STATUS.JUMP) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                start = true;
                jump_status = JUMP_STATUS.JUMP;
                Assets.instants.assetsAudio.fly.stop();
                Assets.instants.assetsAudio.fly.play();
        }

        if (jump_status.equals(JUMP_STATUS.JUMP)) {
            bird.jump(deltaTime);
            jumpTimer -= 0.1;
            if (jumpTimer <= 0) {
                jump_status = JUMP_STATUS.DOWN;
                jumpTimer = 2f;
            }
        }else if (jump_status.equals(JUMP_STATUS.DOWN)){
            bird.down(deltaTime);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.R))
            init();

        if (start) {
            for (Rectangle tubeRectangle : tube.rectangles) {
                // 与管道发生碰撞
                if (tubeRectangle.overlaps(bird.rectangle)) {
                    start = false;
                    decoration.gameStatus = Decoration.GAME_STATUS.OVER;
                }
                // 飞过管道 金币加1
                if (tubeRectangle.x + tubeRectangle.getWidth() == bird.position.x) {
                    numbers.score ++;
                    Assets.instants.assetsAudio.coin.play();
                }
            }
            // 与陆地发生碰撞
            if (land.rectangle.overlaps(bird.rectangle)) {
                start = false;
                decoration.gameStatus = Decoration.GAME_STATUS.OVER;
            }

            if (!start) {
                Assets.instants.assetsAudio.collision.stop();
                Assets.instants.assetsAudio.collision.play();
            }
        }
    }
}

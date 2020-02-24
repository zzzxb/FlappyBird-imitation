package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

import xyz.zzzxb.flappybird.game.Assets;

public class Numbers extends AbstractGameObjects{

    public TextureRegion[] numbers;
    public int score;
    public float sunWidth;
    public char[] charScore;

    public Numbers() {
        numbers = Assets.instants.assetsNumber.numbers;
    }

    public void init() {
        score = 0;
        charScore = new char[]{48};
        sunWidth = numbers[0].getRegionWidth();
        position.x = Gdx.graphics.getWidth() / 2 - sunWidth / 2;
        position.y = Gdx.graphics.getHeight() - numbers[0].getRegionHeight() - 8;
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < charScore.length; i++) {
            batch.draw(numbers[Integer.parseInt(Character.toString(charScore[i]))],
                    (Gdx.graphics.getWidth() / 2) - sunWidth / 2
                    + numbers[0].getRegionWidth() * i, position.y);
        }
    }

    @Override
    public void update(float deltaTime) {
        // 计算 score 有多少位
        String strScore = String.valueOf(score/2);
        charScore = strScore.toCharArray();
        sunWidth = charScore.length * numbers[0].getRegionWidth();
    }
}

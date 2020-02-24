package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import xyz.zzzxb.flappybird.game.Assets;

/**
 * 这个类中有两个背景图， 一个白天一个黑夜，通过设定白天黑夜时间，进行这两中状态的切换。
 */
public class GameBackground extends AbstractGameObjects implements Disposable {

    public TextureRegion bgDay;
    public TextureRegion bgNight;
    public float timer = 24f; // 白天黑夜的时间
    public float count = 0f; // 计时器
    public float slidingSpeed = 0.3f; // 背景滚动速度
    public WEATHER_STATUS weather_status; // 天气状态

    public GameBackground() {
        bgDay = Assets.instants.assetsBackground.bg_day;
        bgNight = Assets.instants.assetsBackground.bg_night;
    }

    public enum WEATHER_STATUS{
        DAY, NIGHT
    }

    public void init() {
        weather_status = WEATHER_STATUS.DAY;
        position.x = 0;
        position.y = 0;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (weather_status.equals(WEATHER_STATUS.DAY)){
            batch.draw(bgDay, position.x , position.y);
            batch.draw(bgDay, position.x + bgDay.getRegionWidth() , position.y);
        }
        else {
            batch.draw(bgNight, position.x , position.y);
            batch.draw(bgNight, position.x + bgDay.getRegionWidth() , position.y);
        }
    }

    @Override
    public void update(float deltaTime) {
        position.x -= slidingSpeed;
        if (Math.abs(position.x) >= bgDay.getRegionWidth())
            position.x = 0;
        count += deltaTime;
        if (count >= timer) {
            count = 0f;
            weather_status = weather_status.equals(WEATHER_STATUS.DAY) ?
                    WEATHER_STATUS.NIGHT : WEATHER_STATUS.DAY;
        }
    }

    @Override
    public void dispose() {
    }
}

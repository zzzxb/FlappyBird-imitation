package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import xyz.zzzxb.flappybird.game.Assets;

public class Land extends AbstractGameObjects implements Disposable {

    public TextureRegion land;
    public Rectangle rectangle;
    public float slidingSpeed = 1f; // 背景滚动速度
    public Pixmap pixmap;
    public Texture texture;

    public Land() {
        land = Assets.instants.assetsLand.land;
        rectangle = new Rectangle();
        pixmap = new Pixmap(336, 112, Pixmap.Format.RGBA8888);
    }

    public void init() {
        position.x = 0;
        position.y = -40;
        rectangle.set(0,-40, 336, 100);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, 336, 112);
        texture = new Texture(pixmap);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(land, position.x, position.y);
        batch.draw(land, position.x + land.getRegionWidth(), position.y);
//        batch.draw(texture, 0, -40);
    }

    @Override
    public void update(float deltaTime) {
        position.x -= slidingSpeed;
        if (Math.abs(position.x) >= land.getRegionWidth())
            position.x = 0;
    }

    @Override
    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}

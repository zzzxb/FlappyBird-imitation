package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import xyz.zzzxb.flappybird.game.Assets;

public class Bird extends AbstractGameObjects implements Disposable{

    public TextureRegion[] bird;
    public float count = 0;
    public World world;
    public Vector2 worldStatus;
    public Rectangle rectangle;
    public Pixmap pixmap;
    public Texture texture;

    public Bird() {
        pixmap = new Pixmap(48, 48, Pixmap.Format.RGBA8888);
        worldStatus = new Vector2(0, 0);
        world = new World(worldStatus, true);
        bird = new TextureRegion[3];
        bird[0] = Assets.instants.assetsBird.bird[0];
        bird[1] = Assets.instants.assetsBird.bird[1];
        bird[2] = Assets.instants.assetsBird.bird[2];
        rectangle = new Rectangle();
    }

    public void init() {
        position.x = Gdx.graphics.getWidth() / 2 - bird[0].getRegionWidth() / 2;
        position.y = 260f;
        worldStatus.set(0, 0);
        rectangle.set(position.x+8, position.y+12,
                bird[0].getRegionWidth()-18, bird[1].getRegionHeight()-24);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(8, 12,30, 24);
        texture = new Texture(pixmap);
    }

    @Override
    public void render(SpriteBatch batch) {
        if ( count <= 0.3)
            batch.draw(bird[0], position.x, position.y);
        else if ( count <= 0.6)
            batch.draw(bird[1], position.x, position.y);
        else if ( count <= 0.9)
            batch.draw(bird[2], position.x, position.y);
//        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void update(float deltaTime) {
        worldStatus.add(world.getGravity().x, world.getGravity().y * deltaTime);
        position.add(worldStatus.x, worldStatus.y);
        count += 0.05;
        count = count >= 0.9f ? 0f : count;
        rectangle.setPosition(position.x, position.y);
    }

    /**
     * 跳跃
     * @param deltaTime
     */
    public void jump(float deltaTime) {
        worldStatus.y += 4 * deltaTime;
    }

    /**
     * 下落
     * @param deltaTime
     */
    public void down(float deltaTime) {
        worldStatus.y -= 2 * deltaTime;
    }

    @Override
    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}

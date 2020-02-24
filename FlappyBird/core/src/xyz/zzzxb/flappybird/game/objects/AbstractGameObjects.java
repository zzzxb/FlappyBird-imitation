package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractGameObjects {

    public Vector2 position;

    public AbstractGameObjects() {
        position = new Vector2();
    }

    public abstract void render(SpriteBatch batch);

    public abstract void update(float deltaTime);
}

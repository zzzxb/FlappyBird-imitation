package xyz.zzzxb.flappybird.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Random;

import xyz.zzzxb.flappybird.game.Assets;

public class Tube extends AbstractGameObjects implements Disposable {

    public AtlasRegion tubeUp;
    public AtlasRegion tubeDown;
    public Vector2[] vector2s;
    public Rectangle[] rectangles;
    public float slidingSpeed = 1f; // 背景滚动速度
    public Pixmap pixmap;
    public Texture texture;

    public Tube() {
        tubeUp = Assets.instants.assetsTube.tubeUP;
        tubeDown = Assets.instants.assetsTube.tubeDown;
        vector2s = new Vector2[3];
        rectangles = new Rectangle[6];
        pixmap = new Pixmap(tubeUp.getRegionWidth(), tubeUp.getRegionHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, tubeUp.getRegionWidth(), tubeUp.getRegionHeight());
        texture = new Texture(pixmap);
    }

    public void init() {
        vector2s[0] = new Vector2(Gdx.graphics.getWidth()*2, (float)new Random().nextInt(150) - 150);
        vector2s[1] = new Vector2(Gdx.graphics.getWidth()*2 + tubeUp.getRegionWidth()*3, (float)new Random().nextInt(150) - 150);
        vector2s[2] = new Vector2(Gdx.graphics.getWidth()*2 + tubeUp.getRegionWidth()*6, (float)new Random().nextInt(150) - 150);
        for (int i = 0; i < rectangles.length; i+=2) {
            rectangles[i] = new Rectangle();
            rectangles[i].setSize(tubeUp.getRegionWidth(), tubeUp.getRegionHeight());
            rectangles[i+1] = new Rectangle();
            rectangles[i+1].setSize(tubeDown.getRegionWidth(), tubeDown.getRegionHeight());
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < vector2s.length; i++) {
            batch.draw(tubeUp, vector2s[i].x, vector2s[i].y);
            batch.draw(tubeDown,vector2s[i].x, vector2s[i].y + tubeDown.getRegionHeight()+80);
            // 矩形边框
//            batch.draw(texture, vector2s[i].x, vector2s[i].y);
//            batch.draw(texture, vector2s[i].x, vector2s[i].y + tubeDown.getRegionHeight()+80);
        }
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0, j = 0; i < vector2s.length; i++, j+=2) {
            vector2s[i].x -= slidingSpeed;
            if (vector2s[i].x < - tubeUp.getRegionWidth()) {
                vector2s[i] = new Vector2(200 + tubeUp.getRegionWidth()* 4, (float)new Random().nextInt(150) - 150);
            }
            rectangles[j].setPosition(vector2s[i].x - 6, vector2s[i].y - 12);
            rectangles[j+1].setPosition(vector2s[i].x - 6, vector2s[i].y + tubeDown.getRegionHeight()+68);
        }

    }

    @Override
    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}

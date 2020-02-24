package xyz.zzzxb.flappybird.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import xyz.zzzxb.flappybird.game.objects.GameBackground;
import xyz.zzzxb.flappybird.util.Constants;

public class Assets implements Disposable {

    public static Assets instants = new Assets();
    private TextureAtlas atlas;

    public AssetsBackground assetsBackground;
    public AssetsLand assetsLand;
    public AssetsTube assetsTube;
    public AssetsBird assetsBird;
    public AssetsNumber assetsNumber;
    public AssetsAudio assetsAudio;
    public AssetsDecoration assetsDecoration;

    private Assets() {
    }

    public void init() {
        atlas = new TextureAtlas(Gdx.files.internal(Constants.GAME_ASSETS_PATH));

        assetsBackground = new AssetsBackground(atlas);
        assetsLand = new AssetsLand(atlas);
        assetsTube = new AssetsTube(atlas);
        assetsBird = new AssetsBird(atlas);
        assetsAudio = new AssetsAudio();
        assetsNumber = new AssetsNumber(atlas);
        assetsDecoration = new AssetsDecoration(atlas);
    }

    /**
     * 背景
     */
    public class AssetsBackground {
        public final AtlasRegion bg_day;
        public final AtlasRegion bg_night;

        public AssetsBackground(TextureAtlas atlas) {
            bg_day = atlas.findRegion("flappybird/bg_day");
            bg_night = atlas.findRegion("flappybird/bg_night");
        }
    }

    /**
     * 陆地
     */
    public class AssetsLand {
        public final AtlasRegion land;

        public AssetsLand(TextureAtlas atlas) {
            land = atlas.findRegion("flappybird/land");
        }
    }

    /**
     * 管道
     */
    public class AssetsTube {
        public final AtlasRegion tubeUP;
        public final AtlasRegion tubeDown;

        public AssetsTube(TextureAtlas atlas) {
            tubeDown = atlas.findRegion("flappybird/pipe_down");
            tubeUP = atlas.findRegion("flappybird/pipe_up");
        }
    }

    /**
     * 小鸟
     */
    public class AssetsBird {
        public AtlasRegion[] bird;

        public AssetsBird(TextureAtlas atlas) {
            bird = new AtlasRegion[3];
            bird[0] = atlas.findRegion("flappybird/bird_0");
            bird[1] = atlas.findRegion("flappybird/bird_1");
            bird[2] = atlas.findRegion("flappybird/bird_2");
        }
    }

    public class AssetsDecoration {
        public AtlasRegion tutorial;
        public AtlasRegion ready;
        public AtlasRegion over;
        public AtlasRegion title;


        public AssetsDecoration(TextureAtlas atlas) {
            tutorial = atlas.findRegion("flappybird/tutorial");
            ready = atlas.findRegion("flappybird/text_ready");
            over = atlas.findRegion("flappybird/text_game_over");
            title = atlas.findRegion("flappybird/title");
        }
    }

    public class AssetsNumber {
        public AtlasRegion[] numbers;

        public AssetsNumber(TextureAtlas atlas) {
            numbers = new AtlasRegion[10];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = atlas.findRegion("flappybird/number_score", i);
            }
        }
    }

    /**
     * 音效
     */
    public class AssetsAudio {
        public Sound fly;
        public Sound collision;
        public Sound coin;

        public AssetsAudio() {
            fly = Gdx.audio.newSound(Gdx.files.internal("audio/fly.mp3"));
            collision = Gdx.audio.newSound(Gdx.files.internal("audio/collision.mp3"));
            coin = Gdx.audio.newSound(Gdx.files.internal("audio/coin.mp3"));
        }
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}

package xyz.zzzxb.flappybird.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import xyz.zzzxb.flappybird.FlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flappy Bird";
		config.width = 288;
		config.height = 512;
//		config.resizable = false;
		new LwjglApplication(new FlappyBird(), config);
	}
}

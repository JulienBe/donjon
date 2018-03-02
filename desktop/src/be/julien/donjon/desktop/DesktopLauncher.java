package be.julien.donjon.desktop;

import be.julien.donjon.ui.EntryPoint;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		System.out.println("Startup");
		new LwjglApplication(new EntryPoint(), config);
	}
}

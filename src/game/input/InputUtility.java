package game.input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	private static ArrayList<KeyCode> keysPressed = new ArrayList<>();
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keysPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keysPressed.contains(keycode)){
				keysPressed.add(keycode);
			}
		}else{
			keysPressed.remove(keycode);
		}
	}

	public static ArrayList<KeyCode> getKeysPressed() {
		return keysPressed;
	}

	public static void setKeysPressed(ArrayList<KeyCode> keysPressed) {
		InputUtility.keysPressed = keysPressed;
	}

	
	
}


import java.awt.Color;
import java.util.Random;

public class RandomColorGenerator {

	public static Color getRandomColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		return randomColor;
	}

}

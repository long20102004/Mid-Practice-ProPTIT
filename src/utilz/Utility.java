package utilz;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Utility {
    public static final String backgroundImage1 = "background2.jpg";
    public static final String backgroundImage2 = "background.jpg";
    public static final String explodeAni = "explodes.png";
    public static final String stick = "stick.png";
    public static final String monsterImage = "monster.png";
    public static final String burnLeft = "burnleft.png";
    public static BufferedImage importImg(String Name){
        BufferedImage img = null;
        InputStream is = Utility.class.getResourceAsStream("/" + Name);
        try{
            img = ImageIO.read(is);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}

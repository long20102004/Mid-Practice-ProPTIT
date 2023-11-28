package utilz;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
    private static final ArrayList<String> arrayList = new ArrayList<String>();
    public static final String backgroundImage1 = "background.jpg";
    public static final String backgroundImage2 = "background2.jpg";
    public static final String backgroundImage3 = "background3.jpg";
    public static final String backgroundImage4 = "background4.jpg";
    public static final String backgroundImage5 = "background5.jpg";
    public static final String backgroundImage6 = "background6.jpg";
    public static final String backgroundImage7 = "background7.jpg";
    public static final String explodeAni = "explodes.png";
    public static final String stick = "stick.png";
    public static final String monsterImage = "monster.png";
    public static final String burnLeft = "burnleft.png";
    public static String getRandomBackGround(){
        arrayList.add(backgroundImage1);
        arrayList.add(backgroundImage2);
        arrayList.add(backgroundImage3);
        arrayList.add(backgroundImage4);
        arrayList.add(backgroundImage5);
        arrayList.add(backgroundImage6);
        arrayList.add(backgroundImage7);
        Random rnd = new Random();
        String Name = arrayList.get(rnd.nextInt(arrayList.size() - 1));
        return Name;
    }

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

package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
    private static final ArrayList<String> arrayList = new ArrayList<String>();
    public static final String backgroundImage1 = "Background/background.jpg";
    public static final String backgroundImage2 = "Background/background2.jpg";
    public static final String backgroundImage3 = "Background/background3.jpg";
    public static final String backgroundImage4 = "Background/background4.jpg";
    public static final String backgroundImage5 = "Background/background5.jpg";
    public static final String backgroundImage6 = "Background/background6.jpg";
    public static final String backgroundImage7 = "Background/background7.jpg";
    public static final String backgroundImage8 = "Background/background8.jpg";
    public static final String explodeAni = "Animation/explodes.png";
    public static final String battleship1 = "Ship/battleship (2).png";
    public static final String battleship2 = "Ship/battleshipsize2.png";
    public static final String battleship2Rotate = "Ship/battleshipsize2 - Rotate.png";
    public static final String battleship3 = "Ship/battleshipsize3.png";
    public static final String battleship3Rotate = "Ship/battleshipsize3 - Rotate.png";
    public static final String battleship4 = "Ship/battleshipsize4.png";
    public static final String battleship4Rotate = "Ship/battleshipsize4 - Rotate.png";
    public static final String stick = "Animation/stick.png";
    public static final String burnLeft = "Animation/burnleft.png";
    public static final String waitingBackground = "Background/Waiting.png";
    public static final String smokeAni = "Animation/smoke.png";
    public static final String broken = "Animation/broken.png";
    public static final String turn = "Animation/turn.png";
    public static final String victory = "Background/victory.jpg";
    public static final String defeated = "Background/defeated.jpg";
    public static final String gameRules = "Background/GameRules.png";
    public static String getRandomBackGround(){
        arrayList.add(backgroundImage1);
        arrayList.add(backgroundImage2);
        arrayList.add(backgroundImage3);
        arrayList.add(backgroundImage4);
        arrayList.add(backgroundImage5);
        arrayList.add(backgroundImage6);
        arrayList.add(backgroundImage7);
        arrayList.add(backgroundImage8);
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

package GameFrame;

import javax.swing.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/*
 * @author Guihution
 * 2020/5/29 11:47
 */
public class IconDictionaries {

    private IconDictionaries(){
        for (int i = 0; i < ICON_URL.length ; i++) {
            ICON_ARR.add(new ImageIcon(getClass().getResource(PRE_URL+ICON_URL[i])));
        }
//        ICON_ARR.forEach(System.out::println);
    }

    private static final String PRE_URL = "/image/";
    private static final String [] ICON_URL = {"bg.jpg","hero.png","bullet.png","enemy.png","bz.png","bomb.png","hero_destory.png","login.jpg"};
    private static final ArrayList<ImageIcon> ICON_ARR = new ArrayList<>();
    private static IconDictionaries IconDictionaries = new IconDictionaries();

    public ImageIcon getBg(){ return null != ICON_ARR.get(0) ? ICON_ARR.get(0) : null; }
    public ImageIcon getPlant(){ return null != ICON_ARR.get(1) ? ICON_ARR.get(1) : null; }
    public ImageIcon getBullet(){ return null != ICON_ARR.get(2) ? ICON_ARR.get(2) : null; }
    public ImageIcon getEnemy(){ return null != ICON_ARR.get(3) ? ICON_ARR.get(3) : null; }
    public ImageIcon getBz(){ return null != ICON_ARR.get(4) ? ICON_ARR.get(4) : null; }
    public ImageIcon getBom(){ return null != ICON_ARR.get(5) ? ICON_ARR.get(5) : null; }
    public ImageIcon getPD(){ return null != ICON_ARR.get(6) ? ICON_ARR.get(6) : null; }
    public ImageIcon getLogBd(){ return null != ICON_ARR.get(7) ? ICON_ARR.get(7) : null; }

    public static IconDictionaries getInstance(){
        return null != IconDictionaries ? IconDictionaries : new IconDictionaries();
    }
}

package GameFrame;

import javax.swing.*;

/*
 * @author Guihution
 * 2020/5/26 12:11
 */
public class Background {

    private int x,y,width,height;
    private ImageIcon backgroundImage = IconDictionaries.getInstance().getBg();

    public Background(int x, int y){
        this.x = x;
        this.y = y;
//        this.width = planImage.getIconWidth();
//        this.height = planImage.getIconHeight();
    }
    //. 背景移动
    public void move(){
        this.y ++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}

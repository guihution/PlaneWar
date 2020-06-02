package GameFrame;

import javax.swing.*;

/*
 * @author Guihution
 * 2020/5/26 12:11
 */
public class Bullet {

    private int x,y,width,height;
    private ImageIcon bulletImage = IconDictionaries.getInstance().getBullet();

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        this.width = bulletImage.getIconWidth();
        this.height = bulletImage.getIconHeight();
    }
    //. 让子弹飞
    public void move(){
        this.y -= 4;
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

    public ImageIcon getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(ImageIcon bulletImage) {
        this.bulletImage = bulletImage;
    }
}

package GameFrame;

import javax.swing.*;

/*
 * @author Guihution
 * 2020/5/26 12:11
 */
public class Enemy {

    private int x,y,width,height,islive=1,speed=2;
    private ImageIcon enemyImage = IconDictionaries.getInstance().getEnemy();

    public Enemy(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = enemyImage.getIconWidth();
        this.height = enemyImage.getIconHeight();
    }
    //. 让敌机来
    public void move(){
        if(this.islive == 1)this.y += this.speed;
    }

    public int getIslive() {
        return islive;
    }

    public void setIslive(int islive) {
        this.islive = islive;
    }

    // 被打中
    public void setEnemyImage(ImageIcon enemyImage) {
        this.enemyImage = enemyImage;
        this.width = enemyImage.getIconWidth();
        this.height = enemyImage.getIconHeight();
        this.islive = 0;
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

    public ImageIcon getEnemyImage() {
        return enemyImage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

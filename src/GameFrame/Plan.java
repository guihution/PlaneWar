package GameFrame;

import javax.swing.*;

/*
 * @author Guihution
 * 2020/5/26 12:11
 */
public class Plan {

    private int x,y,width,height,isLive = 1,overTime=0;
    private ImageIcon planImage = IconDictionaries.getInstance().getPlant();

    public Plan(int x, int y){
        this.x = x;
        this.y = y;
        this.width = planImage.getIconWidth();
        this.height = planImage.getIconHeight();
    }

    public void reCome (){
        setPlanImage(IconDictionaries.getInstance().getPlant());
        this.x = 200;
        this.y = 520;
        this.width = planImage.getIconWidth();
        this.height = planImage.getIconHeight();
        this.isLive = 1;
        this.overTime = 0;
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

    public ImageIcon getPlanImage() {
        return planImage;
    }

    public void setPlanImage(ImageIcon planImage) {
        this.planImage = planImage;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    public int getIsLive() {
        return isLive;
    }

    public void setIsLive(int isLive) {
        this.isLive = isLive;
    }
}

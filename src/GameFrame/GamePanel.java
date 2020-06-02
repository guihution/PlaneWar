package GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.function.Function;

/*
 * @author Guihution
 * 2020/5/26 12:10
 */
@SuppressWarnings("warning")
public class GamePanel extends JPanel implements MouseMotionListener {

    // 飞机
    private Plan plan = new Plan(200,520);
    // 子弹
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> bulletYs = new ArrayList<Bullet>();
    // 敌机
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    // 背景
    private ArrayList<Background> backgrounds = new ArrayList<Background>();
    // 提示
    private ArrayList<TipItem> tips = new ArrayList<TipItem>();
    // 分数
    private long achievement = 0;
    // 子弹添加时间系数
    private long count = 1;
    // 游戏结束
    boolean gameOver = false;
    // 提示
    private String tip = "";
    // 敌机速度
    private int speed = 2;
    private int enemy_leval = 1;
    private int max_enemy_leval = 7;// 最高等级
    // 武器等级
    private int aream_leval = 1;
    // 升级标准
//    private int[] up_pip = {10,20,30,40,50,60,70,80,90,100};
    private int[] up_pip = {100,200,500,1000,1500,2000,3000,4000,5000,10000};
    private int max_aream_leval = 10; // 最高等级
    // 提示读秒
    private int tiptemp = 1;

    private void aream_leval_up(){ // 武器升级
        tip = Tip.ARMS_LEVEL_UP.getTip();
        aream_leval++ ;
        tips.add(new TipItem("--武器升级--"));
    }

    private void enemy_speed_up(){ // 敌机速度升级
        tip = Tip.ENEMY_SPEED_UP.getTip();
        speed ++ ;
        tips.add(new TipItem("--敌机速度加快--"));
    }

    private class mThread extends Thread {
        private long sleep = 0;
        private Object args = null;
        private Function<Object,Object> func;

        public mThread(Function<Object,Object> f,Object args,long s){
            this.func = f;
            this.sleep = s;
            this.args = args;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleep);
                if(func!=null)func.apply(args);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    // 初始化
    public void init() {
        // 添加背景
        backgrounds.add(new Background(0,0));
        System.out.println("--游戏初始化--");

        while (!gameOver){
            count ++ ;//2147483647
            // 添加另外一张背景图
            if(backgrounds.size()==1) backgrounds.add(new Background(0,-700));
            // 控制背景图片
            for (int i = 0;i < backgrounds.size();i++) {
                Background bg = backgrounds.get(i);
                if(bg != null){
                    if(bg.getY()>690) backgrounds.remove(bg);
                    bg.move();
                }
            }

            // 子弹间隔
            if(count % Math.max (15 - aream_leval,5) == 0) {
                Bullet b = new Bullet(plan.getX() + 45,plan.getY() - 15);
                bullets.add(b);
                bulletYs.add(b);
            }

            // 控制子弹个数
            for (int i = 0;i < bullets.size();i++) {
                Bullet b = bullets.get(i);
                if(b != null) {
                    if (b.getY() < - 15) bullets.remove(b);
                    b.move();
                }
            }

            // 敌机间隔
            for (int i = 0; i < Math.max(enemy_leval/2,1); i++) {
                if(count % Math.max (50 - enemy_leval,15) == 0) enemies.add(new Enemy((int)(Math.random()*430),0 , speed));
            }
            // 敌机间隔
            for (int i = 0;i < enemies.size();i++) {
                Enemy e = enemies.get(i);
                if(e != null){
                    if(e.getY()>700) enemies.remove(e);
                    haveBullet(e);
                    if(plan.getIsLive() ==  1 && e.getIslive() == 1 && isHit(plan,e)){ // 飞机是否坠毁
                        gameOver();
                        gameOver = false;// 游戏结束
                    };
                    e.move();
                }
            }


            // 武器升级
            if(aream_leval < max_aream_leval && achievement > up_pip[aream_leval]) {
                ++tiptemp;
                System.out.println("当前武器等级："+aream_leval);
                aream_leval_up();
                System.out.println("武器升级，当前武器等级："+aream_leval);
            }
            // 敌机升级
            if(enemy_leval < max_enemy_leval && count % (1000 + enemy_leval * 1000)  == 0) {
                System.out.println("当前敌机速度等级："+enemy_leval+"；数量"+enemy_leval/2);
                ++enemy_leval;
                if(enemy_leval%2!=0) {
                    System.out.println("敌机速度升级："+speed);
                    enemy_speed_up();
                    System.out.println("当前敌机速度："+speed);
                } else {
                    tips.add(new TipItem("--敌机数量变多--"));
                }
            }

            if(tiptemp > 0 ) { // 提示读秒 && ++tiptemp >100
                for (int i = 0; i < tips.size(); i++) {
                    TipItem t = tips.get(i);
                    if(null!=t){
                        if(t.getTime() > 200) {
                            tips.remove(t);
                        }else {
                            t.setTime(t.getTime()+1);
                        }
                    }
                }
            }

            repaint();
            try {
                Thread.sleep(10);//10ms 基数
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void  reCome(){
        plan.reCome();// 重置飞机状态
        bullets.removeAll(bullets);
        bulletYs.removeAll(bulletYs);
        enemies.removeAll(enemies);
        tips.removeAll(tips);
        achievement  = 0;
        aream_leval = enemy_leval = 1;
        count = 1;
        speed = 2;
        repaint();
        gameOver = false;
    }
    private void exit(){
        new mThread(v -> {
            gameOver = true;
            System.exit(0);
            return v;
        }, null,500).start();
    }
    private void gameOver(){
        System.out.println("---GameOver--游戏结束！---");
        int time = plan.getOverTime();
        plan.setIsLive(0);
        if(plan.getIsLive()==0) plan.setPlanImage(IconDictionaries.getInstance().getPD());
        Object [] obj = {"重来","退出"};
        int n = JOptionPane.showOptionDialog(null, "飞机坠毁！您的得分是："+achievement, "提示",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("warning"),obj,obj[0]);
        if(n==0){
           reCome();
        } else {
            exit();
        }
    }

    private boolean isHit(Plan p,Enemy e){ // 飞机坠毁
        Rectangle pwRect = new Rectangle(p.getX(),p.getY()+65,p.getWidth(),p.getHeight()-114);// 飞机底部 124-114 底部高度 10
        Rectangle phRect = new Rectangle(p.getX()+42,p.getY(),p.getWidth()-85,p.getHeight()-32); // 飞机中心
        Rectangle ewRect = new Rectangle(e.getX(),e.getY()-10,e.getWidth(),e.getHeight()-20); // 敌机尾翼 宽度 49 高度 36-20 16
        Rectangle ehRect = new Rectangle(e.getX()+10,e.getY(),e.getWidth()-20,e.getHeight()); // 敌机中心
        return phRect.intersects(ewRect) || phRect.intersects(ehRect) || pwRect.intersects(ewRect) || pwRect.intersects(ehRect);// 是否碰撞
    }

    private boolean isHit(Enemy e,Bullet b){
        Rectangle eRect = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());
        Rectangle bRect = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());
        return eRect.intersects(bRect);
    }

    //  是否有子弹
    private boolean haveBullet(Enemy e){
        int elength = enemies.size();
        for (int j = 0;j < bullets.size();j++) {
            Bullet b = bullets.get(j);
            if(b!=null && isHit(e,b)){
                hit(e);
                bullets.remove(b);
            }
        }
        return false;
    }

    //  是否打中敌机
    private boolean isHit(Bullet b,Enemy e,Integer y){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(e.getY()+b.getY()>y) {
                    hit(e);
                    bullets.remove(b);
                }
            }
        }).start();
        return false;
    }

    private void hit(Enemy e){
        e.setEnemyImage(IconDictionaries.getInstance().getBom());
        new mThread(o -> {
            if(e != null) {
                enemies.remove(e) ;
                achievement += 10;
            }
            return o;
        },null,500).start();
    }

    // 展示飞机
    @Override
    public void paint(Graphics g) {
        // 让父类画完
        super.paint(g);
        // 背景
        for (int i = 0;i < backgrounds.size();i++) {
            Background bg = backgrounds.get(i);
            g.drawImage(bg.getBackgroundImage().getImage(),bg.getX(),bg.getY(),null);
        }
        // 飞机
        if (plan!=null){
            Image image = plan.getPlanImage().getImage();
            g.drawImage(image,plan.getX(),plan.getY(),null);
        }
        // 子弹
        for (int i = 0;i < bullets.size();i++) {
            Bullet b = bullets.get(i);
            if(b!=null) g.drawImage(b.getBulletImage().getImage(),b.getX(),b.getY(),null);
        }
        // 敌机
        for (int i = 0;i < enemies.size();i++) {
            Enemy e = enemies.get(i);
            if(e!=null) g.drawImage(e.getEnemyImage().getImage(),e.getX(),e.getY(),null);
        }
        // 暂时通知
        for (int i = 0; i < tips.size(); i++) {
            TipItem t = tips.get(i);
            if(null!=t) g.drawString(t.getContent(),380,Math.max((tips.size()-i)*20+60,60));
        }

        // 武器下次升级提示消息
        if(aream_leval<max_aream_leval) g.drawString("下次升级需要达到 "+up_pip[aream_leval]+" 积分",10,40);

        // 武器等级
        g.drawString("武器等级："+aream_leval+(aream_leval<max_aream_leval?"":"(Max)"),380,20);
        // 敌机等级
        g.drawString("敌机等级："+enemy_leval+(enemy_leval<max_enemy_leval?"":"(Max)"),380,40);

        g.drawString("战斗积分："+achievement,10,20);
    }

    // 计算飞机位置
    private void calcPlanPostion(MouseEvent e){
        int tempX = e.getX();
        int tempY = e.getY();
        if(plan != null && plan.getIsLive() == 1) { // 确认飞机对象存在并存活
            if(Math.abs(tempX-plan.getX()) < 150 && Math.abs(tempY-plan.getY()) < 150 ){ // 防止闪现
                if(tempX > 2 && tempX < 476){
                    plan.setX(tempX-50);
                }
                if(tempY > 100 && tempY < 680){
                    plan.setY(tempY-100);
                }
            }
        }
        // 重新画
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(plan.getIsLive()==1)calcPlanPostion(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

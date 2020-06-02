package GameFrame;

import javax.swing.*;

/*
 * @author Guihution
 * 2020/5/26 9:40
 */
public class GameFrame extends JFrame implements Runnable {

    // 面板 装元素
    private GamePanel panel = new GamePanel();

    public GameFrame(){
        initGame();
    }

    public GameFrame(String title){
        setTitle(title);
        initGame();
    }

    // 添加元素
    private void addElements(){
        add(panel);
    }

    // 将绑定了监听器的对象添加在窗体中
    private void addListener(){
        addMouseMotionListener(panel);
    }

    protected void initGame() {
        addElements();
        addListener();
        setBounds(1200,100,480,700);// 设置位置大小
        setResizable(false);// 设置不可拖拽
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置程序销毁
        setVisible(true);// 设置面板显示

    }

    @Override
    public void run() {
        // 初始化
        panel.init();
        System.out.println("init 初始化");
//        panel.init();
    }
}

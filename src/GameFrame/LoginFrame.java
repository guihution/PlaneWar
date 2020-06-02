package GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * @author Guihution
 * 2020/6/2 9:35
 */
public class LoginFrame extends JFrame {
    // 需要一个 panel 用来展示
    private JLabel jLabel = new JLabel();

    public LoginFrame(String title){
        this.setTitle(title);
        jLabel.setBounds(0,0,480,800);
        // label 上可以直接添加图片
        jLabel.setIcon(IconDictionaries.getInstance().getLogBd());
        this.add(jLabel);

        // 点击
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click:"+e.getX()+" "+e.getY());
                if(e.getX()>=120 && e.getX()<=355  && e.getY()>=585 && e.getY() <=645) {
                    LoginFrame.this.setVisible(false);// 垃圾回收还未执行
                    // 开一个新窗口
                    new Thread(new GameFrame(title)).start();
//                    jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }else {
//                    jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        // 移动
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getX()+" "+e.getY());
                if(e.getX()>=120 && e.getX()<=355  && e.getY()>=585 && e.getY() <=645) {
                    LoginFrame.this.setVisible(false);
                    // 开一个新窗口
                    new GameFrame();
//                    jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }else {
//                    jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        this.setBounds(1200,100,480,800);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}

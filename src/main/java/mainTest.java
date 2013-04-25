// a simple exmple that can show the basis of swing -------------------------------------------------------------------------
// import pakages which we need import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class mainTest {
    public static void main(String[] args) {
        HelloCsdnFrame frame = new HelloCsdnFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();
    }
}

/**
 * this part we construct a new frame HelloCsdnFrame
 */

class HelloCsdnFrame extends JFrame {
    JTextField t=new JTextField();
    JButton b1=new JButton("1");
    JButton b2=new JButton("2");
    JButton b3=new JButton("3");
    JButton b4=new JButton("+");
    JButton b5=new JButton("4");
    JButton b6=new JButton("5");
    JButton b7=new JButton("6");
    JButton b8=new JButton("-");
    JButton b9=new JButton("7");
    JButton b10=new JButton("8");
    JButton b11=new JButton("9");
    JButton b12=new JButton("*");
    JButton b13=new JButton(".");
    JButton b14=new JButton("0");
    JButton b15=new JButton("=");
    JButton b16=new JButton("/");
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();

    public HelloCsdnFrame() {
        //窗体属性的设置
        setTitle("计算器");
        setResizable(false);
        setBounds(100,100,230,230);   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //文本框属性的设置
        t.setEditable(false);
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setColumns(18);
        p1.add(t);
        add(p1,"North");
        //按钮属性的设置
        GridLayout l=new GridLayout(4,0);
        l.setVgap(10);
        l.setHgap(10);
        p2.setLayout(l);
        add(p2,"Center");
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        p2.add(b6);
        p2.add(b7);
        p2.add(b8);
        p2.add(b9);
        p2.add(b10);
        p2.add(b11);
        p2.add(b12);
        p2.add(b13);
        p2.add(b14);
        p2.add(b15);
        p2.add(b16);
        //窗体左右两侧添加空白标签
        JLabel ll=new JLabel();
        ll.setPreferredSize(new Dimension(10,0));
        add(ll,"West");
        JLabel rl=new JLabel();
        rl.setPreferredSize(new Dimension(10,0));
        add(rl,"East");
        setVisible(true);


    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
}

/**
 * this part we extend our HelloCsdnFram to JFrame and  construct a new object HelloCsdnPanel and add it on the frame
 */
class HelloCsdnPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello +多宝", MESSAGE_X, MESSAGE_Y);
    }

    public static final int MESSAGE_X = 100;
    public static final int MESSAGE_Y = 100;
} /** A panel that display a message */


import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MewTest extends JFrame
{
    public static void main(String arg[])
    {
        JFrame main = new MewTest();
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(100,100);
    }
    
    public MewTest()
    {
        this.setLayout(new GridLayout(5,1));
        JLabel a = new JLabel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        JLabel b = new JLabel("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        JLabel c = new JLabel("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        JLabel d = new JLabel("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        JLabel e = new JLabel("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        add(a);
        add(b);
        add(c);
        add(d);
        add(e);
        a.addMouseListener(new Meh());
        b.addMouseListener(new Meh());
        c.addMouseListener(new Meh());
        d.addMouseListener(new Meh());
        e.addMouseListener(new Meh());
    }
    
}

class Meh implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println((e.getSource()));
        }
        public void mouseEntered(MouseEvent e){};
        public void mouseExited(MouseEvent e){};
        public void mousePressed(MouseEvent e){};
        public void mouseReleased(MouseEvent e){};
    }
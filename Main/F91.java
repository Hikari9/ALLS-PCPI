import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
public class F91
{
    public static BigInteger z = BigInteger.ZERO;
    public static JButton y = new JButton("Press me");
    public static void main(String arg[]) throws Exception
    {
        JFrame x = new JFrame();
        
        x.add(y,BorderLayout.CENTER);
        x.setSize(100,100);
        x.setVisible(true);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        y.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               z = z.add(BigInteger.ONE);
               y.setText(z.toString());
           }
        });
    }
    
    public static int func(int x)
    {
        if(x >= 101)
        {
            return x-10;
        }
        else
        {
            return func(func(x+11));
        }
    }
}
